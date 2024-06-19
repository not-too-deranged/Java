package View;

import Control.FavouritesLogic;
import Control.MediaLogic;
import Model.FavouritesStorage;
import Model.Media;
import Model.Movies;
import Model.Series;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;


public class FavouritesGui extends JFrame {
    static JFrame f;
    static JLabel descriptionField = new JLabel();
    static JLabel directorField = new JLabel();
    static JLabel actorsField = new JLabel();
    static JLabel countryField = new JLabel();
    static JTable favouritesTable = new JTable();
    static JScrollPane tableScrollPane = new JScrollPane(favouritesTable);
    private static FavouritesLogic favouritesLogic;

    public FavouritesGui(FavouritesLogic favouritesLogic) {
        this.favouritesLogic = favouritesLogic;
    }

    public static void favouritesScreen() {
        f = new JFrame("Favoriten Screen");
        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        JLabel faves = new JLabel("Favoriten");
        f.add(faves, BorderLayout.NORTH);
        JLabel description = new JLabel("Beschreibung:");
        JLabel director = new JLabel("Regisseur:");
        JLabel actors = new JLabel("Schauspieler:");
        JLabel country = new JLabel("Produktionsland:");

        JPanel information = new JPanel(new GridLayout(8, 1));
        information.add(description);
        information.add(descriptionField);
        information.add(director);
        information.add(directorField);
        information.add(actors);
        information.add(actorsField);
        information.add(country);
        information.add(countryField);
        f.add(information, BorderLayout.CENTER);
        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        favouritesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MediaLogic mediaLogic = new MediaLogic();
                int row = favouritesTable.getSelectedRow();
                String title = favouritesTable.getValueAt(row, 0).toString();
                String[] information = mediaLogic.getInformation(title);
                descriptionField.setText(information[0]);
                directorField.setText(information[1]);
                actorsField.setText(information[2]);
                countryField.setText(information[3]);
            }
        });
        MediaViewGui.setLabels(FavouritesStorage.getFavouritesList());
    }

    public static void setLabels(List<Media> mediaTable) {
        String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};
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
        
       favouritesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tableScrollPane.setVisible(true);


    }
}
