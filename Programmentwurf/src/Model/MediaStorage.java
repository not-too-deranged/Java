package Model;

import java.util.ArrayList;
import java.util.List;

public class MediaStorage {
   private static List<Media> mediaList = new ArrayList<>();

   public static List<Media> getMediaList(){
       return mediaList;
   }
    public static void setMediaList(List<Media> mediaList){
        MediaStorage.mediaList = mediaList;}

    /**
     * adds media items to list
     * @param mediaItems media items
     */

public static void addElementsToList(Media mediaItems){
       mediaList.add(mediaItems);
}

}
