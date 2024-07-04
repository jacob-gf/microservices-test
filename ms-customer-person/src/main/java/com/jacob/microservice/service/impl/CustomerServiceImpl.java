package com.jacob.microservice.service.impl;

import com.jacob.microservice.controller.dto.CustomerDto;
import com.jacob.microservice.entity.CustomerEntity;
import com.jacob.microservice.mapper.CustomerMapper;
import com.jacob.microservice.repository.CustomerRepository;
import com.jacob.microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto.Response> getAllCustomers() {
        return customerMapper.toCustomerDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto.Response getCustomerById(Long id) {
        return customerMapper.toCustomerDto(customerRepository.findById(id).get());
    }

    @Override
    public CustomerDto.Response createCustomer(CustomerDto.Request request) {
        CustomerEntity data = customerMapper.toCustomerEntity(request);

        return customerMapper.toCustomerDto(customerRepository.save(data));
    }

    @Override
    public CustomerDto.Response updateCustomer(Long id, CustomerDto.Request request) {
        return customerMapper.toCustomerDto(customerRepository.save(customerMapper.toCustomerEntity(request)));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
