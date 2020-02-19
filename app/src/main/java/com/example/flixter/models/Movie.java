package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieID;
    String posterPath;
    String backDropPath;
    String title;
    String overview;
    double rating;


    // empty constructor for parcel
    public Movie(){

    }


    Movie(JSONObject jsonObject) throws JSONException {
        backDropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray movieArray) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieArray.length(); i++){
            movies.add(new Movie(movieArray.getJSONObject(i)));
        }
        return movies;
    }

    public int getMovieID() {
        return movieID;
    }

    public double getRating() {
        return rating;
    }

    public String getPosterPath() {
        return String.format( "https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackDropPath() {
        return String.format( "https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
