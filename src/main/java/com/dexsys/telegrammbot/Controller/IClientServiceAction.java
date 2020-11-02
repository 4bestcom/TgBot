package com.dexsys.telegrammbot.Controller;

import com.dexsys.telegrammbot.DTO.UserDTO;
import com.dexsys.telegrammbot.Domain.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IClientServiceAction {
    boolean readUserPhoneFromServer(String phone);

    ResponseEntity<String> createUser(User user);

    ResponseEntity<UserDTO> readUserFromServer(String uuid);

    ResponseEntity<Set<HttpMethod>> getOptionsFromServer(String uuid);

    ResponseEntity<String> generateUser();

    ResponseEntity<List<UserDTO>> readAllUserFromServer();

}
