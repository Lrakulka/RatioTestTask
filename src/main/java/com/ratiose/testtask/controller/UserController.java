package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
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

    // TODO: configure security
    @RequestMapping(value = "/addActor", method = POST)
    public ResponseEntity addActor(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String actorId,
                                   HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return getReturnStatus(Objects.nonNull(user) && Objects.nonNull(userFacade.addActor(user, actorId)));
    }

    @RequestMapping(value = "/removeActor", method = PATCH)
    public ResponseEntity removeActor(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String actorId,
                                      HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return getReturnStatus(Objects.nonNull(user) && Objects.nonNull(userFacade.removeActor(user, actorId)));
    }

    private ResponseEntity getReturnStatus(boolean result) {
        return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(null);
    }
}
