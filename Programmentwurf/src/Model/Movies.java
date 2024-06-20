package Model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Movies extends Media {
    private int durationInMin;
    public int getDurationInMin() {
        return durationInMin;
    }
    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }

    @Override
    public String toString() {
        return super.toString() + "durationInMin = " + durationInMin;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!Objects.equals(this.getTitle(), ((Movies) obj).getTitle())) {
            return false;
        }
        Movies movies = (Movies) obj;
        if(durationInMin != movies.durationInMin)     {
            return false;
        }
            return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(durationInMin);
    }
    /**
     * initialised all parameters of movies
     */
    public Movies(String title, Set<String> genre, String director, String cast, String country, int releaseYear, String ageRating, String description, int durationInMin) {
        super(title, genre, director, cast, country, releaseYear, ageRating, description);
        this.durationInMin = durationInMin;

    }
}
