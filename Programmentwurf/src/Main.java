import Control.DataLogic;
import Control.MediaLogic;
import Model.MediaStorage;
import View.MediaViewGui;


import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import static Control.MediaLogic.*;

public class Main {
    static MediaLogic mediaLogic = new MediaLogic();
    private static final MediaViewGui gui = new MediaViewGui(mediaLogic);

    public static void main(String[] args) {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        File selectedFile = null;

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
        }
        DataLogic.categorizeLines( selectedFile);
        mediaLogic.sortData(MediaStorage.getMediaList());

        MediaViewGui searchGui = new MediaViewGui(mediaLogic);
        searchGui.setLabels(MediaStorage.getMediaList());
        MediaViewGui.titleSearch();






    }




}
