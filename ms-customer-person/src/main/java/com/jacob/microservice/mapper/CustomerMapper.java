package com.jacob.microservice.mapper;

import com.jacob.microservice.controller.dto.CustomerDto;
import com.jacob.microservice.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    List<CustomerDto.Response> toCustomerDtoList(List<CustomerEntity> customerEntityEntityList);

    CustomerDto.Response toCustomerDto(CustomerEntity customerEntity);

    CustomerEntity toCustomerEntity(CustomerDto.Request customerDto);



}
