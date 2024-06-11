package Control;

import Model.Movies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MediaLogic {
    public static void categorizeLines(File selectedFile, List<String> moviesList, List<String> tvShowsList) {
        try {
            assert selectedFile != null;
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    //change age and genre here, add director etc
                    String title = values[2];
                    String genre = values[10];
                    if (values[1].equalsIgnoreCase("Movie")) {
                        //eliminate min
                        int durationInMin = Integer.parseInt(values[9]);
                        Movies movie = new Movies(title, genre, director, cast, releaseYear,  ageRating,  description, durationInMin);
                        //methode add movie to list aufrufen
                    } else if (values[1].equalsIgnoreCase("TV Show")) {
                        //Series = new ...
                        //methode add movie to list aufrufen
                    }



                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String[] getSortedShow(Set<String> show) {
        String[] sortedShow = new String[show.size()];
        sortedShow = show.toArray(sortedShow);
        return sortedShow;
    }

    public static String[] getSortedGenre(Set<String> genre) {
        String[] sortedGenre = new String[genre.size()];
        sortedGenre = genre.toArray(sortedGenre);
        Arrays.sort(sortedGenre);
        return sortedGenre;
    }
}
