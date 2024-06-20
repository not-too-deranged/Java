package Control;


import Model.Media;
import Model.MediaStorage;
import View.MediaViewGui;

import java.util.*;


public class MediaLogic {

    public void sortData(List<Media> mediaData) {
        Collections.sort(mediaData);
    }

    public String[] getInformation(String userInput) {
        Media media = UtilityLogic.getMediaByName(userInput, MediaStorage.getMediaList());
        String[] information = {media.getDescription(), media.getDirector(), media.getCast(), media.getCountry()};
        return information;
    }

    public List<Media> getSearchResults(String userInput, List<Media> mediaList) {
        String finalUserInput = userInput.toLowerCase();
        List<Media> media = mediaList.stream().filter(f -> f.getTitle().toLowerCase().contains(finalUserInput) || f.getDirector().toLowerCase().contains(finalUserInput) || f.getCast().toLowerCase().contains(finalUserInput) || f.getDescription().toLowerCase().contains(finalUserInput)).toList();
        return media;
    }

    public List<Media> getTypeResults(String selectedType, List<Media> mediaList) {
        if (selectedType.equals("Shows")) {
            selectedType = "Series";
        }
        String finalSelectedType = selectedType;
        List<Media> media = mediaList.stream().filter(f -> f.getClass().getSimpleName().equals(finalSelectedType)).toList();
        return media;
    }

    public List<Media> getGenreResults(String selectedGenre, List<Media> mediaList) {
        List<Media> media = mediaList.stream().filter(f -> f.getGenre().contains(selectedGenre)).toList();
        return media;
    }

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
