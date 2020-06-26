package view;

import controller.DodajZdravstveniKartonListener;
import controller.FizikalniPregledListener;
import controller.IzmeniZdravstveniKartonListener;
import controller.PacijentController;
import controller.ZapocniPregledListener;
import model.*;

//import javax.management.Query;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.Integer;
import java.util.*;
import java.util.List;


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
    private ArrayList<String> pacijenti  = new ArrayList<>();
    private ArrayList<Lekovi> terapija = new ArrayList<>();
    private ArrayList<Dijagnoze> dijagnoze = new ArrayList<>();
    private ArrayList<DodatnaIspitivanjaEnum> dodatnaIspitivanja = new ArrayList<>();
    private  JPanel panCenter;
    private BufferedImage image;


    private JLabel statusLinija;
    private JLabel trudnoca;
    private ButtonGroup trudnocaButtonGroup;


    public static MainWindow getInstance(){
        if (instance == null) {
            instance = new MainWindow();
            instance.initialise();
        }
        return instance;
    }

    public void initialise()  {

        try {
            image = ImageIO.read(new File("images/download.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        previous = new JButton("Vrati se");
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
        if(instance.getStatusLinija().getText().equals("Pacijent uspesno izmenjen!") ||
                instance.getStatusLinija().getText().equals("Podaci su sacuvani!") ||
                instance.getStatusLinija().getText().equals("Rezultati ispitivanja uspesno sacuvani!")){
            instance.getStatusLinija().setText("");
        }
        imePacijenta = new JTextField();
        imePacijenta.setMaximumSize(new Dimension(400,30));

        ButtonGroup pol = new ButtonGroup();
        polZ = new JRadioButton("zenski");
        polM = new JRadioButton("muski");
        polM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(instance.getPolM().isSelected()){
                    instance.getTrudnocaNe().setSelected(true);
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
        pacijenti = new ArrayList<>();

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
        if(instance.getStatusLinija().getText().equals("Pacijent uspesno dodat!") ||
                instance.getStatusLinija().getText().equals("Podaci su sacuvani!") ||
                instance.getStatusLinija().getText().equals("Rezultati ispitivanja uspesno sacuvani!")){
            instance.getStatusLinija().setText("");
        }

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
        if(trenutnoAktivanPacijent.getPol().equals(PolEnum.M)){
            instance.getTrudnocaDa().setEnabled(false);
            instance.getTrudnocaNe().setEnabled(false);
        }else{
            if(trenutnoAktivanPacijent.getTrudnoca().equals(true)){
                trudnocaDa.setSelected(true);
            }else{
                trudnocaNe.setSelected(true);
            }
        }
        polM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(instance.getPolM().isSelected()){
                    instance.getTrudnocaNe().setSelected(true);
                    instance.getTrudnocaDa().setEnabled(false);
                    instance.getTrudnocaNe().setEnabled(false);
                }else{
                    instance.getTrudnocaDa().setEnabled(true);
                    instance.getTrudnocaNe().setEnabled(true);
                }

            }
        });

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
        ArrayList<String> niz = new ArrayList<String>();
        for(Pacijent p: WelcomeWindow.getInstance().getListaPacijenata()){
            niz.add(p.getIme());
        }
        return niz;
    }

    public void upisiUPrologFile(String provera, String linija) throws IOException {
        System.out.println("------------- CITANJE I PISANJE -----------------");
        List<String> prologFajl = new ArrayList<>();
        try{
            File file=new File("prolog/projekat.pl");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                prologFajl.add(line);
            }
            fr.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Boolean flag = false;
        int izmena = -1;
        for(int i = 0 ; i < prologFajl.size(); i++){
            if(prologFajl.get(i).contains(provera)){
                flag = true;
                izmena = i;
            }
        }
        if(izmena != -1){
            prologFajl.set(izmena, linija);
        }

        if(flag == true){
            try {
                FileWriter exampleFileWriter = new FileWriter("prolog/projekat.pl");
                for(int i = 0; i < prologFajl.size(); i++){
                    if(i == prologFajl.size()-1){
                        exampleFileWriter.append(prologFajl.get(i) );
                    }else{
                        exampleFileWriter.append(prologFajl.get(i) + "\n" );
                    }

                }
                exampleFileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FileWriter exampleFileWriter = new FileWriter("prolog/projekat.pl", true);
                exampleFileWriter.append("\n" +linija);
                exampleFileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upisiFizikalniPregledCBR(){
        //"csv-files/fizikalni-pregled.csv"
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/fizikalni-pregled.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;ime;pol;godine;tezina;pusac;dijabeticar;asmaticar;fizickaAktivnost;trudnoca;alergican;auskultacija;gornjiPritisak;donjiPritisak;listaSimptoma;porodicneBolesti\n");
            Integer fl = 0;
            for(Pacijent pacijent : WelcomeWindow.getInstance().getListaPacijenata()){
                fl++;
                String line = pacijent.getId() + ";";
                line += pacijent.getIme() + ";";
                line += pacijent.getPol() + ";";
                line += pacijent.getGodine() + ";";
                line += pacijent.getTezina() + ";";
                if(pacijent.getPusac()){
                    line +=  "da;";
                }else if(!pacijent.getPusac()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getDijabeticar()){
                    line +=  "da;";
                }else if(!pacijent.getDijabeticar()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getAsmaticar()){
                    line +=  "da;";
                }else if(!pacijent.getAsmaticar()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getFizickaAktivnost()){
                    line +=  "da;";
                }else if(!pacijent.getFizickaAktivnost()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getTrudnoca()){
                    line +=  "da;";
                }else if(!pacijent.getTrudnoca()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getAlergican()){
                    line +=  "da;";
                }else if(!pacijent.getAlergican()){
                    line +=  "ne;";
                }else{
                    line += " ;";
                }

                if(pacijent.getAuskultacija() != null){
                    line += pacijent.getAuskultacija() + ";";
                }else{
                    line += " ;";
                }

                if(pacijent.getGornjiPritisak() != null){
                    line += pacijent.getGornjiPritisak() + ";";
                }else{
                    line += " ;";
                }

                if(pacijent.getDonjiPritisak() != null){
                    line += pacijent.getDonjiPritisak() + ";";
                }else{
                    line += " ;";
                }

                Integer flag = 0;
                if(pacijent.getListaSimptoma().isEmpty()){
                    line += " ;";
                }else{
                    for(Simptomi simptom : pacijent.getListaSimptoma()){
                        flag++;
                        if(flag == pacijent.getListaSimptoma().size()){
                            line += simptom + ";";
                        }else{
                            line += simptom + ",";
                        }
                    }
                }

                flag = 0;
                if(pacijent.getPorodicneBolesti().isEmpty()){
                    line += " ;";
                }else{
                    for(PorodicneBolesti bolest : pacijent.getPorodicneBolesti()){
                        flag++;
                        if(flag == pacijent.getPorodicneBolesti().size()){
                            line += bolest;
                        }else{
                            line += bolest + ",";
                        }
                    }
                }

                if(fl != WelcomeWindow.getInstance().getListaPacijenata().size()){
                    line += "\n";
                }
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Fizikalni pregledi sacuvani u csv fajl");
    }

    public void upisiDodatnaIspitivanjaCBR(){
        //"csv-files/dodatna-ispitivanja.csv"
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/dodatna-ispitivanja.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaRezultataDodatnihIspitivanja\n");
            Integer fl = 0;
            for(Pacijent pacijent : WelcomeWindow.getInstance().getListaPacijenata()){
                fl++;
                String line = pacijent.getId() + ";";
                if(pacijent.getListaRezultataDodatnihIspitivanja().isEmpty()){
                    line += " ;";
                }else{
                    Integer flag = 0;
                    for(DodatnaIspitivanjaEnum die : pacijent.getListaRezultataDodatnihIspitivanja().keySet()){
                        flag++;
                        if(flag == pacijent.getListaRezultataDodatnihIspitivanja().size()){
                            line += die + "=";
                            Integer flag2 = 0;
                            for(String s : pacijent.getListaRezultataDodatnihIspitivanja().get(die)){
                                flag2++;
                                if(flag2 == pacijent.getListaRezultataDodatnihIspitivanja().get(die).size()){
                                    line += s;
                                }else{
                                    line += s +"&";
                                }
                            }
                        }else{
                            line += die + "=";
                            Integer flag2 = 0;
                            for(String s : pacijent.getListaRezultataDodatnihIspitivanja().get(die)){
                                flag2++;
                                if(flag2 == pacijent.getListaRezultataDodatnihIspitivanja().get(die).size()){
                                    line += s;
                                }else{
                                    line += s +"&";
                                }
                            }
                            line += ",";
                        }
                    }
                }

                if(fl != WelcomeWindow.getInstance().getListaPacijenata().size()){
                    line += "\n";
                }
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Dodatna ispitivanja sacuvana u csv fajl");
    }

    public void upisiDijagnozeCBR(){
        //"csv-files/dijagnoza.csv"
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/dijagnoza.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaDijagnoza\n");
            Integer fl = 0;
            for(Pacijent pacijent :  WelcomeWindow.getInstance().getListaPacijenata()){
                fl++;
                String line = pacijent.getId() + ";";

                if(pacijent.getListaDijagnoza().isEmpty()){
                    line += " ;";
                }else{
                    Integer flag = 0;
                    for(Dijagnoze dijag : pacijent.getListaDijagnoza()){
                        flag++;
                        if(flag == pacijent.getListaDijagnoza().size()){
                            line += dijag;
                        }else{
                            line += dijag + ",";
                        }
                    }
                }

                if(fl !=  WelcomeWindow.getInstance().getListaPacijenata().size()){
                    line += "\n";
                }
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Dijagnoze sacuvane u csv fajl");
    }

    public void upisiLekoveCBR(){
        //"csv-files/lekovi.csv"
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/lekovi.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaTerapija\n");
            Integer fl = 0;
            for(Pacijent pacijent : WelcomeWindow.getInstance().getListaPacijenata()){
                fl++;
                String line = pacijent.getId() + ";";
                Integer flag = 0;
                if(pacijent.getListaLekova().isEmpty()){
                    line += " ;";
                }else{
                    for(Lekovi lek : pacijent.getListaLekova()){
                        flag++;
                        if(flag == pacijent.getListaLekova().size()){
                            line += lek;
                        }else{
                            line += lek + ",";
                        }
                    }
                }

                if(fl != WelcomeWindow.getInstance().getListaPacijenata().size()){
                    line += "\n";
                }
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Lekovi sacuvani u csv fajl");
    }

    public void zapocniPregledView(){
        instance.getStatusLinija().setText("");
        boxCentar.removeAll();
        boxRight.removeAll();
        pacijenti = new ArrayList<>();
        if(instance.getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            pacijenti = dodajPacijenteCBR();
        }else{
            pacijenti = dodajPacijenteRB();
        }


        JPanel pan1 = new JPanel();
        pan1.setLayout(new FlowLayout());
        cbPacijenti = new JComboBox();
        cbPacijenti.removeAllItems();
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
                UnesiRezDIWindow.getInstance().setUnetiRezDI(new ArrayList<>());
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


        JPanel predlozi = new JPanel();
        predlozi.setLayout(new BoxLayout(predlozi,BoxLayout.Y_AXIS));

        JPanel panE1 = new JPanel();
        panE1.setPreferredSize(new Dimension(100,20));

        predlozi.add(panE1);
        predlozi.add(zapocni);

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
