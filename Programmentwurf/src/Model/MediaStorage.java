package Model;

import java.util.ArrayList;
import java.util.List;

public class MediaStorage {
   private List<Media> mediaList = new ArrayList<>();

   public List<Media> getMediaList(){
       return mediaList;
   }
    public void setMediaList(List<Media> mediaList){
        this.mediaList = mediaList;}


   // methode add element to media list mediaList.add(media);
}
