package Control;


import Model.Media;
import Model.MediaStorage;
import Model.Movies;
import Model.Series;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class MediaLogic {
    public static void categorizeLines(File selectedFile) {
        try {
            assert selectedFile != null;
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    String age = null;
                    switch (values[8]) {
                        case "PG-13":
                            age = values[8].replace("PG-13", "FSK12");
                            break;
                        case "TV-MA":
                            age = values[8].replace("TV-MA", "FSK18");
                            break;
                        case "PG":
                            age = values[8].replace("PG", "FSK6");
                            break;
                        case "TV-14":
                            age = values[8].replace("TV-14", "FSK16");
                            break;
                        case "TV-PG":
                            age = values[8].replace("TV-PG", "FSK6");
                            break;
                        case "TV-Y":
                            age = values[8].replace("TV-Y", "FSK0");
                            break;
                        case "TV-Y7":
                            age = values[8].replace("TV-Y7", "FSK6");
                            break;
                        case "R":
                            age = values[8].replace("R", "FSK18");
                            break;
                        case "TV-G":
                            age = values[8].replace("TV-G", "FSK0");
                            break;
                        case "G":
                            age = values[8].replace("G", "FSK0");
                            break;
                        case "NC-17":
                            age = values[8].replace("NC-17", "FSK18");
                            break;
                        case "TV-Y7-FV":
                            age = values[8].replace("TV-Y7-FV", "FSK6");
                            break;
                        case "NR":
                            age = values[8].replace("NR", "FSK18");
                            break;
                        case "":
                            continue;
                    }
                    values[10] = values[10].replaceAll("\\s", "");
                    Set<String> genres = new HashSet<>(List.of(values[10].split(",")));
                    genres = genres.stream().map(f -> switch (f) {

                        case "Action&Adventure", "TVAction&Adventure" -> "Action & Adventure";
                        case "Comedies", "TVComedies", "Stand-UpComedy", "Stand-UpComedy&TalkShows" -> "Comedies";
                        case "Dramas", "TVDramas" -> "Dramas";
                        case "HorrorMovies", "TVHorror" -> "Horror";
                        case "Sci-Fi&Fantasy", "TVSci-Fi&Fantasy" -> "Sci-Fi & Fantasy";
                        case "Thrillers", "TVThrillers" -> "Thrillers";
                        case "Children&Family Movies", "Kids'TV" -> "Children & Family";
                        case "RomanticMovies", "RomanticTVShows" -> "Romantic";
                        case "Documentaries", "Docuseries" -> "Documentaries";
                        default -> "Entertainment";
                    }).collect(Collectors.toSet());

                    String title = values[2];
                    Set<String> genre = genres;
                    String director = values[3];
                    String cast = values[4];
                    String country = values[5];
                    int releaseYear = Integer.parseInt(values[7]);
                    String ageRating = age;
                    String description = values[11];
                    if (values[1].equalsIgnoreCase("Movie")) {
                        int durationInMin = Integer.parseInt(values[9].replace(" min", ""));
                        Movies movie = new Movies(title, genre, director, cast, country, releaseYear, ageRating, description, durationInMin);
                        MediaStorage.addElementsToList(movie);
                    } else if (values[1].equalsIgnoreCase("TV Show")) {
                        String seasons = values[9].replaceAll(" Seasons", "");
                        int numberOfSeasons = Integer.parseInt(seasons.replace(" Season", ""));
                        Series series = new Series(title, genre, director, cast, country, releaseYear, ageRating, description, numberOfSeasons);
                        MediaStorage.addElementsToList(series);
                    }


                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
