package com.movie.dea.controller;

import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviePageController { // controller UI
    private final MovieService movieService;

    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false)String title,
            @RequestParam(required = false)String genre,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {
        Sort sort = direction.equals("asc")
                ? Sort.by(sortBy).ascending() // от меньшего к большему
                : Sort.by(sortBy).descending(); // от большего к меньшему

        List<Movie> movies = movieService.getAllMovie(); // ?

        model.addAttribute("movies", movieService.search(title, genre, sort));
        model.addAttribute("title", title);
        model.addAttribute("genre", genre);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        return "movies/list";
    }

    // формат добавлния
    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("movieForm", new MovieForm());
        return "movies/new";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("movieForm") MovieForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return  form.getId() == null ? "movies/new" : "movies/edit";
        }

        Movie movie;

        if (form.getId() == null) {
            movie = new Movie();
        } else {
            movie = movieService.getMovie(form.getId());
        }

       // add
        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre());
        movie.setRating(form.getRating());
        movie.setDuration(form.getDuration());
        movie.setReleaseDate(form.getReleaseDate());


        movieService.createMovie(movie);
        return "redirect:/movies";
    }

    // формат обновлении
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){

        Movie movie = movieService.getMovie(id);
        MovieForm form = new MovieForm();
        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setGenre(movie.getGenre());
        form.setRating(movie.getRating());
        form.setDuration(movie.getDuration());
        form.setReleaseDate(movie.getReleaseDate());

        model.addAttribute("movieForm", form);
        return "movies/edit";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        movieService.deleteById(id);
        return "redirect:/movies";
    }
}
