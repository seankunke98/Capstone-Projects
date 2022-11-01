package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class Movie {

    @JsonProperty("id")
    private int movie_id;

    private String original_title;

    @JsonProperty("poster_path")
    private String poster;

    private String overview;

    private LocalDate release_date;

    @JsonProperty("genre_ids")
    private int[] genre_ids;

    private Genre[] genres;

    @JsonProperty("vote_average")
    private double rating;

    private boolean isFavorited;

    private boolean isSaved;

    private int runtime;

    private String genre_name;

    public Movie(int movie_id, String original_title, String poster, String overview, LocalDate release_date, double rating, int[] genre_ids) {
        this.movie_id = movie_id;
        this.original_title = original_title;
        this.poster = poster;
        this.overview = overview;
        this.release_date = release_date;
        this.rating = rating;
        this.genre_ids = genre_ids;
    }

    public Movie() {
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String original_title) {
        this.original_title = original_title;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", original_title='" + original_title + '\'' +
                ", poster='" + poster + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date=" + release_date +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                ", rating=" + rating +
                '}';
    }
}
