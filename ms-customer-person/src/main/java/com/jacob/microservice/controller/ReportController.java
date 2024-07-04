package com.jacob.microservice.controller;

import com.jacob.microservice.controller.dto.CustomerDto;
import com.jacob.microservice.service.CustomerService;
import com.jacob.microservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reporte")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> generarReporteEstadoCuenta(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin,
            @RequestParam String cliente) {

        Map<String, Object> reporte = service.getTrasactionsByFilters(fechaInicio, fechaFin, cliente);
        return ResponseEntity.ok(reporte);
    }

}
