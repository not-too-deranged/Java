package Control;


import Model.Media;
import Model.MediaStorage;
import Model.Movies;
import Model.Series;
import View.MediaViewGui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class MediaLogic {

    public void sortData(List<Media> mediaData) {
        Collections.sort(mediaData);
    }

    public String[] getInformation(String userInput) {
        List<Media> mediaList = MediaStorage.getMediaList();
        Optional<Media> media = mediaList.stream().filter(f -> f.getTitle().equals(userInput)).findFirst();
        String[] information = {media.get().getDescription(), media.get().getDirector(), media.get().getCast(), media.get().getCountry()};
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

    public void buttonFilter(String userInput, String selectedType, String selectedGenre) {
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

    }

}
