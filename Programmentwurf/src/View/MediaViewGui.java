package View;

import Control.FavouritesLogic;
import Control.MediaLogic;
import Control.UtilityLogic;
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

    static String[] comboTypes = {"-", "Movies", "Shows"};
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
    private static FavouritesLogic favouritesLogic = new FavouritesLogic();

    public MediaViewGui(MediaLogic mediaLogic) {
        this.mediaLogic = mediaLogic;
    }

    /**
     * creates the media screen adding all necessary elements and linking them to the needed methods
     */

    public static void titleSearch() {
        f = new JFrame("Film & Serien Suche");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JButton searchButton = new JButton("Suchen");
        JButton addFavourites = new JButton("+ Favoriten");
        JButton openFavourites = new JButton("Favoriten öffnen");

        searchButton.addActionListener(s -> mediaLogic.buttonFilter(searchField.getText(), type.getSelectedItem().toString(), genre.getSelectedItem().toString()));

        JLabel search = new JLabel("Suche:");
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


        f.add(searchOptions, BorderLayout.NORTH);
        f.add(infoAndFaves, BorderLayout.SOUTH);


        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(1200, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        tvShowsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /**
             * fills out additional information on the media when a row is selected
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tvShowsTable.getSelectedRow();
                if(row >= 0) {
                    String title = tvShowsTable.getValueAt(row, 0).toString();
                    String[] information = mediaLogic.getInformation(title);
                    descriptionField.setText(information[0]);
                    directorField.setText(information[1]);
                    actorsField.setText(information[2]);
                    countryField.setText(information[3]);
                }
            }
        });

        addFavourites.addActionListener(new ActionListener() {
            /**
             * throws a warning message if the user tries adding the same media item to the favourites list twice
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tvShowsTable.getSelectedRow();
                String title = tvShowsTable.getValueAt(row, 0).toString();
                if (!favouritesLogic.addFavourites(title)) {
                    JOptionPane.showMessageDialog(f, "Bereits in Favoriten");
                }
            }
        });

        openFavourites.addActionListener(new ActionListener() {
            /**
             * opens the favorite screen and hides the media screen
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                favouritesLogic.openFavScreen();
            }
        });




    }

    /**
     * under special circumstances namely when the user decides the favourites screen shall no longer be required in their life, and they wish to see some more media items, and might even think of adding some more to the
     * lists of favourites to be displayed once the favourites screen is reopened then this button shall help making the media screen once again appear if the favourites screen is closed
     */

    public static void setVisibleAgain() {
        f.setVisible(true);
    }



    public static void setLabels(List<Media> mediaTable) {
        String[][] data = UtilityLogic.getTableInformation(mediaTable);
        String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};

        amountResults.setText("Anzahl der Ergebnisse: " + mediaTable.size());
        tvShowsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tableScrollPane.setVisible(true);


    }


}