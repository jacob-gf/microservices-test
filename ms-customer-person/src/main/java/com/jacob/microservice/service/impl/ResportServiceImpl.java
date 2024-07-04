package com.jacob.microservice.service.impl;

import com.jacob.microservice.entity.CustomerEntity;
import com.jacob.microservice.integration.TransactionClient;
import com.jacob.microservice.repository.CustomerRepository;
import com.jacob.microservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class ResportServiceImpl implements ReportService {

    @Autowired
    private TransactionClient transactionClient;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Object> getTrasactionsByFilters(LocalDate fechaInicio, LocalDate fechaFin, String cliente) {

        Optional<CustomerEntity> customer = customerRepository.findByNameContainingIgnoreCase(cliente);
        if (customer.isPresent()){
            return transactionClient.getTrasactionsByFilters(fechaInicio, fechaFin, customer.get().getId());
        }

        return null;
    }
}
