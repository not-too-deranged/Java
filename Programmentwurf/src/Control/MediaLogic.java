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

import static java.util.stream.Collectors.toList;

public class MediaLogic {
    public static void categorizeLines(File selectedFile, List<String> moviesList, List<String> tvShowsList) {
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
                    Set<String> genres = new HashSet<String>(List.of(values[10].split(",")));
                    genres = genres.stream().map(f -> switch (f) {

                            case "Action & Adventure" ->
                                 "Action & Adventure";

                            case "TV Action & Adventure" ->
                                f = values[10].replace("TV Action & Adventure", "Action & Adventure");
                        case "Comedies" -> f = "Comedies";
                        case "TV Comedies" -> f = values[10].replace("TV Comedies", "Comedies");
                        case "Stand-Up Comedy" -> f = values[10].replace("Stand-Up Comedy", "Comedies");
                        case "Stand-Up Comedy & Talk Shows" ->
                                f = values[10].replace("Stand-Up Comedy & Talk Shows", "Comedies");
                        case "Dramas" -> f = "Dramas";
                        case "TV Dramas" -> f = values[10].replace("TV Dramas", "Dramas");
                        case "Horror Movies" -> f = values[10].replace("Horror Movies", "Horror");
                        case "TV Horror" -> f = values[10].replace("TV Horror", "Horror");
                        case "Sci-Fi & Fantasy" -> f = "Sci-Fi & Fantasy";
                        case "TV Sci-Fi & Fantasy" -> f = values[10].replace("TV Sci-Fi & Fantasy", "Science Fiction");
                        case "Thrillers" -> f = "Thrillers";
                        case "TV Thrillers" -> f = values[10].replace("TV Thrillers", "Thriller");
                        case "Children & Family Movies" ->
                                f = values[10].replace("Children & Family Movies", "Children & Family");
                        case "Kids' TV" -> f = values[10].replace("Kids' TV", "Children & Family");
                        case "Romantic Movies" -> f = values[10].replace("Romantic Movies", "Romantic");
                        case "Romantic TV Shows" -> f = values[10].replace("Romantic TV Shows", "Romantic");
                        case "Documentaries" -> f = "Documentaries";
                        case "Docuseries" -> f = values[10].replace("Docuseries", "Documentaries");
                        case "Anime Features" -> f = values[10].replace("Anime Features", "Entertainment");
                        case "Anime Series" -> f = values[10].replace("Anime Series", "Entertainment");
                        case "British TV Shows" -> f = values[10].replace("British TV Shows", "Entertainment");
                        case "Cult Movies" -> f = values[10].replace("Cult Movies", "Entertainment");
                        case "Classic Movies" -> f = values[10].replace("Classic Movies", "Entertainment");
                        case "Classic & Cult TV" -> f = values[10].replace("Classic & Cult TV", "Entertainment");
                        case "Crime TV Shows" -> f = values[10].replace("Crime TV Shows", "Entertainment");
                        case "Faith & Spirituality" -> f = values[10].replace("Faith & Spirituality", "Entertainment");
                        case "Independent Movies" -> f = values[10].replace("Independent Movies", "Entertainment");
                        case "International Movies" -> f = values[10].replace("International Movies", "Entertainment");
                        case "International TV Shows" ->
                                f = values[10].replace("International TV Shows", "Entertainment");
                        case "Korean TV Shows" -> f = values[10].replace("Korean TV Shows", "Entertainment");
                        case "LGBTQ Movies" -> f = values[10].replace("LGBTQ Movies", "Entertainment");
                        case "Movies" -> f = values[10].replace("Movies", "Entertainment");
                        case "Music & Musicals" -> f = values[10].replace("Music & Musicals", "Entertainment");
                        case "Reality TV" -> f = values[10].replace("Reality TV", "Entertainment");
                        case "Science & Nature TV" -> f = values[10].replace("Science & Nature TV", "Entertainment");
                        case "Sports Movies" -> f = values[10].replace("Sports Movies", "Entertainment");
                        case "Spanish-Language TV Shows" ->
                                f = values[10].replace("Spanish-Language TV Shows", "Entertainment");
                        case "Teen TV Shows" -> f = values[10].replace("Teen TV Shows", "Entertainment");
                        case "TV Shows" -> f = values[10].replace("TV Shows", "TV Show");
                        case "TV Mysteries" -> f = values[10].replace("TV Mysteries", "Mystery");


                        default -> "Entertainment";
                    }).collect(Collectors.toSet());

                    String title = values[2];
                    Set<String> genre = genres;
                    String director = values[3];
                    String cast = values[4];
                    int releaseYear = Integer.parseInt(values[7]);
                    String ageRating = age;
                    String description = values[11];
                    if (values[1].equalsIgnoreCase("Movie")) {
                        int durationInMin = Integer.parseInt(values[9].replace(" min", ""));
                        Movies movie = new Movies(title, genre, director, cast, releaseYear, ageRating, description, durationInMin);
                        MediaStorage.addElementsToList(movie);
                        System.out.println("2");
                    } else if (values[1].equalsIgnoreCase("TV Show")) {
                        String seasons = values[9].replaceAll("\\w$", "");
                        int numberOfSeasons = Integer.parseInt(seasons.replace(" Season", ""));
                        Series series = new Series(title, genre, director, cast, releaseYear, ageRating, description, numberOfSeasons);
                        MediaStorage.addElementsToList(series);
                    }


                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
