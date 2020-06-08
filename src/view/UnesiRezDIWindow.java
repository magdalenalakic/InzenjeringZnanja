package view;

import controller.PredloziDodatnaIspitivanjaListener;
import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UnesiRezDIWindow  extends JFrame {

    private static UnesiRezDIWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static UnesiRezDIWindow getInstance() {
        if (instance == null) {
            instance = new UnesiRezDIWindow();
//            instance.init();
        }

        return instance;
    }


    public void setInstance(UnesiRezDIWindow newInstance) {
        instance = newInstance;
    }



    public void init() {

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();

        JLabel naslov = new JLabel("- UNESITE REZULTATE DODATNIH ISPITIVANJA -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);

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
