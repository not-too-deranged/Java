package View;

import Control.StatisticLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatistikGui extends JFrame {
    static JFrame f;

    /**
     * creates the statistic screen adding nothing
     */

    public static void statisticScreen() {
        f = new JFrame("Statistic Screen");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        JButton openStatistic = new JButton("Statistik öffnen");


        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        openStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticLogic statisticLogic = new StatisticLogic();
                statisticLogic.openFavScreen();
            }
        });
    }
}
