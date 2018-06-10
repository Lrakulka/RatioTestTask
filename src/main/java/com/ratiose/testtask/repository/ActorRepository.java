package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {
    Actor findByTmdbId(String tmdbId);
}
