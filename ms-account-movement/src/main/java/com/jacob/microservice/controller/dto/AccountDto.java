package com.jacob.microservice.controller.dto;


import lombok.Getter;
import lombok.Setter;

public class AccountDto {

    @Getter @Setter
    public static class Response {
        private Long id;
        private String accountNumber;
        private String accountType;
        private Double initialBalance;
        private boolean state;
    }

    @Getter @Setter
    public static class Request {
        private Long id;
        private String accountNumber;
        private String accountType;
        private Double initialBalance;
        private boolean state;
    }
}
