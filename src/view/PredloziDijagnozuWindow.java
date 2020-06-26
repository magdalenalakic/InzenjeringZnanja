package view;

import controller.PredloziTerapijuListener;
import controller.UnesiRezDIListener;
import model.Dijagnoze;
import model.DodatnaIspitivanjaEnum;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PredloziDijagnozuWindow extends JFrame {

    private static PredloziDijagnozuWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static PredloziDijagnozuWindow getInstance() {
        if (instance == null) {
            instance = new PredloziDijagnozuWindow();
//            instance.init();
        }

        return instance;
    }


    public void setInstance(PredloziDijagnozuWindow newInstance) {
        instance = newInstance;
    }

    private JComboBox dijagnoze;

    public void init() {

        JLabel naslov = new JLabel("- DIJAGNOZE -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        List<Dijagnoze> lis = new ArrayList<>();
        for(Dijagnoze D:MainWindow.getInstance().getDijagnoze()){
            lis.add(D);
        }

        JList<Dijagnoze> listBox = new JList(lis.toArray());

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.setBackground(new Color(255, 255, 255));

        dijagnoze = new JComboBox();
        dijagnoze.removeAllItems();
//        dijagnoze.setPreferredSize(new Dimension(150,20));
        for( Dijagnoze ime: MainWindow.getInstance().getDijagnoze()) {
            dijagnoze.addItem(ime);
        }


        System.out.println(dijagnoze.getSelectedItem());

        pan.add(dijagnoze);
        MainWindow.getInstance().getBoxCentar().add(pan);

        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
        dalje.addActionListener(new PredloziTerapijuListener());

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(220,30));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);


        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }

    public JComboBox getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(JComboBox dijagnoze) {
        this.dijagnoze = dijagnoze;
    }
}
