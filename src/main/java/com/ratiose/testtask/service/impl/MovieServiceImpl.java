package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.Movie;
import com.ratiose.testtask.repository.MovieRepository;
import com.ratiose.testtask.service.BaseTmdbService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MovieServiceImpl implements BaseTmdbService<Movie> {
    @Autowired
    private TmdbApi tmdbApi;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findByTmdbId(String actorId) {
        return movieRepository.findByTmdbId(actorId);
    }

    @Override
    public Movie register(String tmdbId) {
        if (!tmdbApi.isMovieExist(tmdbId) || Objects.nonNull(movieRepository.findByTmdbId(tmdbId))) {
            return null;
        }
        final Movie movie = createActor(tmdbId);
        return movieRepository.save(movie);
    }

    protected Movie createActor(String tmdbId) {
        Movie actor = new Movie();
        actor.setTmdbId(tmdbId);
        return actor;
    }
}
