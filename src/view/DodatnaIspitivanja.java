package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
//import com.ugos.jiprolog.engine.JIPEngine;
//import com.ugos.jiprolog.engine.JIPQuery;
//import com.ugos.jiprolog.engine.JIPTerm;
//import com.ugos.jiprolog.engine.JIPVariable;

public class DodatnaIspitivanja extends JFrame {
    private static DodatnaIspitivanja instance = null;
    private JComboBox cbPacijenti;
    private JList listaSimptoma;
    private JButton previous;
    private JButton cancel;
    private JButton next;
//    private JIPEngine engine = new JIPEngine();

    public static DodatnaIspitivanja getInstance(){
        if (instance == null) {
            instance = new DodatnaIspitivanja();
            instance.initialise();
        }
        return instance;
    }
    public void initialise(){
        setSize(850, 550);
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
        panTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel licence = new JLabel("Izaberi Pacijenta");
        licence.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        panTop.add(licence);
        JPanel panPacijenti = new JPanel();
        cbPacijenti = new JComboBox();
        ArrayList<String> pacijenti = new ArrayList<String>();
        pacijenti.add("Milan");
        pacijenti.add("Milica");
        pacijenti.add("Petar");

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
        Box boxCentar = Box.createVerticalBox();
        boxCentar.add(Box.createVerticalStrut(20));
        boxCentar.add(panTop);
        boxCentar.add(panPacijenti);
        boxCentar.add(new Label("Izaberite simptome"));
        boxCentar.add(listaSimptoma);

        boxCentar.add(Box.createGlue());

        add(boxCentar, BorderLayout.CENTER);

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
        panBtm.add(previous);
        panBtm.add(next);
        add(panBtm, BorderLayout.SOUTH);
    }
    public void dodajPacijente(){
//        JIPQuery query = engine.openSynchronousQuery("person(X)");
//        ArrayList<String> niz= new ArrayList<String>();
//        JIPTerm solution;
//
//        while ( (solution = query.nextSolution()) != null  ) {
//            //System.out.println("solution: " + solution);
//            for (JIPVariable var: solution.getVariables()) {
//                niz.add(var.getValue().toString());
//            }
//        }
//
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
}
