package com.jacob.microservice.service;

import com.jacob.microservice.controller.dto.TransactionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<TransactionDto.Response> getAllTransactions();

    TransactionDto.Response getTransactionById(Long id);

    TransactionDto.Response createTransaction(TransactionDto.Request request);

    TransactionDto.Response updateTransaction(Long id, TransactionDto.Request request);

    void deleteTransaction(Long id);

    Map<String, Object> generarReporteEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId);
}
