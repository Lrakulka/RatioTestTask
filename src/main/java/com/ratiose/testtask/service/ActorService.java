package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.Actor;

public interface ActorService {
    Actor findByTmdbId(String actorId);

    Actor registerActor(String actorId);
}
