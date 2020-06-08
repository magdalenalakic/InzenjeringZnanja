package view;

//import com.sun.org.apache.xpath.internal.operations.Variable;
import com.ugos.jiprolog.engine.*;
import controller.CuvanjePacijenata;
import controller.DodajZdravstveniKartonListener;
import controller.FizikalniPregledListener;
import controller.IzmeniZdravstveniKartonListener;
import controller.PacijentController;
import controller.ZapocniPregledListener;
import main.DijagnozeApp;
import main.DodatnaIspitivanjaApp;
import main.TerapijaApp;
import model.*;
import org.jpl7.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;

//import javax.management.Query;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.Integer;
import java.util.*;
import java.util.List;
//import com.ugos.jiprolog.engine.JIPEngine;
//import com.ugos.jiprolog.engine.JIPQuery;
//import com.ugos.jiprolog.engine.JIPTerm;
//import com.ugos.jiprolog.engine.JIPVariable;



public class MainWindow extends JFrame {

    private static MainWindow instance = null;
    private static PacijentController pacijentController = new PacijentController();
    private IzabranaOpcija izabranaOpcija;
    private Pacijent trenutnoAktivanPacijent;
    private JComboBox cbPacijenti;
    private JList listaSimptoma;
    private JButton previous;
    private JButton cancel;
    private JButton next;
    private JIPEngine engine = new JIPEngine();
    private Box boxCentar;
    private Box boxRight;
    private JTextField imePacijenta;
    private JTextField godinePacijenta;
    private JRadioButton polZ;
    private JRadioButton polM;
    private JRadioButton pusacDa;
    private JRadioButton pusacNe;
    private JRadioButton dijabeticarDa;
    private JRadioButton dijabeticarNe;
    private JRadioButton tezinaPovecana;
    private JRadioButton tezinaSmanjena;
    private JRadioButton tezinaNormalna;
    private JRadioButton asmaticarDa;
    private JRadioButton asmaticarNe;
    private JRadioButton fizickaAktivnostDa;
    private JRadioButton fizickaAktivnostNe;
    private JRadioButton trudnocaDa;
    private JRadioButton trudnocaNe;
    private JRadioButton alergijaDa;
    private JRadioButton alergijaNe;
    private ArrayList<String> pacijenti;
    private ArrayList<Lekovi> terapija;
    private ArrayList<Dijagnoze> dijagnoze;
    private ArrayList<DodatnaIspitivanjaEnum> dodatnaIspitivanja;
    private  JPanel panCenter;

//    private List<>

    private JLabel statusLinija;
    private JLabel trudnoca;
    private ButtonGroup trudnocaButtonGroup;


    public static MainWindow getInstance(){
        if (instance == null) {
            instance = new MainWindow();
            instance.ucitajPrologFajlove();
            instance.initialise();

        }
        return instance;
    }

    public void ucitajPrologFajlove(){
        engine.consultFile("prolog/projekat.pl");
    }

    public void initialise(){

        setSize(1000, 700);
        JLabel title = new JLabel("Aplikacija za pomoc pri dijagnostici i predlaganju ispitivanja i terapija");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        setTitle("Aplikacija za doktore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(WindowEvent e) {
                int dialogButton = JOptionPane.showConfirmDialog(instance,
                        "Da li ste sigurni da zelite da zatvorite aplikaciju",
                        "Zatvaranje aplikacije",
                        JOptionPane.YES_NO_OPTION);

                if (dialogButton != JOptionPane.YES_OPTION) {
                    instance.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                } else {
                    instance.dispose();
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }
        });
        getRootPane().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.LIGHT_GRAY));

        JPanel panTop = new JPanel();
        panTop.setPreferredSize(new Dimension(100,50));
        panTop.add(title);
        add(panTop, BorderLayout.NORTH);


//        JPanel panTop = new JPanel();
//        panTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel licence = new JLabel("Izaberi Pacijenta");
        licence.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
//        panTop.add(licence);
        JPanel panPacijenti = new JPanel();


//        ArrayList<String> pacijenti = new ArrayList<String>();
//        pacijenti.add("Milan");
//        pacijenti.add("Milica");
//        pacijenti.add("Petar");


        ArrayList<String> simptomi = new ArrayList<String>();
//        simptomi.add("otezano disanje");
//        simptomi.add("bol u grudima");
        listaSimptoma = new JList(simptomi.toArray());
        listaSimptoma.setSize(100,20);
        listaSimptoma.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        panPacijenti.add(cbPacijenti);
        panCenter = new JPanel();
        JPanel panCenter = new JPanel();


        boxCentar = Box.createVerticalBox();
        boxCentar.add(Box.createVerticalStrut(20));
        boxCentar.add(Box.createGlue());
        panCenter.add(boxCentar);

        add(panCenter, BorderLayout.CENTER);

        JPanel panRight = new JPanel();
        panRight.setPreferredSize(new Dimension(250,200));
        add(panRight, BorderLayout.EAST);

        boxRight = Box.createVerticalBox();
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(Box.createGlue());
        panRight.add(boxRight);

        JPanel panLeft = new JPanel();
        panLeft.setPreferredSize(new Dimension(250,200));
        panLeft.setBackground(new Color(26, 26, 22));

        JButton dodaj = new JButton("Dodaj zdravstveni karton");
        dodaj.setPreferredSize(new Dimension(200,30));
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dodajZdravstveniKartonView();


            }
        });

        JButton izmeni = new JButton("Izmeni zdravstveni karton");
        izmeni.setPreferredSize(new Dimension(200,30));


        JButton cuvanje = new JButton("Cuvanje");
        cuvanje.setPreferredSize(new Dimension(200,30));
        cuvanje.addActionListener(new CuvanjePacijenata());



        izmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.getStatusLinija().setText("");
                OdabirPacijentaZaIzmenuZK();
            }
        });


        JButton zapocni = new JButton("Zapocni pregled");
        zapocni.setPreferredSize(new Dimension(200,30));
        zapocni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.getStatusLinija().setText("");
                zapocniPregledView();
            }
        });
        panLeft.add(dodaj);
        panLeft.add(izmeni);
        panLeft.add(zapocni);
        panLeft.add(cuvanje);

        add(panLeft, BorderLayout.WEST);



        JPanel panBtm = new JPanel();
        panBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));

        next = new JButton("Dalje");
        next.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        next.setPreferredSize(new Dimension(95, 25));
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                if (cb.isSelected()) {
//                    WizardFrame3 wz3 = WizardFrame3.getInstance();
//                    wz3.setVisible(true);
//                    instance.setVisible(false);
//                } else {
//                    JOptionPane.showMessageDialog(instance,WizardFrame2.getInstance().getResourceBundle().getString("terms"),
//                            WizardFrame2.getInstance().getResourceBundle().getString("warning"), JOptionPane.OK_OPTION);
//                    return;
//                }
            }
        });
        cancel = new JButton("Zatvori");
        cancel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        cancel.setPreferredSize(new Dimension(95, 25));
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.showConfirmDialog(instance,
                        "Da li ste sigurni da zelite da zatvorite aplikaciju",
                        "Zatvaranje aplikacije",
                        JOptionPane.YES_NO_OPTION);

                if (dialogButton != JOptionPane.YES_OPTION) {
                    instance.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                } else {
                    instance.dispose();
                }

            }
        });
        previous = new JButton("Prethodno");
        previous.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        previous.setPreferredSize(new Dimension(95, 25));
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boxCentar.removeAll();
                boxRight.removeAll();
                WelcomeWindow wz1 = WelcomeWindow.getInstance();
                wz1.setVisible(true);
                instance.setVisible(false);

            }
        });
        statusLinija = new JLabel();
        statusLinija.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        statusLinija.setForeground(new Color(255,0,0));

        panBtm.add(statusLinija);
        panBtm.add(previous);
        panBtm.add(cancel);

        add(panBtm, BorderLayout.SOUTH);
    }

    public void dodajZdravstveniKartonView(){
        imePacijenta = new JTextField();
        imePacijenta.setMaximumSize(new Dimension(400,30));

        ButtonGroup pol = new ButtonGroup();
        polZ = new JRadioButton("zenski");
        polM = new JRadioButton("muski");
        polM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(instance.getPolM().isSelected()){
                    instance.getTrudnocaDa().setEnabled(false);
                    instance.getTrudnocaNe().setEnabled(false);
                }else{
                    instance.getTrudnocaDa().setEnabled(true);
                    instance.getTrudnocaNe().setEnabled(true);
                }

            }
        });
        pol.add(polZ);
        pol.add(polM);

        godinePacijenta = new JTextField();
        godinePacijenta.setMaximumSize(new Dimension(400,30));

        ButtonGroup tezina = new ButtonGroup();
        tezinaNormalna = new JRadioButton("normalna");
        tezinaPovecana = new JRadioButton("povecana");
        tezinaSmanjena = new JRadioButton("smanjena");
        tezina.add(tezinaNormalna);
        tezina.add(tezinaPovecana);
        tezina.add(tezinaSmanjena);

        ButtonGroup pusac = new ButtonGroup();
        pusacDa = new JRadioButton("da");
        pusacNe = new JRadioButton("ne");
        pusac.add(pusacDa);
        pusac.add(pusacNe);

        ButtonGroup dijabeticar = new ButtonGroup();
        dijabeticarDa = new JRadioButton("da");
        dijabeticarNe = new JRadioButton("ne");
        dijabeticar.add(dijabeticarDa);
        dijabeticar.add(dijabeticarNe);

        ButtonGroup asmaticar = new ButtonGroup();
        asmaticarDa = new JRadioButton("da");
        asmaticarNe = new JRadioButton("ne");
        asmaticar.add(asmaticarDa);
        asmaticar.add(asmaticarNe);

        ButtonGroup fizickaAktivnost = new ButtonGroup();
        fizickaAktivnostDa = new JRadioButton("jeste");
        fizickaAktivnostNe = new JRadioButton("nije");
        fizickaAktivnost.add(fizickaAktivnostDa);
        fizickaAktivnost.add(fizickaAktivnostNe);

        trudnocaButtonGroup = new ButtonGroup();
        trudnocaDa = new JRadioButton("da");
        trudnocaNe = new JRadioButton("ne");
        trudnocaButtonGroup.add(trudnocaDa);
        trudnocaButtonGroup.add(trudnocaNe);

        ButtonGroup alergija = new ButtonGroup();
        alergijaDa = new JRadioButton("ima");
        alergijaNe = new JRadioButton("nema");
        alergija.add(alergijaDa);
        alergija.add(alergijaNe);


        boxCentar.removeAll();
        boxRight.removeAll();
        boxCentar.add(new JLabel("Podaci o pacijentu"));
        boxCentar.add(new JLabel("Ime:"));
        boxCentar.add(imePacijenta);
        boxCentar.add(new JLabel("Pol:"));

        JPanel radioPol =  new JPanel();
        radioPol.setLayout(new FlowLayout());
        radioPol.add(polZ);
        radioPol.add(polM);

        boxCentar.add(polZ);
        boxCentar.add(polM);

        boxCentar.add(new JLabel("Godine:"));
        boxCentar.add(godinePacijenta);

        boxCentar.add(new JLabel("Tezina:"));
        boxCentar.add(tezinaSmanjena);
        boxCentar.add(tezinaNormalna);
        boxCentar.add(tezinaPovecana);

        boxCentar.add(new JLabel("Pusac:"));
        boxCentar.add(pusacDa);
        boxCentar.add(pusacNe);

        boxCentar.add(new JLabel("Dijabeticar:"));
        boxCentar.add(dijabeticarDa);
        boxCentar.add(dijabeticarNe);

        boxRight.add(new JLabel("Asmaticar:"));
        boxRight.add(asmaticarDa);
        boxRight.add(asmaticarNe);

        boxRight.add(new JLabel("Fizicka aktivnost:"));
        boxRight.add(fizickaAktivnostDa);
        boxRight.add(fizickaAktivnostNe);

        trudnoca = new JLabel("Trudnoca:");
        boxRight.add(trudnoca);
        boxRight.add(trudnocaDa);
        boxRight.add(trudnocaNe);

        boxRight.add(new JLabel("Alergija:"));
        boxRight.add(alergijaDa);
        boxRight.add(alergijaNe);

        JButton dodajZK = new JButton("DODAJ");
        dodajZK.addActionListener(new DodajZdravstveniKartonListener());
        dodajZK.setPreferredSize(new Dimension(200,50));

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(100,50));
        boxCentar.add(panE);
        boxCentar.add(dodajZK);

        boxCentar.revalidate();
        boxCentar.repaint();
        boxRight.revalidate();
        boxRight.repaint();
    }

    public void OdabirPacijentaZaIzmenuZK(){

        boxCentar.removeAll();
        boxRight.removeAll();

        if(instance.getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            pacijenti = dodajPacijenteCBR();
        }else{
            pacijenti = dodajPacijenteRB();
        }

        JPanel pan1 = new JPanel();
        pan1.setLayout(new FlowLayout());
        cbPacijenti = new JComboBox();
        cbPacijenti.setPreferredSize(new Dimension(150,20));
        for( String ime: pacijenti) {
            cbPacijenti.addItem(ime);
        }
        pan1.add(cbPacijenti);

        JButton izmeniZK = new JButton("Izmeni zdravstveni karton");
        izmeniZK.setPreferredSize(new Dimension(200,30));
        izmeniZK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzmeniZKView();
            }
        });
        pan1.add(izmeniZK);

        boxCentar.add(pan1);

        boxCentar.revalidate();
        boxCentar.repaint();


    }

    public void IzmeniZKView(){

        boxCentar.removeAll();
        boxRight.removeAll();

        trenutnoAktivanPacijent = null;
        for(Pacijent pacijent:WelcomeWindow.getInstance().getListaPacijenata()){
            if(pacijent.getIme().equals(String.valueOf(MainWindow.getInstance().getCbPacijenti().getSelectedItem()))){
                trenutnoAktivanPacijent = pacijent;
            }
        }

        imePacijenta = new JTextField(trenutnoAktivanPacijent.getIme());
        imePacijenta.setEnabled(false);
        imePacijenta.setMaximumSize(new Dimension(400,30));

        ButtonGroup pol = new ButtonGroup();
        polZ = new JRadioButton("zenski");
        polM = new JRadioButton("muski");
        polM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(instance.getPolM().isSelected()){
//                    instance.getTrudnocaDa().setEnabled(false);
//                    instance.getTrudnocaNe().setEnabled(false);
                }else{
                    instance.getTrudnocaDa().setEnabled(true);
                    instance.getTrudnocaNe().setEnabled(true);
                }

            }
        });
        pol.add(polZ);
        pol.add(polM);
        if(trenutnoAktivanPacijent.getPol().equals(PolEnum.M)){
            polM.setSelected(true);
        }else{
            polZ.setSelected(true);
        }

        godinePacijenta = new JTextField(trenutnoAktivanPacijent.getGodine().toString());
        godinePacijenta.setMaximumSize(new Dimension(400,30));

        ButtonGroup tezina = new ButtonGroup();
        tezinaNormalna = new JRadioButton("normalna");
        tezinaPovecana = new JRadioButton("povecana");
        tezinaSmanjena = new JRadioButton("smanjena");
        tezina.add(tezinaNormalna);
        tezina.add(tezinaPovecana);
        tezina.add(tezinaSmanjena);
        if(trenutnoAktivanPacijent.getTezina().equals(TezinaEnum.normalnaTezina)){
            tezinaNormalna.setSelected(true);
        }else if(trenutnoAktivanPacijent.getTezina().equals(TezinaEnum.smanjenaTezina)){
            tezinaSmanjena.setSelected(true);
        }else{
            tezinaPovecana.setSelected(true);
        }

        ButtonGroup pusac = new ButtonGroup();
        pusacDa = new JRadioButton("da");
        pusacNe = new JRadioButton("ne");
        pusac.add(pusacDa);
        pusac.add(pusacNe);
        if(trenutnoAktivanPacijent.getPusac().equals(true)){
            pusacDa.setSelected(true);
        }else{
            pusacNe.setSelected(true);
        }

        ButtonGroup dijabeticar = new ButtonGroup();
        dijabeticarDa = new JRadioButton("da");
        dijabeticarNe = new JRadioButton("ne");
        dijabeticar.add(dijabeticarDa);
        dijabeticar.add(dijabeticarNe);
        if(trenutnoAktivanPacijent.getDijabeticar().equals(true)){
            dijabeticarDa.setSelected(true);
        }else{
            dijabeticarNe.setSelected(true);
        }

        ButtonGroup asmaticar = new ButtonGroup();
        asmaticarDa = new JRadioButton("da");
        asmaticarNe = new JRadioButton("ne");
        asmaticar.add(asmaticarDa);
        asmaticar.add(asmaticarNe);
        if(trenutnoAktivanPacijent.getAsmaticar().equals(true)){
            asmaticarDa.setSelected(true);
        }else{
            asmaticarNe.setSelected(true);
        }

        ButtonGroup fizickaAktivnost = new ButtonGroup();
        fizickaAktivnostDa = new JRadioButton("jeste");
        fizickaAktivnostNe = new JRadioButton("nije");
        fizickaAktivnost.add(fizickaAktivnostDa);
        fizickaAktivnost.add(fizickaAktivnostNe);
        if(trenutnoAktivanPacijent.getFizickaAktivnost().equals(true)){
            fizickaAktivnostDa.setSelected(true);
        }else{
            fizickaAktivnostNe.setSelected(true);
        }

        trudnocaButtonGroup = new ButtonGroup();
        trudnocaDa = new JRadioButton("da");
        trudnocaNe = new JRadioButton("ne");
        trudnocaButtonGroup.add(trudnocaDa);
        trudnocaButtonGroup.add(trudnocaNe);
        if(trenutnoAktivanPacijent.getTrudnoca().equals(true)){
            trudnocaDa.setSelected(true);
        }else{
            trudnocaNe.setSelected(true);
        }

        ButtonGroup alergija = new ButtonGroup();
        alergijaDa = new JRadioButton("ima");
        alergijaNe = new JRadioButton("nema");
        alergija.add(alergijaDa);
        alergija.add(alergijaNe);
        if(trenutnoAktivanPacijent.getAlergican().equals(true)){
            alergijaDa.setSelected(true);
        }else{
            alergijaNe.setSelected(true);
        }


        boxCentar.removeAll();
        boxRight.removeAll();
        boxCentar.add(new JLabel("Podaci o pacijentu"));
        boxCentar.add(new JLabel("Ime:"));
        boxCentar.add(imePacijenta);
        boxCentar.add(new JLabel("Pol:"));

        JPanel radioPol =  new JPanel();
        radioPol.setLayout(new FlowLayout());
        radioPol.add(polZ);
        radioPol.add(polM);

        boxCentar.add(polZ);
        boxCentar.add(polM);

        boxCentar.add(new JLabel("Godine:"));
        boxCentar.add(godinePacijenta);

        boxCentar.add(new JLabel("Tezina:"));
        boxCentar.add(tezinaSmanjena);
        boxCentar.add(tezinaNormalna);
        boxCentar.add(tezinaPovecana);

        boxCentar.add(new JLabel("Pusac:"));
        boxCentar.add(pusacDa);
        boxCentar.add(pusacNe);

        boxCentar.add(new JLabel("Dijabeticar:"));
        boxCentar.add(dijabeticarDa);
        boxCentar.add(dijabeticarNe);

        boxRight.add(new JLabel("Asmaticar:"));
        boxRight.add(asmaticarDa);
        boxRight.add(asmaticarNe);

        boxRight.add(new JLabel("Fizicka aktivnost:"));
        boxRight.add(fizickaAktivnostDa);
        boxRight.add(fizickaAktivnostNe);

        trudnoca = new JLabel("Trudnoca:");
        boxRight.add(trudnoca);
        boxRight.add(trudnocaDa);
        boxRight.add(trudnocaNe);

        boxRight.add(new JLabel("Alergija:"));
        boxRight.add(alergijaDa);
        boxRight.add(alergijaNe);

        JButton izmeniZK = new JButton("IZMENI");
        izmeniZK.addActionListener(new IzmeniZdravstveniKartonListener());
        izmeniZK.setPreferredSize(new Dimension(200,50));

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(100,50));
        boxCentar.add(panE);
        boxCentar.add(izmeniZK);

        boxCentar.revalidate();
        boxCentar.repaint();
        boxRight.revalidate();
        boxRight.repaint();
    }

    public ArrayList<String> dodajPacijenteCBR(){

        ArrayList<String> niz = new ArrayList<String>();
        for(Pacijent p:WelcomeWindow.getInstance().getListaPacijenata()){
            niz.add(p.getIme());
        }

        return niz;
    }

    public ArrayList<String> dodajPacijenteRB(){
        JIPQuery query = engine.openSynchronousQuery("pacijent(X)");
        ArrayList<String> niz = new ArrayList<String>();
        JIPTerm solution;
        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                niz.add(var.getValue().toString());
                System.out.println(var.getValue().toString());
            }
        }
        ucitajPrologFile();
        return niz;
    }

    public void ucitajPrologFile(){
        ArrayList<Pacijent> pacijenti = new ArrayList<>();

        //IMENA PACIJENATA I ID
        Integer i = 1;
        JIPQuery query = engine.openSynchronousQuery("pacijent(X)");
        JIPTerm solution;
        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                Pacijent pacijent = new Pacijent();
                pacijent.setId(i.longValue());
                pacijent.setIme(var.getValue().toString());
                i++;
                pacijenti.add(pacijent);
//                System.out.println(var.getValue().toString());
            }
        }

        //POL
        JIPQuery query2 = engine.openSynchronousQuery("pol(X, Y)");
        JIPTerm solution2;
        while ( (solution2 = query2.nextSolution()) != null  ) {
//            System.out.println(solution2);
            JIPVariable ime = solution2.getVariables()[0];
            JIPVariable pol = solution2.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("pol : "+ pol.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setPol(PolEnum.valueOf(pol.getValue().toString()));
                }
            }
        }

        //GODINE
        JIPQuery query3 = engine.openSynchronousQuery("godine(X, Y)");
        JIPTerm solution3;
        while ( (solution3 = query3.nextSolution()) != null  ) {
//            System.out.println(solution3);
            JIPVariable ime = solution3.getVariables()[0];
            JIPVariable godine = solution3.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("godine : "+ godine.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setGodine(Integer.parseInt(godine.getValue().toString()));
                }
            }
        }

        //PUSAC
        JIPQuery query4 = engine.openSynchronousQuery("pusac(X, Y)");
        JIPTerm solution4;
        while ( (solution4 = query4.nextSolution()) != null  ) {
//            System.out.println(solution4);
            JIPVariable ime = solution4.getVariables()[0];
            JIPVariable pusac = solution4.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("pusac : "+ pusac.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(pusac.getValue().toString().equals("da")){
                        p.setPusac(true);
                    }else{
                        p.setPusac(false);
                    }
                }
            }
        }

        //TEZINA
        JIPQuery query5 = engine.openSynchronousQuery("tezina(X, Y)");
        JIPTerm solution5;
        while ( (solution5 = query5.nextSolution()) != null  ) {
//            System.out.println(solution5);
            JIPVariable ime = solution5.getVariables()[0];
            JIPVariable tezina = solution5.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("tezina : "+ tezina.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setTezina(TezinaEnum.valueOf(tezina.getValue().toString()));
                }
            }
        }

        //DIJABETICAR
        JIPQuery query6 = engine.openSynchronousQuery("dijabeticar(X, Y)");
        JIPTerm solution6;
        while ( (solution6 = query6.nextSolution()) != null  ) {
//            System.out.println(solution6);
            JIPVariable ime = solution6.getVariables()[0];
            JIPVariable dijabeticar = solution6.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("dijabeticar : "+ dijabeticar.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(dijabeticar.getValue().toString().equals("da")){
                        p.setDijabeticar(true);
                    }else{
                        p.setDijabeticar(false);
                    }
                }
            }
        }

        //ASMATICAR
        JIPQuery query7 = engine.openSynchronousQuery("asmaticar(X, Y)");
        JIPTerm solution7;
        while ( (solution7 = query7.nextSolution()) != null  ) {
//            System.out.println(solution7);
            JIPVariable ime = solution7.getVariables()[0];
            JIPVariable asmaticar = solution7.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("asmaticar : "+ asmaticar.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(asmaticar.getValue().toString().equals("da")){
                        p.setAsmaticar(true);
                    }else{
                        p.setAsmaticar(false);
                    }
                }
            }
        }

        //FIZICKA AKTIVNOST
        JIPQuery query8 = engine.openSynchronousQuery("fizickaAktivnost(X, Y)");
        JIPTerm solution8;
        while ( (solution8 = query8.nextSolution()) != null  ) {
//            System.out.println(solution8);
            JIPVariable ime = solution8.getVariables()[0];
            JIPVariable fizickaAktivnost = solution8.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("fizickaAktivnost : "+ fizickaAktivnost.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(fizickaAktivnost.getValue().toString().equals("da")){
                        p.setFizickaAktivnost(true);
                    }else{
                        p.setFizickaAktivnost(false);
                    }
                }
            }
        }

        //TRUDNOCA
        JIPQuery query9 = engine.openSynchronousQuery("trudnoca(X, Y)");
        JIPTerm solution9;
        while ( (solution9 = query9.nextSolution()) != null  ) {
//            System.out.println(solution9);
            JIPVariable ime = solution9.getVariables()[0];
            JIPVariable trudnoca = solution9.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("trudnoca : "+ trudnoca.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(trudnoca.getValue().toString().equals("da")){
                        p.setTrudnoca(true);
                    }else{
                        p.setTrudnoca(false);
                    }
                }
            }
        }

        //ALERGICAN
        JIPQuery query10 = engine.openSynchronousQuery("alergican(X, Y)");
        JIPTerm solution10;
        while ( (solution10 = query10.nextSolution()) != null  ) {
//            System.out.println(solution10);
            JIPVariable ime = solution10.getVariables()[0];
            JIPVariable alergican = solution10.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("alergican : "+ alergican.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(alergican.getValue().toString().equals("da")){
                        p.setAlergican(true);
                    }else{
                        p.setAlergican(false);
                    }
                }
            }
        }

        //PORODICNE BOLESTI
        JIPQuery query11 = engine.openSynchronousQuery("porodicneBolesti(X, Y)");
        JIPTerm solution11;
        while ( (solution11 = query11.nextSolution()) != null  ) {

            JIPVariable ime = solution11.getVariables()[0];

            JIPEngine engine = new JIPEngine();
            JIPTermParser termParser = engine.getTermParser();

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    JIPList list = (JIPList)termParser.parseTerm(String.valueOf(solution11.getVariables()[1]));
                    p.getPorodicneBolesti().add(PorodicneBolesti.valueOf(String.valueOf(list.getHead())));
                    while(!list.getTail().toString().equals("[]")){
                        list = (JIPList)termParser.parseTerm(String.valueOf(list.getTail()));
                        p.getPorodicneBolesti().add(PorodicneBolesti.valueOf(String.valueOf(list.getHead())));
                    }
                }
            }
        }

        //ASKULTACIJA
        JIPQuery query12 = engine.openSynchronousQuery("auskultacija(X, Y)");
        JIPTerm solution12;
        while ( (solution12 = query12.nextSolution()) != null  ) {
//            System.out.println(solution12);
            JIPVariable ime = solution12.getVariables()[0];
            JIPVariable auskultacija = solution12.getVariables()[1];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("auskultacija : "+ auskultacija.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                   p.setAuskultacija(AuskultacijaEnum.valueOf(auskultacija.getValue().toString()));
                }
            }
        }

        //PRITISAK
        JIPQuery query13 = engine.openSynchronousQuery("pritisak(X, Y, Z)");
        JIPTerm solution13;
        while ( (solution13 = query13.nextSolution()) != null  ) {
//            System.out.println(solution13);
            JIPVariable ime = solution13.getVariables()[0];
            JIPVariable pritisakG = solution13.getVariables()[1];
            JIPVariable pritisakD = solution13.getVariables()[2];
//            System.out.println("ime : "+ ime.getValue().toString());
//            System.out.println("pritisakG : "+ pritisakG.getValue().toString());
//            System.out.println("pritisakD : "+ pritisakD.getValue().toString());
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setGornjiPritisak(Integer.parseInt(pritisakG.getValue().toString()));
                    p.setDonjiPritisak(Integer.parseInt(pritisakD.getValue().toString()));
                    RezPritiskaEnum rez = pacijentController.racunanjeRezultataPritiska(p.getGornjiPritisak(), p.getDonjiPritisak());
                    if(!rez.equals(null)){
                        p.setRezPritiska(rez);
                    }

                }
            }
        }

        //REZ ANALIZE KRVI
        JIPQuery query14 = engine.openSynchronousQuery("rezAnalizeKrvi(X, Y, Z, F)");
        JIPTerm solution14;
        while ( (solution14 = query14.nextSolution()) != null  ) {
            JIPVariable ime = solution14.getVariables()[0];
            JIPVariable nivoSeceraUKrvi = solution14.getVariables()[1];
            JIPVariable nivoHolesterola = solution14.getVariables()[2];
            JIPVariable nivoTriglecirida = solution14.getVariables()[3];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("nivoSeceraUKrvi : "+ nivoSeceraUKrvi.getValue().toString());
            System.out.println("nivoHolesterola : "+ nivoHolesterola.getValue().toString());
            System.out.println("nivoTriglecirida : "+ nivoTriglecirida.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(nivoSeceraUKrvi.getValue().toString());
                    rezultati.add(nivoHolesterola.getValue().toString());
                    rezultati.add(nivoTriglecirida.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, rezultati);
                }
            }
        }

        //REZ EKG
        JIPQuery query15 = engine.openSynchronousQuery("rezEkg(X, Y, Z)");
        JIPTerm solution15;
        while ( (solution15 = query15.nextSolution()) != null  ) {
            JIPVariable ime = solution15.getVariables()[0];
            JIPVariable nalaz = solution15.getVariables()[1];
            JIPVariable puls = solution15.getVariables()[2];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("nalaz : "+ nalaz.getValue().toString());
            System.out.println("puls : "+ puls.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(nalaz.getValue().toString());
                    rezultati.add(puls.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, rezultati);
                }
            }
        }

        //REZ ERGOMETRIJA
        JIPQuery query16 = engine.openSynchronousQuery("rezErgometrija(X, Y)");
        JIPTerm solution16;
        while ( (solution16 = query16.nextSolution()) != null  ) {
            JIPVariable ime = solution16.getVariables()[0];
            JIPVariable ergometrija = solution16.getVariables()[1];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("ergometrija : "+ ergometrija.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ergometrija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, rezultati);
                }
            }
        }

        //REZ EHOKARDIOGRAFIJA
        JIPQuery query17 = engine.openSynchronousQuery("rezEhokardiografije(X, Y)");
        JIPTerm solution17;
        while ( (solution17 = query17.nextSolution()) != null  ) {
            JIPVariable ime = solution17.getVariables()[0];
            JIPVariable ehokardiografija = solution17.getVariables()[1];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("ehokardiografija : "+ ehokardiografija.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ehokardiografija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, rezultati);
                }
            }
        }

        //REZ KORONARNA ANGIOGRAFIJA
        JIPQuery query18 = engine.openSynchronousQuery("rezKA(X, Y)");
        JIPTerm solution18;
        while ( (solution18 = query18.nextSolution()) != null  ) {
            JIPVariable ime = solution18.getVariables()[0];
            JIPVariable koronarnaAngiografija = solution18.getVariables()[1];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("koronarnaAngiografija : "+ koronarnaAngiografija.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(koronarnaAngiografija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, rezultati);
                }
            }
        }

        //REZ RENDGENA
        JIPQuery query19 = engine.openSynchronousQuery("rezRendgen(X, Y)");
        JIPTerm solution19;
        while ( (solution19 = query19.nextSolution()) != null  ) {
            JIPVariable ime = solution19.getVariables()[0];
            JIPVariable rendgena = solution19.getVariables()[1];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("rendgena : "+ rendgena.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(rendgena.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, rezultati);
                }
            }
        }

        //REZ HOLTER 24
        JIPQuery query20 = engine.openSynchronousQuery("rezHolter24(X, Y, Z, H)");
        JIPTerm solution20;
        while ( (solution20 = query20.nextSolution()) != null  ) {
            JIPVariable ime = solution20.getVariables()[0];
            JIPVariable srcanaFrekvencija = solution20.getVariables()[1];
            JIPVariable poremecajRitma = solution20.getVariables()[2];
            JIPVariable segment = solution20.getVariables()[3];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("srcanaFrekvencija : "+ srcanaFrekvencija.getValue().toString());
            System.out.println("poremecajRitma : "+ poremecajRitma.getValue().toString());
            System.out.println("segment : "+ segment.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(srcanaFrekvencija.getValue().toString());
                    rezultati.add(poremecajRitma.getValue().toString());
                    rezultati.add(segment.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, rezultati);
                }
            }
        }

        //REZ CT
        JIPQuery query21 = engine.openSynchronousQuery("rezCT(X, Y)");
        JIPTerm solution21;
        while ( (solution21 = query21.nextSolution()) != null  ) {
            JIPVariable ime = solution21.getVariables()[0];
            JIPVariable ct = solution21.getVariables()[1];

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("ct : "+ ct.getValue().toString());

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ct.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, rezultati);
                }
            }
        }

        //REZ
        JIPQuery query22 = engine.openSynchronousQuery("dijagnoza(X, Y)");
        JIPTerm solution22;
        while ( (solution22 = query22.nextSolution()) != null  ) {
            JIPVariable ime = solution22.getVariables()[0];
            JIPVariable dijagnoze = solution22.getVariables()[1];
//            JIPEngine engine = new JIPEngine();
//            JIPTermParser termParser = engine.getTermParser();
//
//            JIPList dijagnoze = (JIPList)termParser.parseTerm(String.valueOf(solution22.getVariables()[1]));

            System.out.println("ime : "+ ime.getValue().toString());
            System.out.println("dijagnoze : "+ dijagnoze.getValue().toString());


        }


        System.out.println("pacijentiii");
        WelcomeWindow.getInstance().setListaPacijenata(pacijenti);
        for(Pacijent p : WelcomeWindow.getInstance().getListaPacijenata()){
            System.out.println(p);
        }

    }

    public void zapocniPregledView(){
        boxCentar.removeAll();
        boxRight.removeAll();
        if(instance.getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            pacijenti = dodajPacijenteCBR();
        }else{
            pacijenti = dodajPacijenteRB();
        }


        JPanel pan1 = new JPanel();
        pan1.setLayout(new FlowLayout());
        cbPacijenti = new JComboBox();
        cbPacijenti.setPreferredSize(new Dimension(150,20));
        for( String ime: pacijenti) {
            cbPacijenti.addItem(ime);
        }
        pan1.add(cbPacijenti);


        JButton prikaziZK = new JButton("Prikazi zdravstveni karton");
        prikaziZK.setPreferredSize(new Dimension(200,30));
        prikaziZK.addActionListener(new ZapocniPregledListener());
        pan1.add(prikaziZK);

        boxCentar.add(pan1);

        boxCentar.revalidate();
        boxCentar.repaint();

    }

    public void prikaziInfoPacijenta(){

        boxCentar.removeAll();
        boxRight.removeAll();

        boxCentar.add(new JLabel("INFORMACIJE O PACIJENTU"));
        trenutnoAktivanPacijent = null;
        for(Pacijent pacijent:WelcomeWindow.getInstance().getListaPacijenata()){
            if(pacijent.getIme().equals(String.valueOf(MainWindow.getInstance().getCbPacijenti().getSelectedItem()))){
                trenutnoAktivanPacijent = pacijent;
            }
        }
        JLabel imeP = new JLabel(trenutnoAktivanPacijent.getIme());
        imeP.setFont(new Font("Tahoma", Font.BOLD, 18));

        boxCentar.add(imeP);
        boxCentar.add(new JLabel("Godine: " + String.valueOf(trenutnoAktivanPacijent.getGodine())));

        if(trenutnoAktivanPacijent.getTezina().equals(TezinaEnum.normalnaTezina)){
            boxCentar.add(new JLabel("Tezina: "+ "NORMALNA"));
        }else if(trenutnoAktivanPacijent.getTezina().equals(TezinaEnum.smanjenaTezina)){
            boxCentar.add(new JLabel("Tezina: "+ "SMANJENA"));
        }else{
            boxCentar.add(new JLabel("Tezina: "+ "POVECANA"));
        }

        if(trenutnoAktivanPacijent.getPusac().equals(true)){
            boxCentar.add(new JLabel("Pusac: " + "DA"));
        }else{
            boxCentar.add(new JLabel("Pusac: " + "NE"));
        }

        if(trenutnoAktivanPacijent.getDijabeticar().equals(true)){
            boxCentar.add(new JLabel("Dijabeticar: " + "DA"));
        }else{
            boxCentar.add(new JLabel("Dijabeticar: " + "NE"));
        }

        if(trenutnoAktivanPacijent.getAsmaticar().equals(true)){
            boxCentar.add(new JLabel("Asmaticar: " + "DA"));
        }else{
            boxCentar.add(new JLabel("Asmaticar: " + "NE"));
        }

        if(trenutnoAktivanPacijent.getFizickaAktivnost().equals(true)){
            boxCentar.add(new JLabel("Fizicka aktivnost: " + "DA"));
        }else{
            boxCentar.add(new JLabel("Fizicka aktivnost: " + "NE"));
        }

        if(trenutnoAktivanPacijent.getPol() == PolEnum.Z){
            if(trenutnoAktivanPacijent.getTrudnoca().equals(true)){
                boxCentar.add(new JLabel("Trudnoca: " + "DA"));
            }else{
                boxCentar.add(new JLabel("Trudnoca: " + "NE"));
            }

        }

        if(trenutnoAktivanPacijent.getAlergican().equals(true)){
            boxCentar.add(new JLabel("Alergija: " + "DA"));
        }else{
            boxCentar.add(new JLabel("Alergija: " + "NE"));
        }

        JButton zapocni = new JButton("ZAPOCNI");
        zapocni.setPreferredSize(new Dimension(200,30));
        zapocni.addActionListener(new FizikalniPregledListener());


        JButton predloziDodatnaIspitivanja = new JButton("Predlozi dodatna ispitivanja");
        predloziDodatnaIspitivanja.setPreferredSize(new Dimension(200,30));
        predloziDodatnaIspitivanja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodatnaIspitivanjaApp dia = new DodatnaIspitivanjaApp();

                try {
                    System.out.println("-----");
                    dia.configure();
                    dia.preCycle();
                    CBRQuery query = new CBRQuery();
                    Pacijent pacijent = new Pacijent();
                    pacijent.setPol(trenutnoAktivanPacijent.getPol());
                    pacijent.setGodine(trenutnoAktivanPacijent.getGodine());
                    pacijent.setRezPritiska(pacijentController.racunanjeRezultataPritiska(trenutnoAktivanPacijent.getGornjiPritisak(),
                            trenutnoAktivanPacijent.getDonjiPritisak()));
                    pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
                    pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
                    pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
                    pacijent.getListaSimptoma().add(Simptomi.umor);

                    pacijent.getPorodicneBolesti().add(PorodicneBolesti.infarktMiokarda);
                    java.util.List<String> l = new ArrayList<>();
                    l.add("10");
                    l.add("2.1");
                    l.add("3");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, l);
                    java.util.List<String> l2 = new ArrayList<>();
                    l2.add("nijeUredan");
                    l2.add("usporen");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, l2);
                    List<String> l3 = new ArrayList<>();
                    l3.add("nijeUredan");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, l3);


//            pacijent.getListaSimptoma().add(Simptomi.umor);

//            pacijent.setScore(5);
                    query.setDescription( pacijent );
                    dia.cycle(query);
                    dia.postCycle();

                    boxRight.removeAll();

                    for(DodatnaIspitivanjaEnum DI:dodatnaIspitivanja){
                        JLabel predlog = new JLabel(DI.name());
                        predlog.setFont(new Font("Tahoma", Font.BOLD, 18));
                        boxRight.add(predlog);
                    }

                    boxRight.revalidate();
                    boxRight.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton predloziDijagnoze = new JButton("Predlozi Dijagnoze");
        predloziDijagnoze.setPreferredSize(new Dimension(200,30));
        predloziDijagnoze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DijagnozeApp da = new DijagnozeApp();

                try {
                    System.out.println("-----");
                    da.configure();
                    da.preCycle();
                    CBRQuery query = new CBRQuery();
                    Pacijent pacijent = new Pacijent();
                    pacijent.setPol(trenutnoAktivanPacijent.getPol());
                    pacijent.setGodine(trenutnoAktivanPacijent.getGodine());
                    pacijent.setRezPritiska(pacijentController.racunanjeRezultataPritiska(130, 95));
                    pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
                    pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
                    pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
                    pacijent.getListaSimptoma().add(Simptomi.umor);

                    pacijent.getPorodicneBolesti().add(PorodicneBolesti.infarktMiokarda);
                    java.util.List<String> l = new ArrayList<>();
                    l.add("10");
                    l.add("2.1");
                    l.add("3");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, l);
                    java.util.List<String> l2 = new ArrayList<>();
                    l2.add("nijeUredan");
                    l2.add("usporen");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, l2);
                    List<String> l3 = new ArrayList<>();
                    l3.add("nijeUredan");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, l3);


//            pacijent.getListaSimptoma().add(Simptomi.umor);

//            pacijent.setScore(5);
                    query.setDescription( pacijent );
                    da.cycle(query);
                    da.postCycle();

                    boxRight.removeAll();

                    for(Dijagnoze dijagnoze:dijagnoze){
                        JLabel predlog = new JLabel(dijagnoze.name());
                        predlog.setFont(new Font("Tahoma", Font.BOLD, 18));
                        boxRight.add(predlog);
                    }

                    boxRight.revalidate();
                    boxRight.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton predloziTerapiju = new JButton("Predlozi Terapiju");
        predloziTerapiju.setPreferredSize(new Dimension(200,30));
        predloziTerapiju.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TerapijaApp ta = new TerapijaApp();

                try {
                    System.out.println("-----");
                    ta.configure();
                    ta.preCycle();
                    CBRQuery query = new CBRQuery();
                    Pacijent pacijent = new Pacijent();
                    pacijent.setPol(trenutnoAktivanPacijent.getPol());
                    pacijent.setGodine(trenutnoAktivanPacijent.getGodine());
                    pacijent.setRezPritiska(pacijentController.racunanjeRezultataPritiska(130, 95));
                    pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
                    pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
                    pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
                    pacijent.getListaSimptoma().add(Simptomi.umor);

                    pacijent.getPorodicneBolesti().add(PorodicneBolesti.infarktMiokarda);
                    java.util.List<String> l = new ArrayList<>();
                    l.add("10");
                    l.add("2.1");
                    l.add("3");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, l);
                    java.util.List<String> l2 = new ArrayList<>();
                    l2.add("nijeUredan");
                    l2.add("usporen");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, l2);
                    List<String> l3 = new ArrayList<>();
                    l3.add("nijeUredan");
                    pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, l3);


//            pacijent.getListaSimptoma().add(Simptomi.umor);

//            pacijent.setScore(5);
                    query.setDescription( pacijent );
                    ta.cycle(query);
                    ta.postCycle();

                    boxRight.removeAll();

                    for(Lekovi lek:terapija){
                        JLabel predlog = new JLabel(lek.name());
                        predlog.setFont(new Font("Tahoma", Font.BOLD, 18));
                        boxRight.add(predlog);
                    }

                    boxRight.revalidate();
                    boxRight.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel predlozi = new JPanel();
        predlozi.setLayout(new BoxLayout(predlozi,BoxLayout.Y_AXIS));

        JPanel panE1 = new JPanel();
        panE1.setPreferredSize(new Dimension(100,20));
        JPanel panE2 = new JPanel();
        panE2.setPreferredSize(new Dimension(100,20));
        JPanel panE3 = new JPanel();
        panE3.setPreferredSize(new Dimension(100,20));
        JPanel panE4 = new JPanel();
        panE4.setPreferredSize(new Dimension(100,20));

        predlozi.add(panE1);
        predlozi.add(zapocni);
        predlozi.add(panE2);
        predlozi.add(predloziDodatnaIspitivanja);
        predlozi.add(panE3);
        predlozi.add(predloziDijagnoze);
        predlozi.add(panE4);
        predlozi.add(predloziTerapiju);

        boxCentar.add(predlozi);



        boxCentar.revalidate();
        boxCentar.repaint();

    }

    public JComboBox getCbPacijenti() {
        return cbPacijenti;
    }

    public JList getListaSimptoma() {
        return listaSimptoma;
    }

    public JButton getPrevious() {
        return previous;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getNext() {
        return next;
    }

    public JIPEngine getEngine() {
        return engine;
    }

    public Box getBoxCentar() {
        return boxCentar;
    }

    public Box getBoxRight() {
        return boxRight;
    }

    public JTextField getImePacijenta() {
        return imePacijenta;
    }

    public JTextField getGodinePacijenta() {
        return godinePacijenta;
    }

    public JRadioButton getPolZ() {
        return polZ;
    }

    public JRadioButton getPolM() {
        return polM;
    }

    public JRadioButton getPusacDa() {
        return pusacDa;
    }

    public JRadioButton getPusacNe() {
        return pusacNe;
    }

    public JRadioButton getDijabeticarDa() {
        return dijabeticarDa;
    }

    public JRadioButton getDijabeticarNe() {
        return dijabeticarNe;
    }

    public JRadioButton getTezinaPovecana() {
        return tezinaPovecana;
    }

    public JRadioButton getTezinaSmanjena() {
        return tezinaSmanjena;
    }

    public JRadioButton getTezinaNormalna() {
        return tezinaNormalna;
    }

    public JRadioButton getAsmaticarDa() {
        return asmaticarDa;
    }

    public JRadioButton getAsmaticarNe() {
        return asmaticarNe;
    }

    public JRadioButton getFizickaAktivnostDa() {
        return fizickaAktivnostDa;
    }

    public JRadioButton getFizickaAktivnostNe() {
        return fizickaAktivnostNe;
    }

    public JRadioButton getTrudnocaDa() {
        return trudnocaDa;
    }

    public JRadioButton getTrudnocaNe() {
        return trudnocaNe;
    }

    public JRadioButton getAlergijaDa() {
        return alergijaDa;
    }

    public JRadioButton getAlergijaNe() {
        return alergijaNe;
    }

    public Pacijent getTrenutnoAktivanPacijent() {
        return trenutnoAktivanPacijent;
    }

    public void setTrenutnoAktivanPacijent(Pacijent trenutnoAktivanPacijent) {
        this.trenutnoAktivanPacijent = trenutnoAktivanPacijent;
    }

    public ArrayList<String> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(ArrayList<String> pacijenti) {
        this.pacijenti = pacijenti;
    }

    public ArrayList<Lekovi> getTerapija() {
        return terapija;
    }

    public void setTerapija(ArrayList<Lekovi> terapija) {
        this.terapija = terapija;
    }

    public ArrayList<Dijagnoze> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(ArrayList<Dijagnoze> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public ArrayList<DodatnaIspitivanjaEnum> getDodatnaIspitivanja() {
        return dodatnaIspitivanja;
    }

    public void setDodatnaIspitivanja(ArrayList<DodatnaIspitivanjaEnum> dodatnaIspitivanja) {
        this.dodatnaIspitivanja = dodatnaIspitivanja;
    }

    public JPanel getPanCenter() {
        return panCenter;
    }

    public void setPanCenter(JPanel panCenter) {
        this.panCenter = panCenter;

    }

    public IzabranaOpcija getIzabranaOpcija() {
        return izabranaOpcija;
    }

    public void setIzabranaOpcija(IzabranaOpcija izabranaOpcija) {
        this.izabranaOpcija = izabranaOpcija;
    }

    public JLabel getStatusLinija() {
        return statusLinija;
    }

    public void setStatusLinija(JLabel statusLinija) {
        this.statusLinija = statusLinija;
    }

    public JLabel getTrudnoca() {
        return trudnoca;
    }

    public void setTrudnoca(JLabel trudnoca) {
        this.trudnoca = trudnoca;
    }

    public ButtonGroup getTrudnocaButtonGroup() {
        return trudnocaButtonGroup;
    }

    public void setTrudnocaButtonGroup(ButtonGroup trudnocaButtonGroup) {
        this.trudnocaButtonGroup = trudnocaButtonGroup;
    }
}
