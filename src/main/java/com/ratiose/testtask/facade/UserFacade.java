package com.ratiose.testtask.facade;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.entity.User;

public interface UserFacade {
    User registerUser(String email, String password);

    User findUser(String email, String password);

    Actor addActor(User email, String actorId);
}
