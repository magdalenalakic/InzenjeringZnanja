package view;

import controller.PredloziDodatnaIspitivanjaListener;
import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class PredloziDodatnaIspitivanjaWindow  extends JFrame {

    private static PredloziDodatnaIspitivanjaWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static PredloziDodatnaIspitivanjaWindow getInstance() {
        if (instance == null) {
            instance = new PredloziDodatnaIspitivanjaWindow();
            instance.init();
        }

        return instance;
    }


    public void setInstance(PredloziDodatnaIspitivanjaWindow newInstance) {
        instance = newInstance;
    }


    public void init() {

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();

        MainWindow.getInstance().getBoxCentar().add(new Label("- DODATNA ISPITIVANJA -"));

        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
        dalje.addActionListener(new PredloziDodatnaIspitivanjaListener());

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(220,30));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);


        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }


}
