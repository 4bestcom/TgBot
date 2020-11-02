package com.dexsys.telegrammbot.controller;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.services.userservices.IUserAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "operations")
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private IUserAction iUserAction;

    @Autowired
    public void setiUserAction(IUserAction iUserAction) {
        this.iUserAction = iUserAction;
    }

    @GetMapping("/{phone}")
    @ApiOperation(value = "Search a user with an phone")
    public ResponseEntity<User> getUser(@PathVariable("phone") String phone) {
        try {
            User user = iUserAction.readUserUsingPhone(phone);
            log.info("user is found");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn("user not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search a user with an ID")
    public ResponseEntity<User> getUserId(@PathVariable("id") long chatId) {
        try {
            User user = iUserAction.readUserFromBase(chatId);
            log.info("user is found");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn("user not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "View a list of users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = iUserAction.readAllUserFromBase();
        if (users.isEmpty()) {
            log.warn("the user base is empty");
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user with an ID")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
        boolean del = iUserAction.deleteUser(id);
        if (del) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            log.warn("user not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
