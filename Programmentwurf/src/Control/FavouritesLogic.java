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
        List<Media> mediaList = MediaStorage.getMediaList();
        List<Media> favouritesList = FavouritesStorage.getFavouritesList();
        Optional<Media> media = mediaList.stream().filter(f -> f.getTitle().equals(title)).findFirst();
        Optional<Media> favouritesMedia = favouritesList.stream().filter(f -> f.getTitle().equals(title)).findFirst();
        if (favouritesMedia.isPresent()) {
            return false;
        } else {
            FavouritesStorage.addElementsToList(media.get());
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
        Optional<Media> media = favouritesList.stream().filter(f -> f.getTitle().equals(valueAt)).findFirst();
        favouritesList.remove(media.get());
        favouritesGui.setLabels(favouritesList);
    }

    public String[] getInformation(String title) {
        List<Media> mediaList = MediaStorage.getMediaList();
        Optional<Media> media = mediaList.stream().filter(f -> f.getTitle().equals(title)).findFirst();
        String[] information = {media.get().getRating(), media.get().getComment()};
        return information;
    }
}
