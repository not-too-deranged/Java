package Model;

import java.util.ArrayList;
import java.util.List;

public class MediaStorage {
   private static List<Media> mediaList = new ArrayList<>();

   public List<Media> getMediaList(){
       return mediaList;
   }
    public void setMediaList(List<Media> mediaList){
        MediaStorage.mediaList = mediaList;}

public static void addElementsToList(Media mediaItems){
       mediaList.add(mediaItems);
}

}
