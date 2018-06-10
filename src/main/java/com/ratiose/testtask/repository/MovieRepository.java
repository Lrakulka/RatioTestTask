package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findByTmdbId(String tmdbId);
}
