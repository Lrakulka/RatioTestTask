package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.ratiose.testtask.controller.ControllerUtils.getReturnStatus;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private UserFacade userFacade;

    // TODO: configure security
    @RequestMapping(value = "/add", method = PATCH)
    public ResponseEntity addActor(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String actorId,
                                   HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return getReturnStatus(Objects.nonNull(user) && Objects.nonNull(userFacade.addActor(user, actorId)));
    }

    @RequestMapping(value = "/remove", method = PATCH)
    public ResponseEntity removeActor(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String actorId,
                                      HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return getReturnStatus(Objects.nonNull(user) && Objects.nonNull(userFacade.removeActor(user, actorId)));
    }
}
