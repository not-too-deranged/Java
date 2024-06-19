package Model;

import java.util.ArrayList;
import java.util.List;

public class FavouritesStorage {
    private static List<Media> favouritesList = new ArrayList<>();

    public static List<Media> getFavouritesList(){
        return favouritesList;
    }
    public void setFavouritesList(List<Media> favouritesList){
        FavouritesStorage.favouritesList = favouritesList;}

    public static void addElementsToList(Media favouriteItems){
        favouritesList.add(favouriteItems);
    }
}
