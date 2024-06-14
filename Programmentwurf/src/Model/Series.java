package Model;


import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Series extends Media {
    private int numberOfSeasons;

    public Series(String title, Set<String> genre, String director, String cast, int releaseYear, String ageRating, String description, int numberOfSeasons) {
        super(title, genre, director, cast, releaseYear, ageRating, description);
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    } //TODO define what must be true about seasons

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeasons);
    }
}
