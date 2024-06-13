package Control;

import Model.Media;
import Model.MediaStorage;
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
                    String genres = null;
                    switch (values[10]) {
                        case "Action & Adventure":
                            genres = "Action & Adventure";
                        case "TV Action & Adventure":
                            genres = values[10].replace("TV Action & Adventure", "Action & Adventure");
                            break;
                        case "Comedies":
                            genres = "Comedies";
                        case "TV Comedies":
                            genres = values[10].replace("TV Comedies", "Comedies");
                        case "Stand-Up Comedy":
                            genres = values[10].replace("Stand-Up Comedy", "Comedies");
                        case "Stand-Up Comedy & Talk Shows":
                            genres = values[10].replace("Stand-Up Comedy & Talk Shows", "Comedies");
                            break;
                        case "Dramas":
                            genres = "Dramas";
                        case "TV Dramas":
                            genres = values[10].replace("TV Dramas", "Dramas");
                            break;
                        case "Horror Movies":
                            genres = values[10].replace("Horror Movies", "Horror");
                        case "TV Horror":
                            genres = values[10].replace("TV Horror", "Horror");
                            break;
                        case "Sci-Fi & Fantasy":
                            genres = "Sci-Fi & Fantasy";
                        case "TV Sci-Fi & Fantasy":
                            genres = values[10].replace("TV Sci-Fi & Fantasy", "Science Fiction");
                            break;
                        case "Thrillers":
                            genres = "Thrillers";
                        case "TV Thrillers":
                            genres = values[10].replace("TV Thrillers", "Thriller");
                            break;
                        case "Children & Family Movies":
                            genres = values[10].replace("Children & Family Movies", "Children & Family");
                        case "Kids' TV":
                            genres = values[10].replace("Kids' TV", "Children & Family");
                            break;
                        case "Romantic Movies":
                            genres = values[10].replace("Romantic Movies", "Romantic");
                        case "Romantic TV Shows":
                            genres = values[10].replace("Romantic TV Shows", "Romantic");
                            break;
                        case "Documentaries":
                            genres = "Documentaries";
                        case "Docuseries":
                            genres = values[10].replace("Docuseries", "Documentaries");
                            break;


                        case "Anime":
                            genres = values[10].replace("Anime", "Animation");
                            break;


                        case "Crime TV Shows":
                            genres = values[10].replace("Crime TV Shows", "Crime");
                            break;

                        case "International TV Shows":
                            genres = values[10].replace("International TV Shows", "International");
                            break;
                        case "Movies":
                            genres = values[10].replace("Movies", "Movie");
                            break;

                        case "Science & Nature TV":
                            genres = values[10].replace("Science & Nature TV", "Science");
                            break;


                        case "TV Mysteries":
                            genres = values[10].replace("TV Mysteries", "Mystery");
                            break;

                        case "TV Shows":
                            genres = values[10].replace("TV Shows", "TV Show");
                            break;
                    }

                    //TODO change age and genre here, add director etc
                    String title = values[2];
                    String genre = genres;
                    String director = values[3];
                    String cast = values[4];
                    int releaseYear = Integer.parseInt(values[7]);
                    String ageRating = age;
                    String description = values[11];
                    if (values[1].equalsIgnoreCase("Movie")) {
                        //TODO eliminate min
                        int durationInMin = Integer.parseInt(values[9]);
                        Movies movie = new Movies(title, genre, director, cast, releaseYear, ageRating, description, durationInMin);
                        MediaStorage.addElementsToList(movie);
                    } else if (values[1].equalsIgnoreCase("TV Show")) {
                        //TODO Series = new ...
                        //TODO methode add movie to list aufrufen
                    }


                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//TODO remove this method
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
