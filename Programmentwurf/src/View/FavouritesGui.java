package View;

import Control.FavouritesLogic;
import Control.MediaLogic;
import Control.UtilityLogic;
import Control.StatisticLogic;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private static JComboBox<String> rating;


    public FavouritesGui(FavouritesLogic favouritesLogic) {
        this.favouritesLogic = favouritesLogic;
    }

    /**
     * creates the favourite screen adding all necessary elements and linking them to the needed methods
     */

    public static void favouritesScreen() {
        f = new JFrame("Favoriten Screen");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        JLabel faves = new JLabel("Favoriten");

        JLabel comment = new JLabel("Kommentar");
        JTextField commentField = new JTextField();
        String[] comboRating = {"1 Stern", "2 Sterne", "3 Sterne", "4 Sterne", "5 Sterne"};
        JButton save = new JButton("Speichern");
        JButton delete = new JButton("Entfernen");
        JLabel description = new JLabel("Beschreibung:");
        JLabel director = new JLabel("Regisseur:");
        JLabel actors = new JLabel("Schauspieler:");
        JLabel country = new JLabel("Produktionsland:");
        rating = new JComboBox<>(comboRating);
        JButton statistic = new JButton("Statistik anzeigen");

        JPanel information = new JPanel(new GridLayout(8, 1));
        information.add(description);
        information.add(descriptionField);
        information.add(director);
        information.add(directorField);
        information.add(actors);
        information.add(actorsField);
        information.add(country);
        information.add(countryField);

        JPanel functions = new JPanel(new GridLayout(1,5));
        functions.add(comment);
        functions.add(commentField);
        functions.add(rating);
        functions.add(save);
        functions.add(delete);

        JPanel favesAndFunctions = new JPanel(new BorderLayout());
        favesAndFunctions.add(functions, BorderLayout.NORTH);
        favesAndFunctions.add(information, BorderLayout.SOUTH);
        JPanel favesAndFunctionsAndStats = new JPanel(new BorderLayout());
        favesAndFunctionsAndStats.add(favesAndFunctions, BorderLayout.CENTER);
        favesAndFunctionsAndStats.add(statistic, BorderLayout.SOUTH);
        f.add(faves, BorderLayout.NORTH);
        f.add(favesAndFunctionsAndStats, BorderLayout.SOUTH);
        f.add(tableScrollPane, BorderLayout.CENTER);


        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        favouritesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /**
             * fills out additional information on the media when a row is selected
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MediaLogic mediaLogic = new MediaLogic();
                int row = favouritesTable.getSelectedRow();
                if(row >= 0)    {
                    String title = favouritesTable.getValueAt(row, 0).toString();
                    String[] information = mediaLogic.getInformation(title);
                    descriptionField.setText(information[0]);
                    directorField.setText(information[1]);
                    actorsField.setText(information[2]);
                    countryField.setText(information[3]);
                    String[] favouritesInformation = favouritesLogic.getInformation(title);
                    if(favouritesInformation[0] != null)    {
                        rating.setSelectedItem(favouritesInformation[0]);
                        commentField.setText(favouritesInformation[1]);
                    } else {
                        rating.setSelectedItem("1 Stern");
                        commentField.setText("");
                    }
                }
            }
        });
        MediaViewGui.setLabels(FavouritesStorage.getFavouritesList());

        f.addWindowListener(new WindowAdapter(){
            /**
             * closes the initial media screen once the favourites screen gets opened
             * @param e the event to be processed
             */
            public void windowClosing(WindowEvent e){
                f.dispose();
                favouritesLogic.setMediaWindow();
                MediaViewGui.setLabels(MediaStorage.getMediaList());
            }
        });

        delete.addActionListener(new ActionListener() {
            /**
             * removes the selected media items from the favourites list
             * @param ae the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                favouritesLogic.removeElement((String) favouritesTable.getValueAt(favouritesTable.getSelectedRow(), 0));
            }
        });

        save.addActionListener(new ActionListener() {
            /**
             * adds the selected media from media screen to the favourites list
             * @param ae the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = favouritesTable.getSelectedRow();
                String title = favouritesTable.getValueAt(row, 0).toString();
                String comment = commentField.getText();
                String rate = (String) rating.getSelectedItem();
                FavouritesStorage.addRating(title, comment, rate);
            }
        });
        statistic.addActionListener(new ActionListener() {
            /**
             * opens the statistic screen
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticLogic.openFavScreen();
            }
        });
    }


    public static void setLabels(List<Media> mediaTable) {
        String[] columnNames = {"Titel", "Genres", "Typ", "Dauer/Staffel", "Jahr", "Altersfreigabe"};
        String[][] data = UtilityLogic.getTableInformation(mediaTable);
        
        favouritesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tableScrollPane.setVisible(true);


    }
}
