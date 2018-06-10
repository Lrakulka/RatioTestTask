package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.facade.UserFacade;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity registerUser(@RequestParam String email,
                                               @RequestParam String password,
                                               HttpSession session) {
        if (userFacade.registerUser(email, password) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // TODO: configure security
    @RequestMapping(value = "/addActor", method = POST)
    public ResponseEntity addActor(@RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam String actorId,
                                       HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return (Objects.isNull(user) || Objects.isNull(userFacade.addActor(user, actorId)))
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
                : ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
