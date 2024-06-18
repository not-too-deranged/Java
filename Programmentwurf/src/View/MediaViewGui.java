package View;

import Model.Media;
import Model.Movies;
import Model.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class MediaViewGui extends JFrame {
    static JFrame f;

    static String[] comboTypes = {"-", "TV Shows", "Movies"};
    static String[] comboGenre = {"-", "Action & Adventure", "Comedies", "Horror", "Sci-Fi & Fantasy", "Thrillers", "Children & Family", "Romantic", "Documentaries", "Entertainment"};

    static JComboBox<String> type = new JComboBox<>(comboTypes);
    static JComboBox<String> genre = new JComboBox<>(comboGenre);
    static JComboBox<String> search = new JComboBox<>();
    static JLabel descriptionField = new JLabel();
    static JLabel directorField = new JLabel();
    static JLabel actorsField = new JLabel();
    static JLabel countryField = new JLabel();
    static JTable tvShowsTable = new JTable();
    static JScrollPane tableScrollPane = new JScrollPane(tvShowsTable);

    public static void titleSearch() {
        f = new JFrame("Film & Serien Suche");

        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JButton search = new JButton("Suchen");
        JButton addFavourites = new JButton("+ Favoriten");
        JButton openFavourites = new JButton("Favoriten öffnen");

        JLabel suche = new JLabel("Suche");
        JLabel description = new JLabel("Beschreibung:");
        JLabel director = new JLabel("Regisseur");
        JLabel actors = new JLabel("Schauspieler");
        JLabel country = new JLabel("Produktionsland");

        JPanel p = new JPanel(new GridLayout(8, 1));
        JPanel q = new JPanel(new GridLayout(1, 2));
        JPanel pq = new JPanel(new BorderLayout());

        p.add(description);
        p.add(descriptionField);
        p.add(director);
        p.add(directorField);
        p.add(actors);
        p.add(actorsField);
        p.add(country);
        p.add(countryField);
        q.add(addFavourites);
        q.add(openFavourites);

        pq.add(p, BorderLayout.CENTER);
        pq.add(q, BorderLayout.NORTH);

        f.setVisible(true);
        f.add(genre, BorderLayout.NORTH);
        f.add(type, BorderLayout.NORTH);
        f.add(pq, BorderLayout.SOUTH);


        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    public void setLabels(List<Media> mediaTable) {
        String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};
        String[][] data = new String[mediaTable.size()][6];

        int i = 0;
        for (Media key : mediaTable) {
            data[i][0] = key.getTitle();
            data[i][1] = key.getGenre().toString();
            if (key instanceof Movies m) {
                data[i][2] = String.valueOf(m.getDurationInMin());
                data[i][3] = "Movie";
            } else {
                Series s = (Series) key;
                data[i][2] = String.valueOf(s.getNumberOfSeasons());
                data[i][3] = "Show";
            }

            data[i][4] = String.valueOf(key.getReleaseYear());
            data[i][5] = key.getAgeRating();
            descriptionField.setText(key.getDescription());
            directorField.setText(key.getDirector());
            actorsField.setText(key.getCast());
            countryField.setText(key.getCountry());
            i++;
        }

        tvShowsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tableScrollPane.setVisible(true);


    }
}