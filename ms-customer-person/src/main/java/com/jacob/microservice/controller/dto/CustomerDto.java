package com.jacob.microservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CustomerDto {

    @Getter
    @Setter
    public static class Response{
        private Long id;
        private String name;
        private String gender;
        private int age;
        private String identification;
        private String address;
        private String phone;
        private String password;
        private boolean state;
    }


    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String name;
        private String gender;
        private int age;
        private String identification;
        private String address;
        private String phone;
        private String password;
        private boolean state;
    }
}
