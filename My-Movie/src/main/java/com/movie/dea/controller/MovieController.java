package com.movie.dea.controller;

import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/pagination")
    public Page<Movie> getPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return movieService.getMoviesByPage(page, size);
    }

    @GetMapping("/all")
    public List<Movie> getMovies(){
        return movieService.getAllMovie();
    }

    @GetMapping("/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getAllMovieByTitle(title);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getAllMovieByGenre(genre);
    }

    @GetMapping("/rating/{minRating}")
    public List<Movie> getAllMoviesByMinRating(@PathVariable Double minRating) {
        return movieService.getAllMovieByMinRating(minRating);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id){
        return movieService.getMovie(id);
    }

    @GetMapping("/date/{releaseDate}")
    public List<Movie> getMovieByDate(@PathVariable LocalDate releaseDate){
        return movieService.getAllMovieByReleaseDate(releaseDate);
    }

    @PostMapping("/add")
    public Movie creatMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        return movieService.deleteById(id);
    }
}
