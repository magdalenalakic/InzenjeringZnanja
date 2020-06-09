package view;

import controller.PredloziDodatnaIspitivanjaListener;
import model.Dijagnoze;
import model.Lekovi;
import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

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
        MainWindow.getInstance().getBoxRight().removeAll();


        JLabel naslov = new JLabel("- IZVESTAJ -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);
        MainWindow.getInstance().getBoxRight().add(new Label("                                                          "));

        JPanel pan2 = new JPanel();
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.Y_AXIS));
        Label labP = new Label("Ime pacijenta:");
        pan2.add(labP);
        JLabel imeP = new JLabel(MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme());
        pan2.add(imeP);
        MainWindow.getInstance().getBoxCentar().add(pan2);

        JPanel pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        Label labDijag = new Label("Dijagnoza:");
        pan1.add(labDijag);

//        for(Dijagnoze l: MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza()){
//            System.out.println(l.toString());
//
//
//            lab = new JLabel(l.toString());
//            pan1.add(lab);
//        }
        lab = new JLabel(PredloziDijagnozuWindow.getInstance().getDijagnoze().getSelectedItem().toString());
        pan1.add(lab);

        MainWindow.getInstance().getBoxCentar().add(pan1);

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        Label labTer = new Label("Terapija:");
        pan1.add(labTer);
        for(Lekovi l: MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaLekova()){
            System.out.println(l.toString());
            lab = new JLabel(l.toString());
            pan.add(lab);
        }
        MainWindow.getInstance().getBoxCentar().add(pan);

        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();


    }
}
