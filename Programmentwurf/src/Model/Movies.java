package Model;

import java.util.Objects;

public class Movies extends Media {
    private int durationInMin;
    public int getDurationInMin() {
        return durationInMin;
    }
    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }

    @Override //when questioned in cmd
    public String toString() {
        return super.toString();
    }

    @Override //when same movies
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(durationInMin);
    }
}
