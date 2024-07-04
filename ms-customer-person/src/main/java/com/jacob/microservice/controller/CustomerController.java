package com.jacob.microservice.controller;

import com.jacob.microservice.controller.dto.CustomerDto;
import com.jacob.microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto.Response>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto.Response> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto.Response> createCustomer(@RequestBody CustomerDto.Request request){
        return new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CustomerDto.Response> updateCustomer(@PathVariable Long id, @RequestBody  CustomerDto.Request request){
        return new ResponseEntity<>(customerService.updateCustomer(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        if (customerService.getCustomerById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
