package view;

import controller.PredloziDijagnozuListener;
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

        Label analizaKrvi = new Label("         Analiza krvi");

        JPanel panelNivoSec = new JPanel(new FlowLayout());
        Label nivoSecera =  new Label("     nivo secera:");
        JTextField rezNivoSecera = new JTextField();
        rezNivoSecera.setPreferredSize(new Dimension(50,30));

        JPanel panelNivoHol = new JPanel(new FlowLayout());
        Label nivoHol = new Label("nivo holesterola:");
        JTextField rezNivoHol = new JTextField();
        rezNivoHol.setPreferredSize(new Dimension(50,30));

        JPanel panelNivoTrig = new JPanel(new FlowLayout());
        Label nivoTrig = new Label("nivo triglicerida:");
        JTextField rezNivoTrig = new JTextField();
        rezNivoTrig.setPreferredSize(new Dimension(50,30));

        Label ekg = new Label("         Ekg");

        JPanel panelNalaz = new JPanel(new FlowLayout());
        JLabel nalaz =  new JLabel("nalaz:");
        ButtonGroup nalazBGroup = new ButtonGroup();
        JRadioButton uredan = new JRadioButton("uredan");
        JRadioButton nijeUredan = new JRadioButton("nije uredan");
        JRadioButton neodredjen = new JRadioButton("neodredjen");
        nalazBGroup.add(uredan);
        nalazBGroup.add(nijeUredan);
        nalazBGroup.add(neodredjen);

        JPanel panelRitam = new JPanel(new FlowLayout());
        JLabel ritam =  new JLabel("ritam:");
        ButtonGroup ritamBGroup = new ButtonGroup();
        JRadioButton ubrzan = new JRadioButton("ubrzan");
        JRadioButton usporen = new JRadioButton("usporen");
        JRadioButton normalan = new JRadioButton("normalan");
        ritamBGroup.add(ubrzan);
        ritamBGroup.add(usporen);
        ritamBGroup.add(normalan);

        Label ehokardiografija = new Label("Ehokardiografija");

        JPanel panelNalazEh = new JPanel(new FlowLayout());
        JLabel nalazEh =  new JLabel("nalaz:");
        ButtonGroup nalazEhBGroup = new ButtonGroup();
        JRadioButton uredanEh = new JRadioButton("uredan");
        JRadioButton nijeUredanEh = new JRadioButton("nije uredan");
        nalazEhBGroup.add(uredanEh);
        nalazEhBGroup.add(nijeUredanEh);

        Label ergometrija = new Label("        Ergometrija");

        JPanel panelNalazErg = new JPanel(new FlowLayout());
//        JLabel opterecenja =  new JLabel("podnosi:");
        ButtonGroup podnosiBGroup = new ButtonGroup();
        JRadioButton niskaOpt = new JRadioButton("niska opterecenja");
        JRadioButton visokaOpt = new JRadioButton("visoka opterecenja");
        podnosiBGroup.add(niskaOpt);
        podnosiBGroup.add(visokaOpt);

        Label koronarnaA = new Label("Koronarna angiografija");

        JPanel panelKA = new JPanel(new FlowLayout());
        ButtonGroup kaBGroup = new ButtonGroup();
        JRadioButton pozitivno = new JRadioButton("pozitivno");
        JRadioButton negativno = new JRadioButton("negativno");
        kaBGroup.add(pozitivno);
        kaBGroup.add(negativno);

        Label rendgen = new Label("Rendgen");

        JPanel panelR = new JPanel(new FlowLayout());
        ButtonGroup rBGroup = new ButtonGroup();
        JRadioButton uredanRend = new JRadioButton("uredan");
        JRadioButton nijeUredanRend = new JRadioButton("nije uredan");
        rBGroup.add(uredanRend);
        rBGroup.add(nijeUredanRend);

        Label ct = new Label("CT");

        JPanel panelCT = new JPanel(new FlowLayout());
        ButtonGroup ctBGroup = new ButtonGroup();
        JRadioButton uredanCT = new JRadioButton("uredan");
        JRadioButton nijeUredanCT = new JRadioButton("nije uredan");
        ctBGroup.add(uredanCT);
        ctBGroup.add(nijeUredanCT);

        Label holter24 = new Label("        Holter24");

        JPanel panelFrek = new JPanel(new FlowLayout());
        JLabel srcanaFrekvencija =  new JLabel("Srcana frekvencija:");
        ButtonGroup frekvBGroup = new ButtonGroup();
        JRadioButton povisen = new JRadioButton("povisen");
        JRadioButton snizen = new JRadioButton("snizen");
        frekvBGroup.add(povisen);
        frekvBGroup.add(snizen);

        JPanel panelPoremecajRitma = new JPanel(new FlowLayout());
        JLabel poremecajRitma =  new JLabel("Poremecaj ritma:");
        ButtonGroup porRitBGroup = new ButtonGroup();
        JRadioButton prisutno = new JRadioButton("prisutno");
        JRadioButton nijePr = new JRadioButton("nije prisutno");
        porRitBGroup.add(prisutno);
        porRitBGroup.add(nijePr);

        JPanel panelST = new JPanel(new FlowLayout());
        JLabel st =  new JLabel("ST segment:");
        ButtonGroup stBGroup = new ButtonGroup();
        JRadioButton normalanST = new JRadioButton("normalan");
        JRadioButton nijeNorST = new JRadioButton("nije normalan");
        stBGroup.add(normalanST);
        stBGroup.add(nijeNorST);


        // -------------------------------------------

        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

        MainWindow.getInstance().getBoxCentar().add(analizaKrvi);
        panelNivoSec.add(nivoSecera);
        panelNivoSec.add(rezNivoSecera);
        panelNivoHol.add(nivoHol);
        panelNivoHol.add(rezNivoHol);
        panelNivoTrig.add(nivoTrig);
        panelNivoTrig.add(rezNivoTrig);
//        panelNivoSec.add(nivoHol);
//        panelNivoSec.add(rezNivoHol);
//        panelNivoSec.add(nivoTrig);
//        panelNivoSec.add(rezNivoTrig);
        MainWindow.getInstance().getBoxCentar().add(panelNivoSec);
        MainWindow.getInstance().getBoxCentar().add(panelNivoHol);
        MainWindow.getInstance().getBoxCentar().add(panelNivoTrig);


        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

        MainWindow.getInstance().getBoxCentar().add(ekg);
        panelNalaz.add(nalaz);
        panelNalaz.add(uredan);
        panelNalaz.add(nijeUredan);
        panelNalaz.add(neodredjen);
        MainWindow.getInstance().getBoxCentar().add(panelNalaz);
        panelRitam.add(ritam);
        panelRitam.add(ubrzan);
        panelRitam.add(usporen);
        panelRitam.add(normalan);
        MainWindow.getInstance().getBoxCentar().add(panelRitam);

        MainWindow.getInstance().getBoxRight().add(new Label("              "));
        MainWindow.getInstance().getBoxRight().add(ehokardiografija);
        panelNalazEh.add(uredanEh);
        panelNalazEh.add(nijeUredanEh);
        MainWindow.getInstance().getBoxRight().add(panelNalazEh);


        MainWindow.getInstance().getBoxCentar().add(ergometrija);
//        panelNalazErg.add(opterecenja);
        panelNalazErg.add(niskaOpt);
        panelNalazErg.add(visokaOpt);
        MainWindow.getInstance().getBoxCentar().add(panelNalazErg);

        MainWindow.getInstance().getBoxRight().add(new Label("              "));
        MainWindow.getInstance().getBoxRight().add(koronarnaA);
        panelKA.add(pozitivno);
        panelKA.add(negativno);
        MainWindow.getInstance().getBoxRight().add(panelKA);

        MainWindow.getInstance().getBoxRight().add(new Label("              "));
        MainWindow.getInstance().getBoxRight().add(rendgen);
        panelR.add(uredanRend);
        panelR.add(nijeUredanRend);
        MainWindow.getInstance().getBoxRight().add(panelR);

        MainWindow.getInstance().getBoxRight().add(new Label("              "));
        MainWindow.getInstance().getBoxRight().add(ct);
        panelCT.add(uredanCT);
        panelCT.add(nijeUredanCT);
        MainWindow.getInstance().getBoxRight().add(panelCT);

        MainWindow.getInstance().getBoxCentar().add(holter24);
        panelFrek.add(srcanaFrekvencija);
        panelFrek.add(povisen);
        panelFrek.add(snizen);
        MainWindow.getInstance().getBoxCentar().add(panelFrek);
        panelPoremecajRitma.add(poremecajRitma);
        panelPoremecajRitma.add(prisutno);
        panelPoremecajRitma.add(nijePr);
        MainWindow.getInstance().getBoxCentar().add(panelPoremecajRitma);
        panelST.add(st);
        panelST.add(normalanST);
        panelST.add(nijeNorST);
        MainWindow.getInstance().getBoxCentar().add(panelST);

        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
        dalje.addActionListener(new PredloziDijagnozuListener());

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(220,30));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);


        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();


    }

}
