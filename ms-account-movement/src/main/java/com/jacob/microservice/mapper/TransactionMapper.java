package com.jacob.microservice.mapper;


import com.jacob.microservice.controller.dto.TransactionDto;
import com.jacob.microservice.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    List<TransactionDto.Response> toTransactionDtoList(List<TransactionEntity> transactionEntityEntityList);

    TransactionDto.Response toTransactionDto(TransactionEntity transactionEntity);

    TransactionEntity toTransactionEntity(TransactionDto.Request transactionDto);
}
