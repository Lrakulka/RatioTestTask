package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.repository.ActorRepository;
import com.ratiose.testtask.service.BaseTmdbService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ActorServiceImpl implements BaseTmdbService<Actor> {
    @Autowired
    private TmdbApi tmdbApi;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor findByTmdbId(String actorId) {
        return actorRepository.findByTmdbId(actorId);
    }

    @Override
    public Actor register(String tmdbId) {
        if (!tmdbApi.isActorExist(tmdbId) || Objects.nonNull(actorRepository.findByTmdbId(tmdbId))) {
            return null;
        }
        final Actor actor = createActor(tmdbId);
        return actorRepository.save(actor);
    }

    protected Actor createActor(String tmdbId) {
        Actor actor = new Actor();
        actor.setTmdbId(tmdbId);
        return actor;
    }
}
