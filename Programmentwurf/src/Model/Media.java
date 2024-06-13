package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Media {
    private String title;
    private String director;
    private String cast;
    private int releaseYear;
    private String ageRating;
    Set<String> genre = new HashSet<String>();
    private String description;

    public Media(String title, Set<String> genre, String director, String cast, int releaseYear, String ageRating, String description) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.releaseYear = releaseYear;
        this.ageRating = ageRating;
        this.description = description;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
