package Control;

import Model.FavouritesStorage;
import Model.Media;
import Model.MediaStorage;
import View.FavouritesGui;
import View.MediaViewGui;

import java.util.List;
import java.util.Optional;

public class FavouritesLogic {
private FavouritesGui favouritesGui = new FavouritesGui(this);

    /**
     * tests if a media item is already saved in favourites and if not ads it
     * @param title is used to look for the matching media data
     * @return boolean shows whether item was already added
     */

    public boolean addFavourites(String title) {
        Media media = UtilityLogic.getMediaByName(title, MediaStorage.getMediaList());
        Media favouritesMedia = UtilityLogic.getMediaByName(title, FavouritesStorage.getFavouritesList());
        if (favouritesMedia != null) {
            return false;
        } else {
            FavouritesStorage.addElementsToList(media);
            return true;
        }
    }

    /**
     * opens favourites Screen
     */
    public void openFavScreen() {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();

        favouritesGui.favouritesScreen();
        favouritesGui.setLabels(favouritesList);
    }

    /**
     * makes first Media Window visible again after closing the favourite screen
     */

    public void setMediaWindow()    {
        MediaViewGui.setVisibleAgain();
    }

    /**
     * removes selected media item from favourites List
     * @param title used to find value in List
     */

    public void removeElement(String title) {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();
        Media media = UtilityLogic.getMediaByName(title, favouritesList);
        favouritesList.remove(media);
        favouritesGui.setLabels(favouritesList);
    }

    /**
     * method used to get additional information about the rating and comment section on the favourite screen
     * @param title used as link value in List
     * @return information about rating and comment
     */

    public String[] getInformation(String title) {
        Media media = UtilityLogic.getMediaByName(title, MediaStorage.getMediaList());
        String[] information = {media.getRating(), media.getComment()};
        return information;
    }
}
