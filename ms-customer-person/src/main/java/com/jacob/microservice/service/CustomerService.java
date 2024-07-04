package com.jacob.microservice.service;

import com.jacob.microservice.controller.dto.CustomerDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {


    List<CustomerDto.Response> getAllCustomers();

    CustomerDto.Response getCustomerById(Long id);

    CustomerDto.Response createCustomer(CustomerDto.Request request);

    CustomerDto.Response updateCustomer(Long id, CustomerDto.Request request);

    void deleteCustomer(Long id);
}
