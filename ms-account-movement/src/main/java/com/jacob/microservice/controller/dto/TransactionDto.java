package com.jacob.microservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TransactionDto {
    @Getter
    @Setter
    public static class Response {
        private Long id;
        private LocalDateTime date;
        private String transactionType;
        private Double value;
        private Double balance;
    }

    @Getter @Setter
    public static class Request {
        private Long id;
        private Double value;
        private Long accountId;
    }
}
