package View;

import Control.MediaLogic;
import Model.Media;
import Model.Movies;
import Model.Series;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class MediaViewGui extends JFrame {
    static JFrame f;

    static String[] comboTypes = {"-", "Movies", "TV Shows"};
    static String[] comboGenre = {"-", "Action & Adventure", "Children & Family", "Comedies", "Horror", "Documentaries", "Entertainment", "Romantic", "Sci-Fi & Fantasy", "Thrillers"};

    static JComboBox<String> type = new JComboBox<>(comboTypes);
    static JComboBox<String> genre = new JComboBox<>(comboGenre);
    static JTextField searchField = new JTextField();
    static JLabel amountResults = new JLabel();
    static JLabel descriptionField = new JLabel();
    static JLabel directorField = new JLabel();
    static JLabel actorsField = new JLabel();
    static JLabel countryField = new JLabel();
    static JTable tvShowsTable = new JTable();
    static JScrollPane tableScrollPane = new JScrollPane(tvShowsTable);
    private static MediaLogic mediaLogic;

    public MediaViewGui(MediaLogic mediaLogic)  {
        this.mediaLogic = mediaLogic;
    }

    public static void titleSearch() {
        f = new JFrame("Film & Serien Suche");

        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JButton searchButton = new JButton("Suchen");
        JButton addFavourites = new JButton("+ Favoriten");
        JButton openFavourites = new JButton("Favoriten öffnen");

        JLabel search = new JLabel("Suche");
        JLabel description = new JLabel("Beschreibung:");
        JLabel director = new JLabel("Regisseur:");
        JLabel actors = new JLabel("Schauspieler:");
        JLabel country = new JLabel("Produktionsland:");

        JPanel information = new JPanel(new GridLayout(8, 1));
        JPanel favourites = new JPanel(new GridLayout(1, 2));
        JPanel infoAndFaves = new JPanel(new BorderLayout());
        JPanel searchOptions = new JPanel(new GridLayout(2, 5));

        information.add(description);
        information.add(descriptionField);
        information.add(director);
        information.add(directorField);
        information.add(actors);
        information.add(actorsField);
        information.add(country);
        information.add(countryField);
        favourites.add(addFavourites);
        favourites.add(openFavourites);
        searchOptions.add(search);
        searchOptions.add(searchField);
        searchOptions.add(type);
        searchOptions.add(genre);
        searchOptions.add(searchButton);
        searchOptions.add(amountResults);

        infoAndFaves.add(information, BorderLayout.CENTER);
        infoAndFaves.add(favourites, BorderLayout.NORTH);

        f.setVisible(true);
        f.add(searchOptions, BorderLayout.NORTH);
        f.add(infoAndFaves, BorderLayout.SOUTH);


        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        tvShowsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tvShowsTable.getSelectedRow();
                String title = tvShowsTable.getValueAt(row, 0).toString();
                String[] information = mediaLogic.getInformation(title);
                descriptionField.setText(information[0]);
                directorField.setText(information[1]);
                actorsField.setText(information[2]);
                countryField.setText(information[3]);
            }
        });
    }

    public static JTable getTvShowsTable() {
        return tvShowsTable;
    }


    public void setLabels(List<Media> mediaTable) {
        String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};
        String[][] data = new String[mediaTable.size()][6];

        int i = 0;
        for (Media key : mediaTable) {
            data[i][0] = key.getTitle();
            data[i][1] = key.getGenre().toString();
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

        amountResults.setText("Anzahl der Ergebnisse: " + mediaTable.size());
        tvShowsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tableScrollPane.setVisible(true);


    }
}