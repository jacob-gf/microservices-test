package com.jacob.microservice.integration;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@FeignClient(name = "ms-account-movement",  url = "${feign.client.transaction.url}")
public interface TransactionClient {

    @GetMapping("/movimientos/reporte")
    Map<String, Object> getTrasactionsByFilters(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin,
            @RequestParam Long clienteId);


}
