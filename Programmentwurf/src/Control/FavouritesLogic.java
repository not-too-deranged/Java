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

    public boolean addFavourites(String title) {
        Media media = UtilityLogic.getMediaByName(title, MediaStorage.getMediaList());
        Media favouritesMedia = UtilityLogic.getMediaByName(title, FavouritesStorage.getFavouritesList());
        if (favouritesMedia == null) {
            return false;
        } else {
            FavouritesStorage.addElementsToList(media);
            return true;
        }
    }
    public void openFavScreen() {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();

        favouritesGui.favouritesScreen();
        favouritesGui.setLabels(favouritesList);
    }

    public void setMediaWindow()    {
        MediaViewGui.setVisibleAgain();
    }

    public void removeElement(String valueAt) {
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();
        Media media = UtilityLogic.getMediaByName(valueAt, favouritesList);
        favouritesList.remove(media);
        favouritesGui.setLabels(favouritesList);
    }

    public String[] getInformation(String title) {
        Media media = UtilityLogic.getMediaByName(title, MediaStorage.getMediaList());
        String[] information = {media.getRating(), media.getComment()};
        return information;
    }
}
