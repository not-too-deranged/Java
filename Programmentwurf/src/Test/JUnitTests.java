package Test;

import Control.MediaLogic;
import Model.Media;
import java.util.*;
import Model.MediaStorage;
import Model.Movies;
import Model.Series;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTests {
    List<Media> mediaData = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Set<String> set = new HashSet<>();
        set.add("Romantic");
        set.add("Horror");
        set.add("Dramas");

        Media mediaSecond = new Series("testing", set, "Empty", "Empty", "Empty", 2020, "Empty", "Empty", 100);
        Media mediaFirst = new Movies("testing 2", Collections.singleton("Horror"), "Empty", "Empty", "Empty", 2024, "Empty", "Empty", 1);
        Media mediaLast = new Movies("not Matching", Collections.singleton("Romantic"), "Empty", "Empty", "Empty", 2018, "Empty", "Empty", 1);
        Media mediaThird = new Series("Empty", Collections.singleton("Dramas"), "Empty", "Empty", "Empty", 2020, "Empty", "Empty", 100);
        mediaData.add(mediaSecond);
        mediaData.add(mediaFirst);
        mediaData.add(mediaLast);
        mediaData.add(mediaThird);
    }
    @Test
    public void testSortData()  {
        Collections.sort(mediaData);
        assertTrue(mediaData.get(0).getReleaseYear() == 2024 && mediaData.get(1).getReleaseYear() == 2020 && mediaData.get(2).getReleaseYear() == 2020 && mediaData.get(3).getReleaseYear() == 2018);
    }

    @Test
    public void testFilter()    {
        MediaLogic mediaLogic = new MediaLogic();
        MediaStorage.setMediaList(mediaData);

        List<Media> test1 = mediaLogic.buttonFilter("testing", "-", "-");
        List<Media> test2 = mediaLogic.buttonFilter("", "Movies", "-");
        List<Media> test3 = mediaLogic.buttonFilter("", "-", "Romantic");
        List<Media> test4 = mediaLogic.buttonFilter("testing", "-", "Horror");
        List<Media> test5 = mediaLogic.buttonFilter("", "Shows", "Dramas");
        List<Media> test6 = mediaLogic.buttonFilter("not Matching", "Movies", "Horror");
        assertTrue(test1.get(0).getTitle().contains("testing") && test1.get(1).getTitle().contains("testing") && test1.size() == 2);
        assertTrue(test2.get(0).getTitle().equals("testing 2") && test2.get(1).getTitle().equals("not Matching") && test2.size() == 2);
        assertTrue(test3.get(0).getTitle().equals("testing") && test3.get(1).getTitle().equals("not Matching") && test3.size() == 2);
        assertTrue(test4.get(0).getTitle().equals("testing") && test4.get(1).getTitle().equals("testing 2") && test4.size() == 2);
        assertTrue(test5.get(0).getTitle().equals("testing") && test5.get(1).getTitle().equals("Empty") && test5.size() == 2);
        assertTrue(test6.isEmpty());
    }
}
