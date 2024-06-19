package Control;

import Model.FavouritesStorage;
import Model.Media;
import Model.MediaStorage;
import View.FavouritesGui;

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


}
