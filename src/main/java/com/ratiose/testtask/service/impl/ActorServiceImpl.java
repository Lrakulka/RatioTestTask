package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.repository.ActorRepository;
import com.ratiose.testtask.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor findById(String actorId) {
        return actorRepository.findByTmdbId(actorId);
    }

    @Override
    public Actor registerActor(String tmdbId) {
        Actor actor = actorRepository.findByTmdbId(tmdbId);
        if (actor != null) {
            return null;
        }
        actor = createActor(tmdbId);
        return actorRepository.save(actor);
    }

    private Actor createActor(String tmdbId) {
        Actor actor = new Actor();
        actor.setTmdbId(tmdbId);
        return actor;
    }
}
