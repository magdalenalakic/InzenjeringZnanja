package view;

import controller.DodajZdravstveniKartonListener;
import controller.ZapocniPregledListener;
import model.Simptomi;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
        prtisiakG.setMaximumSize(new Dimension(200,30));

        prtisiakD = new JTextField();
        prtisiakD.setMaximumSize(new Dimension(200,30));

        ButtonGroup auskultacija = new ButtonGroup();
        uredna = new JRadioButton("uredna");
        postojiSum = new JRadioButton("postoji sum");
        poremecajRitma = new JRadioButton("poremecaj ritma");

        auskultacija.add(uredna);
        auskultacija.add(postojiSum);
        auskultacija.add(poremecajRitma);

//        godinePacijenta = new JTextField();
//        godinePacijenta.setMaximumSize(new Dimension(400,30));
//
//        ButtonGroup tezina = new ButtonGroup();
//        tezinaNormalna = new JRadioButton("normalna");
//        tezinaPovecana = new JRadioButton("povecana");
//        tezinaSmanjena = new JRadioButton("smanjena");
//        tezina.add(tezinaNormalna);
//        tezina.add(tezinaPovecana);
//        tezina.add(tezinaSmanjena);
//
//        ButtonGroup pusac = new ButtonGroup();
//        pusacDa = new JRadioButton("da");
//        pusacNe = new JRadioButton("ne");
//        pusac.add(pusacDa);
//        pusac.add(pusacNe);
//
//        ButtonGroup dijabeticar = new ButtonGroup();
//        dijabeticarDa = new JRadioButton("da");
//        dijabeticarNe = new JRadioButton("ne");
//        dijabeticar.add(dijabeticarDa);
//        dijabeticar.add(dijabeticarNe);
//
//        ButtonGroup asmaticar = new ButtonGroup();
//        asmaticarDa = new JRadioButton("da");
//        asmaticarNe = new JRadioButton("ne");
//        asmaticar.add(asmaticarDa);
//        asmaticar.add(asmaticarNe);
//
//        ButtonGroup fizickaAktivnost = new ButtonGroup();
//        fizickaAktivnostDa = new JRadioButton("jeste");
//        fizickaAktivnostNe = new JRadioButton("nije");
//        fizickaAktivnost.add(fizickaAktivnostDa);
//        fizickaAktivnost.add(fizickaAktivnostNe);
//
//        ButtonGroup trudnoca = new ButtonGroup();
//        trudnocaDa = new JRadioButton("da");
//        trudnocaNe = new JRadioButton("ne");
//        trudnoca.add(trudnocaDa);
//        trudnoca.add(trudnocaNe);
//
//        ButtonGroup alergija = new ButtonGroup();
//        alergijaDa = new JRadioButton("ima");
//        alergijaNe = new JRadioButton("nema");
//        alergija.add(alergijaDa);
//        alergija.add(alergijaNe);
//

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Fizikalni pregled ... "));
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Pritisak:"));
        MainWindow.getInstance().getBoxCentar().add(prtisiakG);
        MainWindow.getInstance().getBoxCentar().add(prtisiakD);
        MainWindow.getInstance().getBoxCentar().add(new JLabel("Auskultacija"));
        JPanel radioPol =  new JPanel();
        radioPol.setLayout(new FlowLayout());
//                boxCentar.add(polZ);
//                boxCentar.add(polM);
        auskultacija.add(uredna);
        auskultacija.add(poremecajRitma);
        auskultacija.add(postojiSum);

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        Simptomi[] numbers =  Simptomi.values();
        JList<Simptomi> listBox = new JList<>(numbers);
        listBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listBox.setVisibleRowCount(-1); // to keep all values visible
        listBox.setSelectedIndex(3);
        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();



//                JOptionPane.showMessageDialog(frame,list.getSelectedValue());
            }
        });

        panel.add(listBox);

//        add(radioPol);
//        revalidate();
//        repaint();
        MainWindow.getInstance().getBoxCentar().add(uredna);
        MainWindow.getInstance().getBoxCentar().add(postojiSum);
        MainWindow.getInstance().getBoxCentar().add(poremecajRitma);
        MainWindow.getInstance().getBoxCentar().add(panel);
//        boxCentar.add(new JLabel("Godine:"));
//        boxCentar.add(godinePacijenta);
//        boxCentar.add(new JLabel("Tezina:"));
//        boxCentar.add(tezinaSmanjena);
//        boxCentar.add(tezinaNormalna);
//        boxCentar.add(tezinaPovecana);
//        boxCentar.add(new JLabel("Pusac:"));
//        boxCentar.add(pusacDa);
//        boxCentar.add(pusacNe);
//        boxCentar.add(new JLabel("Dijabeticar:"));
//        boxCentar.add(dijabeticarDa);
//        boxCentar.add(dijabeticarNe);
//        boxRight.add(new JLabel("Asmaticar:"));
//        boxRight.add(asmaticarDa);
//        boxRight.add(asmaticarNe);
//        boxRight.add(new JLabel("Fizicka aktivnost:"));
//        boxRight.add(fizickaAktivnostDa);
//        boxRight.add(fizickaAktivnostNe);
//        boxRight.add(new JLabel("Trudnoca:"));
//        boxRight.add(trudnocaDa);
//        boxRight.add(trudnocaNe);
//        boxRight.add(new JLabel("Alergija:"));
//        boxRight.add(alergijaDa);
//        boxRight.add(alergijaNe);
        JButton dodajZK = new JButton("DALJE");
        dodajZK.setPreferredSize(new Dimension(200,30));
        dodajZK.addActionListener(new DodajZdravstveniKartonListener());
        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(100,50));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dodajZK);

//        boxCentar.revalidate();
//        boxCentar.repaint();
//        boxRight.revalidate();
//        boxRight.repaint();

        MainWindow.getInstance().getBoxCentar().add(pan1);
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();
    }
}
