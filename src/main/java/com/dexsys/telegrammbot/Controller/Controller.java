package com.dexsys.telegrammbot.Controller;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Services.IUserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/")
public class Controller {
    private IUserAction iUserAction;
    @Autowired
    public void setiUserAction(IUserAction iUserAction) {
        this.iUserAction = iUserAction;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable ("id") long id){
        User user = iUserAction.readUserFromBase(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = iUserAction.readAllUserFromBase();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id){
        boolean del = iUserAction.deleteUser(id);
        if (del){
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
