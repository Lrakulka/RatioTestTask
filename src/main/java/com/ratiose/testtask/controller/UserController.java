package com.ratiose.testtask.controller;

import com.ratiose.testtask.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.ratiose.testtask.controller.ControllerUtils.getReturnStatus;
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
        return getReturnStatus(Objects.nonNull(userFacade.registerUser(email, password)));
    }
}
