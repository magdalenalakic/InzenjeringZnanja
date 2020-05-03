package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame {


    private static MainWindow instance = null;
    private JButton next;
    private JButton cancel;



    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
            instance.init();
        }

        return instance;
    }


    public void setInstance(MainWindow newInstance) {
        instance = newInstance;
    }



    public void init() {


        setSize(350, 350);
        setTitle("Dobrodosli");
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

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(100, 150));
        add(empty, BorderLayout.NORTH);

        JPanel panTop = new JPanel();
        panTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcome = new JLabel("Dobrodosli");
        welcome.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        panTop.add(welcome);
        add(panTop, BorderLayout.CENTER);

        JPanel panBtm = new JPanel();
        panBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        next = new JButton("Nastavi");
        next.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        next.setPreferredSize(new Dimension(95,25));
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DodatnaIspitivanja wz2 = DodatnaIspitivanja.getInstance();
                wz2.setVisible(true);
                instance.setVisible(false);

            }
        });
        cancel = new JButton("Izadji");
        cancel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        cancel.setPreferredSize(new Dimension(95,25));
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
        panBtm.add(cancel);
        panBtm.add(next);
        add(panBtm, BorderLayout.SOUTH);

    }


}
