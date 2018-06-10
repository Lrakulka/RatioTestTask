package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.Actor;

public interface ActorService {
    Actor findById(String actorId);

    Actor registerActor(String actorId);
}
