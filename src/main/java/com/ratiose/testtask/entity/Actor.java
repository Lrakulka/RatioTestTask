package com.ratiose.testtask.entity;

import javax.persistence.*;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    String tmdbId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }
}
