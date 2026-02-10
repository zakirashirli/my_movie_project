package com.movie.dea.service;

import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Movie;
import com.movie.dea.exception.MovieNotFoundException;
import com.movie.dea.repository.MovieRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }
//    public List<Movie> getAllMovieByTitle(String title) {
//        return movieRepository.findByTitle(title);
//    }
//    public List<Movie> getAllMovieByGenre(String genre) {
//        return movieRepository.findByGenre(genre);
//    }
    public List<Movie> getAllMovieByMinRating(Double minRating) {
        return movieRepository.findByMinRating(minRating);
    }
    public List<Movie> getAllMovieByReleaseDate(LocalDate releaseDate){
        return movieRepository.findByReleaseDate(releaseDate);
    }

    public Movie createMovie(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    public Movie getMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new MovieNotFoundException("No such a movie in db with the following id: " + id));
    }

    public Page<Movie> getMoviesByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedMovie.getTitle());
                    existing.setGenre(updatedMovie.getGenre());
                    existing.setDuration(updatedMovie.getDuration());
                    existing.setRating(updatedMovie.getRating());
                    existing.setReleaseDate(updatedMovie.getReleaseDate());
                    return movieRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("No such a movie with following ID:" + id));
    }

    public void deleteById(Integer id) {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException(
                    "No such a movie with id: " + id
            );
        }
        movieRepository.deleteById(id);
    }


//    public List<Movie> search(String title, String genre, Sort sort) {
//        if (title != null && !title.isBlank()) {
//            return movieRepository.findByTitleContainingIgnoreCase(title);
//        }
//
//        if (genre != null && !genre.isBlank()) {
//            return movieRepository.findByGenre(genre);
//        }
//
//        return movieRepository.findAll(sort);
//    }

    public Page<Movie> searchPaginated(
            String title,
            String genre,
            int page,
            int size,
            Sort sort
    ) {
        Pageable pageable = PageRequest.of(page, size, sort);

        String safeTitle = (title == null) ? "" : title;
        String safeGenre = (genre == null) ? "" : genre;

        return movieRepository.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
                safeTitle,
                safeGenre,
                pageable
        );
    }
}
