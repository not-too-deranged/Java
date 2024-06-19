package View;

import Control.FavouritesLogic;
import Control.MediaLogic;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;



public class FavouritesGui extends JFrame {
    static JFrame f;
    static JLabel descriptionField = new JLabel();
    static JLabel directorField = new JLabel();
    static JLabel actorsField = new JLabel();
    static JLabel countryField = new JLabel();
    static JTable tvShowsTable = new JTable();
    static JScrollPane tableScrollPane = new JScrollPane(tvShowsTable);
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
        information.add(information, BorderLayout.CENTER);
        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        tvShowsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MediaLogic mediaLogic = new MediaLogic();
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
}
