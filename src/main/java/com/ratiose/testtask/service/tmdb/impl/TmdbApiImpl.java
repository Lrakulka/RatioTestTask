package com.ratiose.testtask.service.tmdb.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Objects;

@Service
public class TmdbApiImpl implements TmdbApi {
    private static final String POPULAR_MOVIES = "/movie/popular";
    private static final String ACTOR_INFO = "/person/{person_id}";
    private static final String MOVIE_INFO = "/movie/{movie_id}";

    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Override
    public String popularMovies() throws IllegalArgumentException {
        return executeRequest(POPULAR_MOVIES);
    }

    @Override
    public boolean isActorExist(String actorId) {
        return Objects.nonNull(executeRequest(ACTOR_INFO.replace("{person_id}", actorId)));
    }

    @Override
    public boolean isMovieExist(String movieId) {
        return Objects.nonNull(executeRequest(MOVIE_INFO.replace("{movie_id}", movieId)));
    }

    private String executeRequest(final String requestUrl) {
        try {
            String url = getTmdbUrl(requestUrl);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

            if (jsonResponse.getStatus() != HttpStatus.SC_OK) {
                return null;
            }

            return jsonResponse.getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTmdbUrl(String tmdbItem) throws URISyntaxException {
        StringBuilder builder = new StringBuilder(tmdbApiBaseUrl);
        builder.append(tmdbItem);
        URIBuilder uriBuilder = new URIBuilder(builder.toString());
        uriBuilder.addParameter("language", tmdbLanguage);
        uriBuilder.addParameter("api_key", tmdbApiKey);
        return uriBuilder.build().toString();
    }
}
