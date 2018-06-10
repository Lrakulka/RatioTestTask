package com.ratiose.testtask.facade.impl;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.facade.UserFacade;
import com.ratiose.testtask.repository.UserRepository;
import com.ratiose.testtask.service.ActorService;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(final String email, final String password) {
        return userService.registerUser(email, password);
    }

    @Override
    public User findUser(final String email, final String password) {
        return userService.findUser(email, password);
    }

    @Override
    public Actor addActor(final User user, final String actorId) {
        final Actor actor = Optional.ofNullable(actorService.findByTmdbId(actorId))
                .orElseGet(() -> actorService.registerActor(actorId));

        if (Objects.nonNull(actor)) {
            user.getFavoriteActors().add(actor);
            userRepository.save(user);
        }
        return actor;
    }

    @Override
    public Actor removeActor(User user, String actorId) {
        final Actor actor = actorService.findByTmdbId(actorId);
        if (Objects.nonNull(actor)) {
            user.getFavoriteActors().remove(actor);
            userRepository.save(user);
        }
        return actor;
    }
}
