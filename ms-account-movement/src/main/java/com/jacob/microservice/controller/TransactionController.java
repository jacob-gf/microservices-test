package com.jacob.microservice.controller;

import com.jacob.microservice.controller.dto.TransactionDto;
import com.jacob.microservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto.Response>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto.Response> getTransactionById(@PathVariable Long id){
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionDto.Response> createTransaction(@RequestBody TransactionDto.Request request){
        return new ResponseEntity<>(transactionService.createTransaction(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto.Response> updateTransaction(@PathVariable Long id, @RequestBody  TransactionDto.Request request){
        return new ResponseEntity<>(transactionService.updateTransaction(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        if (transactionService.getTransactionById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reporte")
    public ResponseEntity<Map<String, Object>> generarReporteEstadoCuenta(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin,
            @RequestParam Long clienteId) {

        Map<String, Object> reporte = transactionService.generarReporteEstadoCuenta(fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59), clienteId);
        return ResponseEntity.ok(reporte);
    }

}
