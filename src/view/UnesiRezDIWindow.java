package view;

import controller.PredloziDijagnozuListener;
import controller.PredloziDodatnaIspitivanjaListener;
import model.DodatnaIspitivanjaEnum;
import model.PorodicneBolesti;
import model.Simptomi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UnesiRezDIWindow  extends JFrame {

    private static UnesiRezDIWindow instance = null;
    private JButton next;
    private JButton cancel;
    private ArrayList<DodatnaIspitivanjaEnum> unetiRezDI = new ArrayList<DodatnaIspitivanjaEnum>();
    private JTextField rezNivoSecera;
    private JTextField rezNivoHol;
    private JTextField rezNivoTrig;
    private JRadioButton uredan;
    private JRadioButton nijeUredan;
    private JRadioButton neodredjen;
    private JRadioButton ubrzan;
    private JRadioButton usporen;
    private JRadioButton normalan;
    private JRadioButton uredanEh;
    private JRadioButton nijeUredanEh;
    private JRadioButton niskaOpt;
    private JRadioButton visokaOpt;
    private JRadioButton pozitivno;
    private JRadioButton negativno;
    private JRadioButton uredanRend;
    private JRadioButton nijeUredanRend;
    private JRadioButton uredanCT;
    private JRadioButton nijeUredanCT;
    private JRadioButton povisen;
    private JRadioButton snizen;
    private JRadioButton prisutno;
    private JRadioButton nijePr;
    private JRadioButton normalanST;
    private JRadioButton nijeNorST;



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

        MainWindow.getInstance().getStatusLinija().setText("");

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();

        JLabel naslov = new JLabel("- UNESITE REZULTATE DODATNIH ISPITIVANJA -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);

        Label analizaKrvi = new Label("         Analiza krvi");

        JPanel panelNivoSec = new JPanel(new FlowLayout());
        Label nivoSecera =  new Label("     nivo secera:");
        rezNivoSecera = new JTextField();
        rezNivoSecera.setPreferredSize(new Dimension(50,30));

        JPanel panelNivoHol = new JPanel(new FlowLayout());
        Label nivoHol = new Label("nivo holesterola:");
        rezNivoHol = new JTextField();
        rezNivoHol.setPreferredSize(new Dimension(50,30));

        JPanel panelNivoTrig = new JPanel(new FlowLayout());
        Label nivoTrig = new Label("nivo triglicerida:");
        rezNivoTrig = new JTextField();
        rezNivoTrig.setPreferredSize(new Dimension(50,30));

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.analizaKrvi)){

            analizaKrvi.setEnabled(false);
            nivoSecera.setEnabled(false);
            nivoHol.setEnabled(false);
            nivoTrig.setEnabled(false);
            rezNivoSecera.setEnabled(false);
            rezNivoHol.setEnabled(false);
            rezNivoTrig.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.analizaKrvi) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.analizaKrvi))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.analizaKrvi);
        }

        Label ekg = new Label("         Ekg");


        JPanel panelNalaz = new JPanel(new FlowLayout());
        JLabel nalaz =  new JLabel("nalaz:");
        ButtonGroup nalazBGroup = new ButtonGroup();
        uredan = new JRadioButton("uredan");
        neodredjen = new JRadioButton("neodredjen");
        nijeUredan = new JRadioButton("nije uredan");
        nalazBGroup.add(uredan);
        nalazBGroup.add(nijeUredan);
        nalazBGroup.add(neodredjen);


        JPanel panelRitam = new JPanel(new FlowLayout());
        JLabel ritam =  new JLabel("ritam:");
        ButtonGroup ritamBGroup = new ButtonGroup();
        ubrzan = new JRadioButton("ubrzan");
        usporen = new JRadioButton("usporen");
        normalan = new JRadioButton("normalan");
        ritamBGroup.add(ubrzan);
        ritamBGroup.add(usporen);
        ritamBGroup.add(normalan);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ekg)){

            ekg.setEnabled(false);
            nalaz.setEnabled(false);
            ritam.setEnabled(false);
            uredan.setEnabled(false);
            nijeUredan.setEnabled(false);
            neodredjen.setEnabled(false);
            ubrzan.setEnabled(false);
            usporen.setEnabled(false);
            normalan.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ekg) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.ekg))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.ekg);
        }

        Label ehokardiografija = new Label("Ehokardiografija");

        JPanel panelNalazEh = new JPanel(new FlowLayout());
        JLabel nalazEh =  new JLabel("nalaz:");
        ButtonGroup nalazEhBGroup = new ButtonGroup();
        uredanEh = new JRadioButton("uredan");
        nijeUredanEh = new JRadioButton("nije uredan");
        nalazEhBGroup.add(uredanEh);
        nalazEhBGroup.add(nijeUredanEh);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ehokardiografija)){

            ehokardiografija.setEnabled(false);
            nalazEh.setEnabled(false);
            uredanEh.setEnabled(false);
            nijeUredanEh.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ehokardiografija) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.ehokardiografija))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.ehokardiografija);
        }

        Label ergometrija = new Label("        Ergometrija");

        JPanel panelNalazErg = new JPanel(new FlowLayout());
//        JLabel opterecenja =  new JLabel("podnosi:");
        ButtonGroup podnosiBGroup = new ButtonGroup();
        niskaOpt = new JRadioButton("niska opterecenja");
        visokaOpt = new JRadioButton("visoka opterecenja");
        podnosiBGroup.add(niskaOpt);
        podnosiBGroup.add(visokaOpt);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ergometrija)){

            ergometrija.setEnabled(false);
            niskaOpt.setEnabled(false);
            visokaOpt.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ergometrija) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.ergometrija))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.ergometrija);
        }

        Label koronarnaA = new Label("Koronarna angiografija");

        JPanel panelKA = new JPanel(new FlowLayout());
        ButtonGroup kaBGroup = new ButtonGroup();
        pozitivno = new JRadioButton("pozitivno");
        negativno = new JRadioButton("negativno");
        kaBGroup.add(pozitivno);
        kaBGroup.add(negativno);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.koronarnaAngiografija)){

            koronarnaA.setEnabled(false);
            pozitivno.setEnabled(false);
            negativno.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.koronarnaAngiografija) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.koronarnaAngiografija))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.koronarnaAngiografija);
        }

        Label rendgen = new Label("Rendgen");

        JPanel panelR = new JPanel(new FlowLayout());
        ButtonGroup rBGroup = new ButtonGroup();
        uredanRend = new JRadioButton("uredan");
        nijeUredanRend = new JRadioButton("nije uredan");
        rBGroup.add(uredanRend);
        rBGroup.add(nijeUredanRend);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.rendgen)){

            rendgen.setEnabled(false);
            uredanRend.setEnabled(false);
            nijeUredanRend.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.rendgen) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.rendgen))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.rendgen);
        }

        Label ct = new Label("CT");

        JPanel panelCT = new JPanel(new FlowLayout());
        ButtonGroup ctBGroup = new ButtonGroup();
        uredanCT = new JRadioButton("uredan");
        nijeUredanCT = new JRadioButton("nije uredan");
        ctBGroup.add(uredanCT);
        ctBGroup.add(nijeUredanCT);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ct)){

            ct.setEnabled(false);
            uredanCT.setEnabled(false);
            nijeUredanCT.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.ct) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.ct))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.ct);
        }


        Label holter24 = new Label("        Holter24");

        JPanel panelFrek = new JPanel(new FlowLayout());
        JLabel srcanaFrekvencija =  new JLabel("Srcana frekvencija:");
        ButtonGroup frekvBGroup = new ButtonGroup();
        povisen = new JRadioButton("povisen");
        snizen = new JRadioButton("snizen");
        frekvBGroup.add(povisen);
        frekvBGroup.add(snizen);

        JPanel panelPoremecajRitma = new JPanel(new FlowLayout());
        JLabel poremecajRitma =  new JLabel("Poremecaj ritma:");
        ButtonGroup porRitBGroup = new ButtonGroup();
        prisutno = new JRadioButton("prisutno");
        nijePr = new JRadioButton("nije prisutno");
        porRitBGroup.add(prisutno);
        porRitBGroup.add(nijePr);

        JPanel panelST = new JPanel(new FlowLayout());
        JLabel st =  new JLabel("ST segment:");
        ButtonGroup stBGroup = new ButtonGroup();
        normalanST = new JRadioButton("normalan");
        nijeNorST = new JRadioButton("nije normalan");
        stBGroup.add(normalanST);
        stBGroup.add(nijeNorST);

        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.holter24)){

            holter24.setEnabled(false);
            srcanaFrekvencija.setEnabled(false);
            poremecajRitma.setEnabled(false);
            st.setEnabled(false);
            povisen.setEnabled(false);
            snizen.setEnabled(false);
            prisutno.setEnabled(false);
            nijePr.setEnabled(false);
            normalanST.setEnabled(false);
            nijeNorST.setEnabled(false);
        }else{
            if(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(DodatnaIspitivanjaEnum.holter24) &&
                    !instance.getUnetiRezDI().contains(DodatnaIspitivanjaEnum.holter24))
                instance.getUnetiRezDI().add(DodatnaIspitivanjaEnum.holter24);
        }




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

    public ArrayList<DodatnaIspitivanjaEnum> getUnetiRezDI() {
        return unetiRezDI;
    }

    public void setUnetiRezDI(ArrayList<DodatnaIspitivanjaEnum> unetiRezDI) {
        this.unetiRezDI = unetiRezDI;
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

    public JTextField getRezNivoSecera() {
        return rezNivoSecera;
    }

    public void setRezNivoSecera(JTextField rezNivoSecera) {
        this.rezNivoSecera = rezNivoSecera;
    }

    public JTextField getRezNivoHol() {
        return rezNivoHol;
    }

    public void setRezNivoHol(JTextField rezNivoHol) {
        this.rezNivoHol = rezNivoHol;
    }

    public JTextField getRezNivoTrig() {
        return rezNivoTrig;
    }

    public void setRezNivoTrig(JTextField rezNivoTrig) {
        this.rezNivoTrig = rezNivoTrig;
    }

    public JRadioButton getUredan() {
        return uredan;
    }

    public void setUredan(JRadioButton uredan) {
        this.uredan = uredan;
    }

    public JRadioButton getNijeUredan() {
        return nijeUredan;
    }

    public void setNijeUredan(JRadioButton nijeUredan) {
        this.nijeUredan = nijeUredan;
    }

    public JRadioButton getNeodredjen() {
        return neodredjen;
    }

    public void setNeodredjen(JRadioButton neodredjen) {
        this.neodredjen = neodredjen;
    }

    public JRadioButton getUbrzan() {
        return ubrzan;
    }

    public void setUbrzan(JRadioButton ubrzan) {
        this.ubrzan = ubrzan;
    }

    public JRadioButton getUsporen() {
        return usporen;
    }

    public void setUsporen(JRadioButton usporen) {
        this.usporen = usporen;
    }

    public JRadioButton getNormalan() {
        return normalan;
    }

    public void setNormalan(JRadioButton normalan) {
        this.normalan = normalan;
    }

    public JRadioButton getUredanEh() {
        return uredanEh;
    }

    public void setUredanEh(JRadioButton uredanEh) {
        this.uredanEh = uredanEh;
    }

    public JRadioButton getNijeUredanEh() {
        return nijeUredanEh;
    }

    public void setNijeUredanEh(JRadioButton nijeUredanEh) {
        this.nijeUredanEh = nijeUredanEh;
    }

    public JRadioButton getNiskaOpt() {
        return niskaOpt;
    }

    public void setNiskaOpt(JRadioButton niskaOpt) {
        this.niskaOpt = niskaOpt;
    }

    public JRadioButton getVisokaOpt() {
        return visokaOpt;
    }

    public void setVisokaOpt(JRadioButton visokaOpt) {
        this.visokaOpt = visokaOpt;
    }

    public JRadioButton getPozitivno() {
        return pozitivno;
    }

    public void setPozitivno(JRadioButton pozitivno) {
        this.pozitivno = pozitivno;
    }

    public JRadioButton getNegativno() {
        return negativno;
    }

    public void setNegativno(JRadioButton negativno) {
        this.negativno = negativno;
    }

    public JRadioButton getUredanRend() {
        return uredanRend;
    }

    public void setUredanRend(JRadioButton uredanRend) {
        this.uredanRend = uredanRend;
    }

    public JRadioButton getNijeUredanRend() {
        return nijeUredanRend;
    }

    public void setNijeUredanRend(JRadioButton nijeUredanRend) {
        this.nijeUredanRend = nijeUredanRend;
    }

    public JRadioButton getUredanCT() {
        return uredanCT;
    }

    public void setUredanCT(JRadioButton uredanCT) {
        this.uredanCT = uredanCT;
    }

    public JRadioButton getNijeUredanCT() {
        return nijeUredanCT;
    }

    public void setNijeUredanCT(JRadioButton nijeUredanCT) {
        this.nijeUredanCT = nijeUredanCT;
    }

    public JRadioButton getPovisen() {
        return povisen;
    }

    public void setPovisen(JRadioButton povisen) {
        this.povisen = povisen;
    }

    public JRadioButton getSnizen() {
        return snizen;
    }

    public void setSnizen(JRadioButton snizen) {
        this.snizen = snizen;
    }

    public JRadioButton getPrisutno() {
        return prisutno;
    }

    public void setPrisutno(JRadioButton prisutno) {
        this.prisutno = prisutno;
    }

    public JRadioButton getNijePr() {
        return nijePr;
    }

    public void setNijePr(JRadioButton nijePr) {
        this.nijePr = nijePr;
    }

    public JRadioButton getNormalanST() {
        return normalanST;
    }

    public void setNormalanST(JRadioButton normalanST) {
        this.normalanST = normalanST;
    }

    public JRadioButton getNijeNorST() {
        return nijeNorST;
    }

    public void setNijeNorST(JRadioButton nijeNorST) {
        this.nijeNorST = nijeNorST;
    }
}
