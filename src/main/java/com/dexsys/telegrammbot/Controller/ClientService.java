package com.dexsys.telegrammbot.Controller;

import com.dexsys.telegrammbot.DTO.UserDTO;
import com.dexsys.telegrammbot.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

@Service
public class ClientService implements IClientServiceAction {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private static final String URL = "https://serene-coast-56441.herokuapp.com/api/users/";


    public boolean readUserPhoneFromServer(String phone) {
        RestTemplate restTemplate = new RestTemplate();
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

    public ResponseEntity<UserDTO> createUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO userDTO = UserDTO.builder()
                .birthDay(user.getBirthDate())
                .chatId(String.valueOf(user.getChatId()))
                .id(UUID.randomUUID().toString())
                .phone(user.getPhone())
                .firstName(user.getUserName())
                .build();
        restTemplate.postForObject(URL, userDTO, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public UserDTO readUserFromServer(String uuid) {
        RestTemplate restTemplate = new RestTemplate();
        UserDTO userDTO = restTemplate.getForObject(URL + "/" + uuid, UserDTO.class);
        if (userDTO == null) {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                log.warn("user not found");
            }
        }
        log.info("userDTO is found");
        return userDTO;
    }


}
