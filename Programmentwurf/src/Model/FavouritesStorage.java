package Model;

import java.util.ArrayList;
import java.util.List;

public class FavouritesStorage {
    private static List<Media> favouritesList = new ArrayList<>();


    public static List<Media> getFavouritesList(){
        return favouritesList;
    }

    /**
     * links a rating and a comment to a certain media item
     * @param title the media title which the media is selected by
     * @param comment adds the input from the comment text field
     * @param rate adds the input from the rating combobox
     */
    public static void addRating(String title, String comment, String rate) {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();
        Media media = favouritesList.stream().filter(f -> f.getTitle().equals(title)).findFirst().get();
        media.setComment(comment);
        media.setRating(rate);
    }

    public void setFavouritesList(List<Media> favouritesList){
        FavouritesStorage.favouritesList = favouritesList;}

    /**
     * adds media items to favourites list
     * @param favouriteItems media item to be added to list
     */

    public static void addElementsToList(Media favouriteItems){
        favouritesList.add(favouriteItems);
    }
}
