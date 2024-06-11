package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class MediaViewGui extends JFrame {
    static JFrame f;
    HashMap<String, List<String>> records;
    static String[] comboTypes ={"TV Shows", "Movies", "--"};
    static String[] comboGenre ={"Action & Adventure", "Comedies", "Horror", "Sci-Fi & Fantasy", "Thrillers", "Children & Family", "Romantic", "Documentaries", "Unterhaltung"};

    static JComboBox<String> type = new JComboBox<>(comboTypes);
    static JComboBox<String> search = new JComboBox<>();
    static JLabel descrpitionField = new JLabel();
    static JLabel directorField = new JLabel();
    static JLabel actorsField = new JLabel();
    static JLabel countryField = new JLabel();
    static JTable tvShowsTable = new JTable();
    static JScrollPane tableScrollPane = new JScrollPane(tvShowsTable);

    public static void titelSearch() {
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

        p.add(suche);
        p.add(description);
        p.add(descrpitionField);
        p.add(director);
        p.add(directorField);
        p.add(actors);
        p.add(actorsField);
        p.add(country);
        p.add(countryField);
        q.add(addFavourites);
        q.add(openFavourites);

        f.setVisible(true);
        f.add(type, BorderLayout.NORTH);
        f.add(p, BorderLayout.CENTER);
        f.add(q, BorderLayout.SOUTH);

        // Add the table scroll pane to the frame, initially hidden
        tableScrollPane.setVisible(false);
        f.add(tableScrollPane, BorderLayout.EAST);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }



    public void setLabels(String media) {
        if (media.equals("TV Show")) {
            String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};
            String[][] data = new String[records.size()][6];

            int i = 0;
            for (String key : records.keySet()) {
                List<String> mediaInfo = records.get(key);
                data[i][0] = mediaInfo.get(2);
                data[i][1] = mediaInfo.get(10);
                data[i][2] = mediaInfo.get(1);
                data[i][3] = mediaInfo.get(9);
                data[i][4] = mediaInfo.get(7);
                data[i][5] = mediaInfo.get(8);
                descrpitionField.setText(mediaInfo.get(11));
                directorField.setText(mediaInfo.get(3));
                actorsField.setText(mediaInfo.get(4));
                countryField.setText(mediaInfo.get(5));
                i++;
            }

            tvShowsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            tableScrollPane.setVisible(true);

        } else {
            tableScrollPane.setVisible(false);
            List<String> mediaInfo = records.get(media);
            descrpitionField.setText(mediaInfo.get(0));
            directorField.setText(mediaInfo.get(1));
            actorsField.setText(mediaInfo.get(2));
            countryField.setText(mediaInfo.get(3));
        }
    }}