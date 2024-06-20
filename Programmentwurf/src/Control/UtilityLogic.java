package Control;

import Model.Media;
import Model.MediaStorage;
import Model.Movies;
import Model.Series;

import java.util.List;
import java.util.Optional;

public class UtilityLogic {
    public static String[][] getTableInformation(List<Media> mediaTable) {

        String[][] data = new String[mediaTable.size()][6];

        int i = 0;
        for (Media key : mediaTable) {
            data[i][0] = key.getTitle();
            data[i][1] = key.getGenre().toString().replace("[", "").replace("]", "");
            if (key instanceof Movies m) {
                data[i][3] = String.valueOf(m.getDurationInMin()) + " min";
                data[i][2] = "Movie";
            } else {
                Series s = (Series) key;
                data[i][3] = String.valueOf(s.getNumberOfSeasons());
                data[i][2] = "Show";
            }

            data[i][4] = String.valueOf(key.getReleaseYear());
            data[i][5] = key.getAgeRating();

            i++;
        }
        return data;
    }

    public static Media getMediaByName(String title, List<Media> mediaList)    {
        Optional<Media> media = mediaList.stream().filter(f -> f.getTitle().equals(title)).findFirst();
        if(media.isPresent()) {
            return media.get();
        } else {
            return null;
        }
    }
}
