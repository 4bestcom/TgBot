package com.dexsys.telegrammbot.Controller;

import com.dexsys.telegrammbot.DTO.UserDTO;
import com.dexsys.telegrammbot.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class ClientService implements IClientServiceAction {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final String URL = "https://serene-coast-56441.herokuapp.com/api/users/";
    private RestOperations restTemplate;
    @Autowired
    public void setRestOperations(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    //this method is for inner business logic
    public boolean readUserPhoneFromServer(String phone) {
        UserDTO[] userDTOarr = restTemplate.getForObject(URL, UserDTO[].class);
        log.info("array UserDTO: " + Arrays.toString(userDTOarr));
        if (userDTOarr != null) {
            log.info("check phone to MockServer");
            return Arrays.stream(userDTOarr)
                    .anyMatch(n -> n.getPhone().equals(phone));
        }
        log.warn("userDTOarr == null");
        return false;
    }

    public ResponseEntity<String> createUser(User user) {
        String[] BirthDateUser = user.getBirthDate().split("\\.");
        LocalDateTime dateTimeBirthDateUser = LocalDateTime.of(Integer.parseInt(BirthDateUser[2]),
                Integer.parseInt(BirthDateUser[1]),
                Integer.parseInt(BirthDateUser[0]), 00, 00, 00);
        UserDTO userDTO = UserDTO.builder()
                .birthDay(dateTimeBirthDateUser.toString())
                .chatId(String.valueOf(user.getChatId()))
                .id(UUID.randomUUID())
                .phone(user.getPhone())
                .firstName(user.getUserName())
                .build();
        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO);
        ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
        if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            return new ResponseEntity<>(response.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String> generateUser() {
        HttpEntity<String> entity = new HttpEntity<>("");
        ResponseEntity<String> response = restTemplate.postForEntity(URL + "generate", entity, String.class);
        if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            return new ResponseEntity<>(response.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> readUserFromServer(String uuid) {
        UserDTO userDTO = restTemplate.getForObject(URL + "/" + uuid, UserDTO.class);
        if (userDTO == null) {
            log.warn("userDTO is not found");
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }
        log.info("userDTO is found");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> readAllUserFromServer() {
        UserDTO[] userDTOarr = restTemplate.getForObject(URL, UserDTO[].class);
        List<UserDTO> listUsers = Arrays.asList(userDTOarr);
        if (!listUsers.isEmpty()) {
            log.info("list UserDTO: " + Arrays.toString(userDTOarr));
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        log.warn("listUserDTO is empty");
        return new ResponseEntity<>(listUsers, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Set<HttpMethod>> getOptionsFromServer(String uuid) {
        Set<HttpMethod> httpMethodSet = restTemplate.optionsForAllow(URL + "/" + uuid);
        if (httpMethodSet.isEmpty()) {
            log.warn("Options method not found");
            return new ResponseEntity<>(httpMethodSet, HttpStatus.NOT_FOUND);
        }
        log.info("Options method is found");
        return new ResponseEntity<>(httpMethodSet, HttpStatus.OK);
    }
}
