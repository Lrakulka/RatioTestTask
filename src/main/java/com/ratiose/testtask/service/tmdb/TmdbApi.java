package com.ratiose.testtask.service.tmdb;

public interface TmdbApi {
    String popularMovies();

    boolean isActorExist(String actorId);

    boolean isMovieExist(String movieId);
}
