//package com.movie.dea.controller;
//
//import com.movie.dea.entity.Movie;
//import com.movie.dea.service.MovieService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api-movie")
//@Tag(name="Movies", description = "Movie management API")
//public class MovieRestController {
//    private final MovieService movieService;
//
//    public MovieRestController(MovieService movieService) {
//        this.movieService = movieService;
//    }
//
//    @Operation(summary = "Get All Movies")
//    @GetMapping
//    public List<Movie> moviesAll() {
//        return movieService.getAllMovie();
//    }
//
//    @Operation(summary = "Get Movie by id")
//    @GetMapping
//    public Movie getMovieById(@PathVariable Integer id) {
//        return movieService.getMovie(id);
//    }
//}
