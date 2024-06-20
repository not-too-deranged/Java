package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Media implements Comparable<Media>{
    private String title;
    public String getTitle(){ return title;}
    private String director;
    public String getDirector() {
        return director;
    }
    private String cast;
    private String comment;
    private String rating;
    public String getCast(){
        return cast;
    }
    private String country;
    public String getCountry(){
        return country;
    }
    private int releaseYear;
    public int getReleaseYear(){
        return releaseYear;
    }
    private String ageRating;
    public String getAgeRating(){
        return ageRating;
    }
    Set<String> genre = new HashSet<String>();
    public Set<String> getGenre(){
        return genre;
    }
    private String description;
    public String getDescription(){
        return description;
    }
    public String getComment() {
        return comment;
    }
    public String getRating() {
        return rating;
    }

    public int compareTo(Media data){
        return data.releaseYear - this.releaseYear;
    }

    public Media(String title, Set<String> genre, String director, String cast, String country, int releaseYear, String ageRating, String description) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.releaseYear = releaseYear;
        this.ageRating = ageRating;
        this.description = description;

    }

    @Override
    public String toString() {

        return "Title: " + title + "\n" + "Genre: " + genre + "\n" + "Director: " + director + "\n" + "Cast: " + cast + "\n" + "Country: " + country + "Release Year: " + releaseYear + "Age Rating: " + ageRating + "\n" + "Description: " + description + "\n";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(String rate) {
        this.rating = rate;
    }
}
