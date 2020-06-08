package view;

import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class FizikalniPregledWindow extends JFrame {

    private static FizikalniPregledWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static FizikalniPregledWindow getInstance() {
        if (instance == null) {
            instance = new FizikalniPregledWindow();
            instance.init();
        }

        return instance;
    }


    public void setInstance(FizikalniPregledWindow newInstance) {
        instance = newInstance;
    }

    private JTextField prtisiakG;
    private JTextField prtisiakD;
    private JRadioButton uredna;
    private JRadioButton postojiSum;
    private JRadioButton poremecajRitma;



    public void init() {

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();

        JPanel pan1 = new JPanel();
        pan1.setLayout(new FlowLayout());

        prtisiakG = new JTextField();
        prtisiakG.setMaximumSize(new Dimension(100,30));

        prtisiakD = new JTextField();
        prtisiakD.setMaximumSize(new Dimension(100,30));

        ButtonGroup auskultacija = new ButtonGroup();
        uredna = new JRadioButton("uredna");
        postojiSum = new JRadioButton("postoji sum");
        poremecajRitma = new JRadioButton("poremecaj ritma");

        auskultacija.add(uredna);
        auskultacija.add(postojiSum);
        auskultacija.add(poremecajRitma);

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Fizikalni pregled ... "));
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Unesigte gornji pritisak:"));
        MainWindow.getInstance().getBoxCentar().add(prtisiakG);
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Unesigte donji pritisak:"));
        MainWindow.getInstance().getBoxCentar().add(prtisiakD);
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Auskultacija"));
        JPanel radioPol =  new JPanel();
        radioPol.setLayout(new FlowLayout());

        auskultacija.add(uredna);
        auskultacija.add(poremecajRitma);
        auskultacija.add(postojiSum);

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        Simptomi[] listS =  Simptomi.values();
        JList<Simptomi> listBox = new JList<>(listS);
        MainWindow.getInstance().getBoxRight().add(new JLabel("Lista izabranih simptoma"));
//        listBox.setBackground(new Color(255,0,0));
        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {
                    JList<Simptomi> list = (JList)e.getSource();
                    System.out.println(list.getSelectedValue());

                    if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma().contains(list.getSelectedValue())){
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma().add(list.getSelectedValue());
                        MainWindow.getInstance().getBoxRight().add(new JLabel(String.valueOf(list.getSelectedValue())));

                    }
                    MainWindow.getInstance().getBoxRight().revalidate();
                    MainWindow.getInstance().getBoxRight().repaint();
                }
            }
        });

        panel.add(listBox);

        JPanel panel2 = new JPanel();
        LayoutManager layout2 = new FlowLayout();
        panel2.setLayout(layout2);
        PorodicneBolesti[] pb =  PorodicneBolesti.values();
        JList<PorodicneBolesti> listaPB = new JList<>(pb);
        MainWindow.getInstance().getBoxRight().add(new JLabel("Lista porodicnih bolesti:"));
//        listBox.setBackground(new Color(255,0,0));
        listaPB.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {
                    JList<PorodicneBolesti> list = (JList)e.getSource();
                    System.out.println(list.getSelectedValue());

                    if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getPorodicneBolesti().contains(list.getSelectedValue())){
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getPorodicneBolesti().add(list.getSelectedValue());
                        MainWindow.getInstance().getBoxRight().add(new JLabel(String.valueOf(list.getSelectedValue())));

                    }
                    MainWindow.getInstance().getBoxRight().revalidate();
                    MainWindow.getInstance().getBoxRight().repaint();
                }
            }
        });

        panel2.add(listaPB);

        MainWindow.getInstance().getBoxCentar().add(uredna);
        MainWindow.getInstance().getBoxCentar().add(postojiSum);
        MainWindow.getInstance().getBoxCentar().add(poremecajRitma);
        MainWindow.getInstance().getBoxCentar().add(panel);
        MainWindow.getInstance().getBoxCentar().add(panel2);

        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
//        dalje.addActionListener(new DodajZdravstveniKartonListener());
        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(100,50));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);

        MainWindow.getInstance().getBoxCentar().add(pan1);
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }
}
