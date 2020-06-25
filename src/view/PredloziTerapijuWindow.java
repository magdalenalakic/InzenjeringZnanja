package view;

import controller.IzvjetsajListener;
import controller.PredloziDodatnaIspitivanjaListener;
import controller.UnesiRezDIListener;
import model.*;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PredloziTerapijuWindow  extends JFrame {

    private static PredloziTerapijuWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static PredloziTerapijuWindow getInstance() {
        if (instance == null) {
            instance = new PredloziTerapijuWindow();
//            instance.init();
        }

        return instance;
    }


    public void setInstance(PredloziTerapijuWindow newInstance) {
        instance = newInstance;
    }


    private JLabel lab;

    public void init() {
        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

        JLabel naslov = new JLabel("- TERAPIJA -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        List<Lekovi> lis = new ArrayList<>();
        for(Lekovi l: MainWindow.getInstance().getTerapija()){
            lis.add(l);
        }
        JList<Lekovi> listBox = new JList(lis.toArray());

        JPanel pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        pan1.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane;
        Label textS = new Label("Lista izabranih lekova:");
        textS.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        pan1.add(textS);

        MainWindow.getInstance().getBoxRight().add(pan1);
        MainWindow.getInstance().getBoxRight().revalidate();
        MainWindow.getInstance().getBoxRight().repaint();

        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {
                    JList<Lekovi> list = (JList)e.getSource();

                    if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaLekova().contains(list.getSelectedValue())){
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaLekova().add(list.getSelectedValue());
                        pan1.add(new JLabel(String.valueOf(list.getSelectedValue())));

                    }

                    MainWindow.getInstance().getBoxRight().revalidate();
                    MainWindow.getInstance().getBoxRight().repaint();
                }
            }
        });

        panel.add(listBox);
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(50, 120));
        MainWindow.getInstance().getBoxCentar().add(scrollPane);


        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
        dalje.addActionListener(new IzvjetsajListener());

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(220,30));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);

        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();


    }
}
