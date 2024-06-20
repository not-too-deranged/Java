package View;

import Control.FavouritesLogic;
import Control.MediaLogic;
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
        f.add(faves, BorderLayout.NORTH);
        f.add(favesAndFunctions, BorderLayout.SOUTH);
        f.add(tableScrollPane, BorderLayout.CENTER);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        favouritesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
            public void windowClosing(WindowEvent e){
                f.dispose();
                favouritesLogic.setMediaWindow();
                MediaViewGui.setLabels(MediaStorage.getMediaList());
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                favouritesLogic.removeElement((String) favouritesTable.getValueAt(favouritesTable.getSelectedRow(), 0));
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = favouritesTable.getSelectedRow();
                String title = favouritesTable.getValueAt(row, 0).toString();
                String comment = commentField.getText();
                String rate = (String) rating.getSelectedItem();
                FavouritesStorage.addRating(title, comment, rate);
            }
        });
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
