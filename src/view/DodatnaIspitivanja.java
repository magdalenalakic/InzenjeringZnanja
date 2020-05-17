package view;

import com.sun.deploy.panel.JavaPanel;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import controller.DodajZdravstveniKartonListener;
import model.Pacijent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
//import com.ugos.jiprolog.engine.JIPEngine;
//import com.ugos.jiprolog.engine.JIPQuery;
//import com.ugos.jiprolog.engine.JIPTerm;
//import com.ugos.jiprolog.engine.JIPVariable;

public class DodatnaIspitivanja extends JFrame {
    private List<Pacijent> listaPacijenata = new ArrayList<>();
    private static DodatnaIspitivanja instance = null;
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


//    private List<>

    public static DodatnaIspitivanja getInstance(){
        if (instance == null) {
            instance = new DodatnaIspitivanja();
            instance.ucitajPrologFajlove();
            instance.initialise();

        }
        return instance;
    }
    public void ucitajPrologFajlove(){
        engine.consultFile("prolog/projekat.pl");
    }

    public void initialise(){

        ArrayList<String> pacijenti = dodajPacijente();

        setSize(1000, 700);
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
        panTop.setPreferredSize(new Dimension(100,100));
        panTop.setBackground(new Color(32, 255, 140));
        add(panTop, BorderLayout.NORTH);

//        JPanel panTop = new JPanel();
//        panTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel licence = new JLabel("Izaberi Pacijenta");
        licence.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
//        panTop.add(licence);
        JPanel panPacijenti = new JPanel();

        cbPacijenti = new JComboBox();
//        ArrayList<String> pacijenti = new ArrayList<String>();
//        pacijenti.add("Milan");
//        pacijenti.add("Milica");
//        pacijenti.add("Petar");

        for( String ime: pacijenti) {
            cbPacijenti.addItem(ime);
        }
        ArrayList<String> simptomi = new ArrayList<String>();
        simptomi.add("otezano disanje");
        simptomi.add("bol u grudima");
        listaSimptoma = new JList(simptomi.toArray());
        listaSimptoma.setSize(100,20);
        listaSimptoma.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panPacijenti.add(cbPacijenti);
        JPanel panCenter = new JPanel();

        boxCentar = Box.createVerticalBox();
        boxCentar.setBackground(new Color(255, 200, 56));
        boxCentar.add(Box.createVerticalStrut(20));
//        boxCentar.add(panTop);
        boxCentar.add(panPacijenti);
//        boxCentar.add(new Label("Izaberite simptome"));
        boxCentar.add(listaSimptoma);

        boxCentar.add(Box.createGlue());
        panCenter.add(boxCentar);

        add(panCenter, BorderLayout.CENTER);



        JPanel panRight = new JPanel();
        panRight.setPreferredSize(new Dimension(250,200));
        panRight.setBackground(new Color(249, 128, 255));
        add(panRight, BorderLayout.EAST);

        boxRight = Box.createVerticalBox();
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(Box.createGlue());
        boxRight.add(new JLabel("DESNOOOOOOOOOOOO:"));
        panRight.add(boxRight);
//        revalidate();
//        repaint();

        JPanel panLeft = new JPanel();
        panLeft.setPreferredSize(new Dimension(250,200));
        panLeft.setBackground(new Color(240, 255, 107));
        panLeft.add(new JLabel("LABELA"));
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


        JButton zapocni = new JButton("Zapocni pregled");
        zapocni.setPreferredSize(new Dimension(200,30));
        panLeft.add(dodaj);
        panLeft.add(izmeni);
        panLeft.add(zapocni);
        add(panLeft, BorderLayout.WEST);
//        add(panLeft, FlowLayout.LEFT);

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
                MainWindow wz1 = MainWindow.getInstance();
                wz1.setVisible(true);
                instance.setVisible(false);

            }
        });
        panBtm.add(cancel);
//        panBtm.add(previous);
//        panBtm.add(next);
        add(panBtm, BorderLayout.SOUTH);
    }

    public void dodajZdravstveniKartonView(){
        imePacijenta = new JTextField();
        imePacijenta.setMaximumSize(new Dimension(400,30));
//                imePacijenta.setColumns(10);
        ButtonGroup pol = new ButtonGroup();
        polZ = new JRadioButton("zenski");
        polM = new JRadioButton("muski");
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

        ButtonGroup trudnoca = new ButtonGroup();
        trudnocaDa = new JRadioButton("da");
        trudnocaNe = new JRadioButton("ne");
        trudnoca.add(trudnocaDa);
        trudnoca.add(trudnocaNe);

        ButtonGroup alergija = new ButtonGroup();
        alergijaDa = new JRadioButton("ima");
        alergijaNe = new JRadioButton("nema");
        alergija.add(alergijaDa);
        alergija.add(alergijaNe);

        System.out.println("AKCIJAA");



        boxCentar.removeAll();
        boxRight.removeAll();
        boxCentar.add(new JLabel("Podaci o pacijentu"));
        boxCentar.add(new JLabel("Ime:"));
        boxCentar.add(imePacijenta);
        boxCentar.add(new JLabel("Pol:"));
        JPanel radioPol =  new JPanel();
        radioPol.setLayout(new FlowLayout());
//                boxCentar.add(polZ);
//                boxCentar.add(polM);
        radioPol.add(polZ);
        radioPol.add(polM);
//        add(radioPol);
//        revalidate();
//        repaint();
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
        boxRight.add(new JLabel("Trudnoca:"));
        boxRight.add(trudnocaDa);
        boxRight.add(trudnocaNe);
        boxRight.add(new JLabel("Alergija:"));
        boxRight.add(alergijaDa);
        boxRight.add(alergijaNe);
        JButton dodajZK = new JButton("DODAJ");
        boxCentar.add(dodajZK);
        dodajZK.addActionListener(new DodajZdravstveniKartonListener());
        boxCentar.revalidate();
        boxCentar.repaint();
        boxRight.revalidate();
        boxRight.repaint();
    }

    public ArrayList<String> dodajPacijente(){
        JIPQuery query = engine.openSynchronousQuery("pacijent(X)");
        ArrayList<String> niz = new ArrayList<String>();
        JIPTerm solution;
        System.out.println("ISPISSS");
        while ( (solution = query.nextSolution()) != null  ) {
            //System.out.println("solution: " + solution);
            for (JIPVariable var: solution.getVariables()) {
                niz.add(var.getValue().toString());
                System.out.println(var.getValue().toString());
            }
        }
        return niz;


//        //labela pacijent
//        lblPatient = new JLabel("Patient");
//        lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblPatient.setBounds(25, 10, 105, 57);
//        panel_1.add(lblPatient);
//
//        //ComboBox sa pacijentima
//        comboBoxPatient = new JComboBox();
//        comboBoxPatient.setBounds(131, 25, 127, 34);
//        panel_1.add(comboBoxPatient);
//
//        for( String ime: niz) {
//            comboBoxPatient.addItem(ime);
//        }
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

    public List<Pacijent> getListaPacijenata() {
        return listaPacijenata;
    }

    public void setListaPacijenata(List<Pacijent> listaPacijenata) {
        this.listaPacijenata = listaPacijenata;
    }
}
