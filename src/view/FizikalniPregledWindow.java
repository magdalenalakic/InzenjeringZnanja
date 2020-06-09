package view;

import controller.PredloziDodatnaIspitivanjaListener;
import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FizikalniPregledWindow extends JFrame {

    private static FizikalniPregledWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static FizikalniPregledWindow getInstance() {
        if (instance == null) {
            instance = new FizikalniPregledWindow();
//            instance.init();
        }

        return instance;
    }


    public void setInstance(FizikalniPregledWindow newInstance) {
        instance = newInstance;
    }

    private JTextField pritisakG;
    private JTextField pritisakD;
    private JRadioButton uredna;
    private JRadioButton postojiSum;
    private JRadioButton poremecajRitma;
    private JPanel pritisakGD;
    private JPanel pp;
    private JLabel pG;
    private JLabel pD;
    JScrollPane scrollPane;
    JScrollPane scrollPane2;

    public void init() {

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();
        pp = new JPanel(new FlowLayout());

        pG = new JLabel("gornji: ");
        pritisakG = new JTextField();
        pritisakG.setPreferredSize(new Dimension(80,30));

        pD = new JLabel("donji: ");
        pritisakD = new JTextField();
        pritisakD.setPreferredSize(new Dimension(80,30));

        ButtonGroup auskultacija = new ButtonGroup();
        uredna = new JRadioButton("uredna");
        postojiSum = new JRadioButton("postoji sum");
        poremecajRitma = new JRadioButton("poremecaj ritma");

        auskultacija.add(uredna);
        auskultacija.add(postojiSum);
        auskultacija.add(poremecajRitma);

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxCentar().removeAll();

        JLabel naslov = new JLabel("- FIZIKALNI PREGLED -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);
        MainWindow.getInstance().getBoxCentar().add(new Label("Unesigte pritisak:"));
        pp.add(pG);
        pp.add(pritisakG);
        pp.add(pD);
        pp.add(pritisakD);
        MainWindow.getInstance().getBoxCentar().add(pp);
        MainWindow.getInstance().getBoxCentar().add(new Label("Auskultacija"));


        //scroll box za listu simptoma

            JPanel panel = new JPanel();
            LayoutManager layout = new FlowLayout();
            panel.setLayout(layout);
            List<Simptomi> listS = new ArrayList<>();
            for(Simptomi s: Simptomi.values()){
                if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma().contains(s)){
                    listS.add(s);
                }
            }
            JList<Simptomi> listBox = new JList(listS.toArray());
            JPanel panSimptomi = new JPanel();
            panSimptomi.setLayout(new BoxLayout(panSimptomi, BoxLayout.Y_AXIS));
//            panSimptomi.setPreferredSize(new Dimension(150, 100));
            panSimptomi.setBackground(new Color(255, 255, 255));
            Label textS = new Label("Lista izabranih simptoma:");
            textS.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            panSimptomi.add(textS);

            for(Simptomi simptom: MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma()){
                panSimptomi.add(new JLabel(simptom.toString()));
            }
            MainWindow.getInstance().getBoxRight().add(panSimptomi);
            MainWindow.getInstance().getBoxRight().revalidate();
            MainWindow.getInstance().getBoxRight().repaint();
            listBox.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    if (!e.getValueIsAdjusting()) {
                        JList<Simptomi> list = (JList)e.getSource();
//                        System.out.println(list.getSelectedValue());

                        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma().contains(list.getSelectedValue())){
                            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma().add(list.getSelectedValue());
                            panSimptomi.add(new JLabel(String.valueOf(list.getSelectedValue())));

                        }

                        MainWindow.getInstance().getBoxRight().revalidate();
                        MainWindow.getInstance().getBoxRight().repaint();
                    }
                }
            });

            panel.add(listBox);
            scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(50, 180));



        //scroll box za listu porodicnih bolesti

            JPanel panel2 = new JPanel();
            LayoutManager layout2 = new FlowLayout();
            panel2.setLayout(layout2);
            PorodicneBolesti[] pb =  PorodicneBolesti.values();
            JList<PorodicneBolesti> listaPB = new JList<>(pb);
            JPanel panPB = new JPanel();
            panPB.setSize(200,600);
            panPB.setBackground(new Color(255, 255, 255));
            panPB.setLayout(new BoxLayout(panPB, BoxLayout.Y_AXIS));
            Label textPB = new Label("Lista izabranih porodicnih bolesti:");
            textPB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            panPB.add(textPB);
            listaPB.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    if (!e.getValueIsAdjusting()) {
                        JList<PorodicneBolesti> list = (JList)e.getSource();
//                        System.out.println(list.getSelectedValue());

                        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getPorodicneBolesti().contains(list.getSelectedValue())){
                            MainWindow.getInstance().getTrenutnoAktivanPacijent().getPorodicneBolesti().add(list.getSelectedValue());
                            panPB.add(new JLabel(String.valueOf(list.getSelectedValue())));
                            MainWindow.getInstance().getBoxRight().add(panPB);
                        }
                        MainWindow.getInstance().getBoxRight().revalidate();
                        MainWindow.getInstance().getBoxRight().repaint();
                    }
                }
            });

            panel2.add(listaPB);
            scrollPane2 = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane2.setPreferredSize(new Dimension(100, 100));


        MainWindow.getInstance().getBoxCentar().add(uredna);
        MainWindow.getInstance().getBoxCentar().add(postojiSum);
        MainWindow.getInstance().getBoxCentar().add(poremecajRitma);

        MainWindow.getInstance().getBoxCentar().add(new Label("Izaberi simptome: "));
        MainWindow.getInstance().getBoxCentar().add(scrollPane);
        MainWindow.getInstance().getBoxCentar().add(new Label("Izaberi porodicne bolesti: "));
        MainWindow.getInstance().getBoxCentar().add(scrollPane2);


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

    public JButton getNext() {
        return next;
    }

    public void setNext(JButton next) {
        this.next = next;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public JTextField getPritisakG() {
        return pritisakG;
    }

    public void setPritisakG(JTextField pritisakG) {
        this.pritisakG = pritisakG;
    }

    public JTextField getPritisakD() {
        return pritisakD;
    }

    public void setPritisakD(JTextField pritisakD) {
        this.pritisakD = pritisakD;
    }

    public JRadioButton getUredna() {
        return uredna;
    }

    public void setUredna(JRadioButton uredna) {
        this.uredna = uredna;
    }

    public JRadioButton getPostojiSum() {
        return postojiSum;
    }

    public void setPostojiSum(JRadioButton postojiSum) {
        this.postojiSum = postojiSum;
    }

    public JRadioButton getPoremecajRitma() {
        return poremecajRitma;
    }

    public void setPoremecajRitma(JRadioButton poremecajRitma) {
        this.poremecajRitma = poremecajRitma;
    }

    public JPanel getPritisakGD() {
        return pritisakGD;
    }

    public void setPritisakGD(JPanel pritisakGD) {
        this.pritisakGD = pritisakGD;
    }

    public JPanel getPp() {
        return pp;
    }

    public void setPp(JPanel pp) {
        this.pp = pp;
    }

    public JLabel getpG() {
        return pG;
    }

    public void setpG(JLabel pG) {
        this.pG = pG;
    }

    public JLabel getpD() {
        return pD;
    }

    public void setpD(JLabel pD) {
        this.pD = pD;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void setScrollPane2(JScrollPane scrollPane2) {
        this.scrollPane2 = scrollPane2;
    }
}
