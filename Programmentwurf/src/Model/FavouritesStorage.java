package Model;

import java.util.ArrayList;
import java.util.List;

public class FavouritesStorage {
    private static List<Media> favouritesList = new ArrayList<>();

    public static List<Media> getFavouritesList(){
        return favouritesList;
    }

    public static void addRating(String title, String comment, String rate) {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();
        Media media = favouritesList.stream().filter(f -> f.getTitle().equals(title)).findFirst().get();
        media.setComment(comment);
        media.setRating(rate);
    }

    public void setFavouritesList(List<Media> favouritesList){
        FavouritesStorage.favouritesList = favouritesList;}

    public static void addElementsToList(Media favouriteItems){
        favouritesList.add(favouriteItems);
    }
}
