package com.jacob.microservice.mapper;

import com.jacob.microservice.controller.dto.AccountDto;
import com.jacob.microservice.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    List<AccountDto.Response> toAccountDtoList(List<AccountEntity> accountEntityEntityList);

    AccountDto.Response toAccountDto(AccountEntity accountEntity);

    AccountEntity toAccountEntity(AccountDto.Request accountDto);
}
