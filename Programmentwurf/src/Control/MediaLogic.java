package Control;

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
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    if (values[1].equalsIgnoreCase("Movie")) {
                        moviesList.add(line); //constructor to make it part of class movie
                    } else if (values[1].equalsIgnoreCase("TV Show")) {
                        tvShowsList.add(line);
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
