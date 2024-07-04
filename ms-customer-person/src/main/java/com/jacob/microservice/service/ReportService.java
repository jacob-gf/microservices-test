package com.jacob.microservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface ReportService {

    Map<String, Object> getTrasactionsByFilters(LocalDate fechaInicio, LocalDate fechaFin, String clienteId);
}
