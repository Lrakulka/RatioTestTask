package com.ratiose.testtask.service;

public interface BaseTmdbService<T> {
    T findByTmdbId(String tmdbId);

    T register(String id);
}
