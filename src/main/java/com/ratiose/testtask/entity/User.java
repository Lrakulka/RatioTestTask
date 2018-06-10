package com.ratiose.testtask.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    String email;

    String password;

    @ElementCollection(targetClass=Actor.class)
    Set<Actor> favoriteActors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Actor> getFavoriteActors() {
        return favoriteActors;
    }

    public void setFavoriteActors(Set<Actor> favoriteActors) {
        this.favoriteActors = favoriteActors;
    }
}