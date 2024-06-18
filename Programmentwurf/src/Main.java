import Control.MediaLogic;
import Model.MediaStorage;
import View.MediaViewGui;


import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import static Control.MediaLogic.*;

public class Main {
    private static final MediaViewGui gui = new MediaViewGui();

    public static void main(String[] args) {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        File selectedFile = null;

        MediaLogic mediaLogic = new MediaLogic();

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
        }
        mediaLogic.categorizeLines( selectedFile);
        mediaLogic.sortData(MediaStorage.getMediaList());

        MediaViewGui searchGui = new MediaViewGui();
        searchGui.setLabels(MediaStorage.getMediaList());
        MediaViewGui.titleSearch();






    }




}
