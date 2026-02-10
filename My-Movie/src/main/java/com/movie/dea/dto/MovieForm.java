package com.movie.dea.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


public class MovieForm { // for UI

    private Integer id;

    @NotBlank(message = "Title is required!")
    @Size(min = 5, max = 100, message = "Title must be 5-100 chars")
    private String title;
    @NotBlank(message = "Genre is required!")
    @Size(min = 5, max = 100, message = "Genre must be 5-100 chars")
    private String genre;

    @NotNull(message = "dont leave empty")

    private LocalDate releaseDate;
    @NotNull(message = "dont leave empty")
    @DecimalMin(value = "1.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must be at most 10")
    private Double rating;
    @NotBlank(message = "Duration is required!")
    @Size(min = 2, max = 3, message = "Duration must be 2-3 chars") // 1000
    private String duration;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
