package Model;


import java.util.Objects;

public class Series extends Media {
    private int numberOfSeasons;
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    } //define what must be true about seasons

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
