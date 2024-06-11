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

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
        }

        List<String> moviesList = new ArrayList<>();
        List<String> tvShowsList = new ArrayList<>();

        categorizeLines(selectedFile, moviesList, tvShowsList);
        System.out.println(tvShowsList.get(1).title);



    }




}
