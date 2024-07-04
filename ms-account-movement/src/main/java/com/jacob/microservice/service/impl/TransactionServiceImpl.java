package com.jacob.microservice.service.impl;

import com.jacob.microservice.controller.dto.TransactionDto;
import com.jacob.microservice.entity.AccountEntity;
import com.jacob.microservice.entity.TransactionEntity;
import com.jacob.microservice.exception.ResourceNotFoundException;
import com.jacob.microservice.exception.SaldoInsuficienteException;
import com.jacob.microservice.mapper.TransactionMapper;
import com.jacob.microservice.repository.AccountRepository;
import com.jacob.microservice.repository.TransactionRepository;
import com.jacob.microservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto.Response> getAllTransactions() {
        return transactionMapper.toTransactionDtoList(transactionRepository.findAll());
    }

    @Override
    public TransactionDto.Response getTransactionById(Long id) {
        return transactionMapper.toTransactionDto(transactionRepository.findById(id).get());
    }

    @Override
    public TransactionDto.Response createTransaction(TransactionDto.Request request) {
        Optional<AccountEntity> account = accountRepository.findById(request.getAccountId());
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("Cuenta no encontrada con ID: " + request.getAccountId());
        }

        Double saldoActual = account.get().getInitialBalance();
        Double valorMovimiento = request.getValue();

        // Determinar el tipo de movimiento y actualizar el saldo
        if (request.getValue() > 0) {
            account.get().setInitialBalance(saldoActual + valorMovimiento);
        } else if (request.getValue() < 0) {
            if (saldoActual < valorMovimiento) {
                throw new SaldoInsuficienteException("Saldo no disponible para realizar el movimiento");
            }
            account.get().setInitialBalance(saldoActual + valorMovimiento);
        }
        AccountEntity accountSaved = accountRepository.save(account.get());

        TransactionEntity transaction = transactionMapper.toTransactionEntity(request);
        transaction.setAccount(accountSaved);
        transaction.setDate(LocalDateTime.now());
        transaction.setBalance(accountSaved.getInitialBalance());
        return transactionMapper.toTransactionDto(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDto.Response updateTransaction(Long id, TransactionDto.Request request) {
        return transactionMapper.toTransactionDto(transactionRepository.save(transactionMapper.toTransactionEntity(request)));
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> generarReporteEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId) {
        // Obtener cuentas asociadas al cliente
        List<AccountEntity> cuentas = accountRepository.findAllByCustomerId(clienteId);

        // Crear un mapa para almacenar la información del reporte
        Map<String, Object> reporte = new HashMap<>();

        // Iterar sobre cada cuenta para obtener su saldo y movimientos en el rango de fechas
        for (AccountEntity cuenta : cuentas) {
            List<TransactionEntity> movimientos = transactionRepository.findByAccountAndDateBetween(cuenta,fechaInicio, fechaFin);
            Map<String, Object> infoCuenta = new HashMap<>();
            infoCuenta.put("numeroCuenta", cuenta.getAccountNumber());
            infoCuenta.put("tipoCuenta", cuenta.getAccountType());
            infoCuenta.put("saldoActual", twoDecimal(cuenta.getInitialBalance()));
            List<Map<String, Object>> transactionsFormat = new ArrayList<>();
            for (TransactionEntity movimiento : movimientos) {
                Map<String, Object> movimientoMap = new HashMap<>();
                movimientoMap.put("fecha", movimiento.getDate());
                movimientoMap.put("valor", twoDecimal(movimiento.getValue()));
                movimientoMap.put("saldo", twoDecimal(movimiento.getBalance()));
                transactionsFormat.add(movimientoMap);
            }
            infoCuenta.put("movimientos", transactionsFormat);
            reporte.put("Cuenta-" + cuenta.getAccountNumber(), infoCuenta);
        }

        return reporte;
    }

    private BigDecimal twoDecimal(Double valor) {
        if (valor == null) return null;
        return new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
    }
}
