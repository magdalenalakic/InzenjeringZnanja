package view;

import controller.CBRCheckedListener;
import controller.RBCheckedListener;
import model.Pacijent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WelcomeWindow extends JFrame {


    private static WelcomeWindow instance = null;
    private JButton next;
    private JButton cancel;
    private BufferedImage image;
    private List<Pacijent> listaPacijenata = new ArrayList<>();



    public static WelcomeWindow getInstance() {
        if (instance == null) {
            instance = new WelcomeWindow();
            instance.init();
        }

        return instance;
    }


    public void setInstance(WelcomeWindow newInstance) {
        instance = newInstance;
    }



    public void init(){

        try {
            image = ImageIO.read(new File("images/download.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(100, 30));
        Box horBox = Box.createHorizontalBox();
        JPanel panL = new JPanel();
        JPanel panR = new JPanel();
//        panL.setBackground(new Color(207, 255, 142));
        panL.setPreferredSize(new Dimension(150,100));
//        panR.setBackground(new Color(255,0,0));
        panR.setPreferredSize(new Dimension(150,100));
        JButton button1 = new JButton("CBR");
        button1.addActionListener(new CBRCheckedListener());
        JButton button2 = new JButton("RB");
        button2.addActionListener(new RBCheckedListener());
        button1.setPreferredSize(new Dimension(100,240));

        button2.setPreferredSize(new Dimension(100,240));

        panL.add(button1, BorderLayout.CENTER);
        panR.add(button2, BorderLayout.CENTER);
        JLabel picLabel1 = new JLabel(new ImageIcon(image));
        picLabel1.setPreferredSize(new Dimension(80,30));
        JLabel picLabel2 = new JLabel(new ImageIcon(image));
        picLabel2.setPreferredSize(new Dimension(80,30));
//        panL.add(picLabel1, BorderLayout.SOUTH);
//        panR.add(picLabel2, BorderLayout.SOUTH);
//        Box verBox1 = Box.createVerticalBox();
//        Box verBox2 = Box.createVerticalBox();
//        verBox1.add(button1);
//        verBox2.add(button2);
//        verBox1.add(picLabel1);
//        verBox2.add(picLabel2);
//        verBox1.add(Box.createGlue());
//        verBox2.add(Box.createGlue());
//        panL.add(verBox1);
//        panR.add(verBox2);
        horBox.add(panL);
        horBox.add(panR);
        horBox.add(Box.createGlue());
//        add(panL, BorderLayout.EAST);
//        add(panR, BorderLayout.WEST);
        add(empty2, BorderLayout.NORTH);
        add(horBox, BorderLayout.CENTER);
        setSize(300, 350);

//        setTitle("Dobrodosli");
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
        empty.setPreferredSize(new Dimension(100, 30));
//        add(empty, BorderLayout.NORTH);
//        setContentPane(new JLabel(new ImageIcon(image)));

        JPanel panTop = new JPanel();
        panTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcome = new JLabel("Dobrodosli");
//        JLabel picLabel = new JLabel(new ImageIcon(image));
        welcome.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
//        panTop.add(picLabel);
        panTop.add(welcome);
//        add(panTop, BorderLayout.CENTER);

        JPanel panBtm = new JPanel();
        panBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        next = new JButton("Nastavi");
        next.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        next.setPreferredSize(new Dimension(95,25));
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow wz2 = MainWindow.getInstance();
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
//        panBtm.add(cancel);
//        panBtm.add(next);
        add(panBtm, BorderLayout.SOUTH);

    }


    public List<Pacijent> getListaPacijenata() {
        return listaPacijenata;
    }

    public void setListaPacijenata(List<Pacijent> listaPacijenata) {
        this.listaPacijenata = listaPacijenata;
    }
}
