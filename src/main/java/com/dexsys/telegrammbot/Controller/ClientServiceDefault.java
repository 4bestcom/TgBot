package com.dexsys.telegrammbot.Controller;


import com.dexsys.telegrammbot.DTO.UserDTO;
import com.dexsys.telegrammbot.Domain.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;


public class ClientServiceDefault implements IClientServiceAction {
    @Override
    public ResponseEntity<String> createUser(User user) {
        return new ResponseEntity<>("{\n" +
                "  \"id\": \"26b97450-e0b3-42a2-8b41-1138b5b75a05\",\n" +
                "  \"firstName\": \"Jadyn\",\n" +
                "  \"secondName\": \"Bartell\",\n" +
                "  \"middleName\": null,\n" +
                "  \"birthDay\": \"1958-10-31T17:46:43.470+00:00\",\n" +
                "  \"phone\": \"(427) 437-1165\",\n" +
                "  \"chatId\": null,\n" +
                "  \"male\": false\n" +
                "}", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> readUserFromServer(String uuid) {
        return new ResponseEntity<>(UserDTO.builder()
                .birthDay("1971-10-30T07:05:47.513+00:00")
                .chatId("59698484")
                .firstName("Inokentii")
                .id(UUID.randomUUID())
                .male(true)
                .secondName("Ivanovich")
                .middleName("Petrov")
                .phone("+79019999999")
                .build(), HttpStatus.OK);
    }

    @Override  //this method is for inner business logic
    public boolean readUserPhoneFromServer(String phone) {
        return true;
    }

    @Override
    public ResponseEntity<Set<HttpMethod>> getOptionsFromServer(String uuid) {
        Set<HttpMethod> httpMethodSet = new HashSet<>(Arrays.asList(HttpMethod.GET,
                HttpMethod.POST, HttpMethod.OPTIONS, HttpMethod.DELETE));
        return new ResponseEntity<>(httpMethodSet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> generateUser() {
        return new ResponseEntity<>("{\n" +
                "  \"id\": \"fc6b7e78-1a51-4195-8d7b-77eaf4d30f08\",\n" +
                "  \"firstName\": \"Darrion\",\n" +
                "  \"secondName\": \"Stanton\",\n" +
                "  \"middleName\": null,\n" +
                "  \"birthDay\": \"1971-10-30T07:05:47.513+00:00\",\n" +
                "  \"phone\": \"1-594-856-6557\",\n" +
                "  \"chatId\": null,\n" +
                "  \"male\": true\n" +
                "}", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> readAllUserFromServer() {
        List<UserDTO> listUserDTO = Arrays.asList(
                UserDTO.builder()
                        .birthDay("1971-10-30T07:05:47.513+00:00")
                        .chatId("59698484")
                        .firstName("Inokentii")
                        .id(UUID.randomUUID())
                        .male(true)
                        .secondName("Ivanovich")
                        .middleName("Petrov")
                        .phone("+79019999999")
                        .build(),
                UserDTO.builder()
                        .birthDay("1953-09-30T07:05:47.513+00:00")
                        .chatId("878184")
                        .firstName("Ivan")
                        .id(UUID.randomUUID())
                        .male(true)
                        .secondName("Pavlovich")
                        .middleName("Kuznecov")
                        .phone("+79018888888")
                        .build());
        return new ResponseEntity<>(listUserDTO, HttpStatus.OK);
    }
}

