package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.facade.UserFacade;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.ratiose.testtask.controller.ControllerUtils.getReturnStatus;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TmdbApi tmdbApi;

    @RequestMapping(value = "/popular", method = POST)
    public ResponseEntity popular(@RequestParam String email,
                                  @RequestParam String password,
                                  HttpSession session) {
        if (userFacade.findUser(email, password) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String popularMovies = tmdbApi.popularMovies();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(popularMovies);
    }

    @RequestMapping(value = "/addWatched", method = PATCH)
    public ResponseEntity addActor(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String movieId,
                                   HttpSession session) {
        final User user = userFacade.findUser(email, password);
        return getReturnStatus(Objects.nonNull(user) && Objects.nonNull(userFacade.addWatchedMovie(user, movieId)));
    }
}
