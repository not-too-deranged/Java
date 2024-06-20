package Control;


import Model.Media;
import Model.MediaStorage;
import View.MediaViewGui;

import java.util.*;


public class MediaLogic {
    /**
     * sorts media by release year
     * @param mediaData the data that gets sorted
     */
    public void sortData(List<Media> mediaData) {
        Collections.sort(mediaData);
    }

    /**
     * method used to acquire the additional information of a media item
     * @param userInput used to determine which media item they want the info on
     * @return information
     */
    public String[] getInformation(String userInput) {
        Media media = UtilityLogic.getMediaByName(userInput, MediaStorage.getMediaList());
        String[] information = {media.getDescription(), media.getDirector(), media.getCast(), media.getCountry()};
        return information;
    }

    /**
     * method to match the text input from the searchbar from the media screen to data from the media
     * @param userInput input from the searchbar
     * @param mediaList media data that gets matched with the userInput
     * @return
     */

    public List<Media> getSearchResults(String userInput, List<Media> mediaList) {
        String finalUserInput = userInput.toLowerCase();
        List<Media> media = mediaList.stream().filter(f -> f.getTitle().toLowerCase().contains(finalUserInput) || f.getDirector().toLowerCase().contains(finalUserInput) || f.getCast().toLowerCase().contains(finalUserInput) || f.getDescription().toLowerCase().contains(finalUserInput)).toList();
        return media;
    }

    /**
     * method to sort the media results by type on the media screen
     * @param selectedType the type that the user inputs in the type combobox
     * @param mediaList data that gets sorted
     * @return only the media of the selected type
     */

    public List<Media> getTypeResults(String selectedType, List<Media> mediaList) {
        if (selectedType.equals("Shows")) {
            selectedType = "Series";
        }
        String finalSelectedType = selectedType;
        List<Media> media = mediaList.stream().filter(f -> f.getClass().getSimpleName().equals(finalSelectedType)).toList();
        return media;
    }

    /**
     * method to sort the media results by genre on the media screen
     * @param selectedGenre genre that the user selects on the genre combobox
     * @param mediaList data that gets sorted
     * @return media that matches the input
     */

    public List<Media> getGenreResults(String selectedGenre, List<Media> mediaList) {
        List<Media> media = mediaList.stream().filter(f -> f.getGenre().contains(selectedGenre)).toList();
        return media;
    }

    /**
     * method to call the search function linking the previous methods if they have been selected
     * @param userInput input from the searchbar
     * @param selectedType input from the type combobox
     * @param selectedGenre input from the genre combobox
     * @return media that matches input
     */

    public List<Media> buttonFilter(String userInput, String selectedType, String selectedGenre) {
        List<Media> mediaList = MediaStorage.getMediaList();
        if (!selectedType.equals("-")) {
            mediaList = getTypeResults(selectedType, mediaList);
        }
        if (!selectedGenre.equals("-")) {
            mediaList = getGenreResults(selectedGenre, mediaList);
        }
        if (!userInput.equals("")) {
            mediaList = getSearchResults(userInput, mediaList);
        }
        MediaViewGui.setLabels(mediaList);
        return mediaList;

    }

}
