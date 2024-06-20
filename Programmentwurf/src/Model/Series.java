package Model;


import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Series extends Media {
    private int numberOfSeasons;

    public Series(String title, Set<String> genre, String director, String cast, String country, int releaseYear, String ageRating, String description, int numberOfSeasons) {
        super(title, genre, director, cast, country, releaseYear, ageRating, description);
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    @Override
    public String toString() {
        return super.toString() + "numberOfSeasons = " + numberOfSeasons;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!Objects.equals(this.getTitle(), ((Series) obj).getTitle())) {
            return false;
        }
        Series series = (Series) obj;
        if(numberOfSeasons != series.getNumberOfSeasons())     {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeasons);
    }
}
