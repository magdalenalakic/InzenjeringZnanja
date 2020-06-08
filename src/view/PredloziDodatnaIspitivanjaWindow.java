package view;

import controller.PredloziDodatnaIspitivanjaListener;
import controller.UnesiRezDIListener;
import main.DodatnaIspitivanjaApp;
import model.DodatnaIspitivanjaEnum;
import model.PorodicneBolesti;
import model.Simptomi;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PredloziDodatnaIspitivanjaWindow  extends JFrame {

    private static PredloziDodatnaIspitivanjaWindow instance = null;
    private JButton next;
    private JButton cancel;


    public static PredloziDodatnaIspitivanjaWindow getInstance() {
        if (instance == null) {
            instance = new PredloziDodatnaIspitivanjaWindow();
//            instance.init();
        }

        return instance;
    }


    public void setInstance(PredloziDodatnaIspitivanjaWindow newInstance) {
        instance = newInstance;
    }


    public void init() {

//        MainWindow.getInstance().getBoxCentar().removeAll();
//        MainWindow.getInstance().getBoxRight().removeAll();
//
//        JLabel naslov = new JLabel("- DODATNA ISPITIVANJA -");
//        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
//        MainWindow.getInstance().getBoxCentar().add(naslov);

        JLabel naslov = new JLabel("- DODATNA ISPITIVANJA -");
        naslov.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        MainWindow.getInstance().getBoxCentar().add(naslov);

        DodatnaIspitivanjaApp dia = new DodatnaIspitivanjaApp();

        try {
            System.out.println("UDJE LI VODJEEEEEEEEEEEEEE");
            dia.configure();
            dia.preCycle();
            CBRQuery query = new CBRQuery();


            System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().toString());

            query.setDescription(  MainWindow.getInstance().getTrenutnoAktivanPacijent() );
            dia.cycle(query);
            dia.postCycle();

            JPanel panel = new JPanel();
            LayoutManager layout = new FlowLayout();
            panel.setLayout(layout);

            List<DodatnaIspitivanjaEnum> lis = new ArrayList<>();
            for(DodatnaIspitivanjaEnum DI:MainWindow.getInstance().getDodatnaIspitivanja()){
                lis.add(DI);
            }

            JList<DodatnaIspitivanjaEnum> listBox = new JList(lis.toArray());

            JPanel pan = new JPanel();
            pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
            pan.setBackground(new Color(255, 255, 255));
            JScrollPane scrollPane;

            listBox.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    if (!e.getValueIsAdjusting()) {
                        JList<DodatnaIspitivanjaEnum> list = (JList)e.getSource();

                        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().contains(list.getSelectedValue())){
                            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().add(list.getSelectedValue());
                            pan.add(new JLabel(String.valueOf(list.getSelectedValue())));
                            MainWindow.getInstance().getBoxRight().add(pan);
                        }
                        MainWindow.getInstance().getBoxRight().revalidate();
                        MainWindow.getInstance().getBoxRight().repaint();
                    }
                }
            });

            panel.add(listBox);
            scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(50, 120));
            MainWindow.getInstance().getBoxCentar().add(scrollPane);


//            MainWindow.getInstance().getBoxCentar().revalidate();
//            MainWindow.getInstance().getBoxCentar().repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        JButton dalje = new JButton("DALJE");
        dalje.setPreferredSize(new Dimension(200,30));
        dalje.addActionListener(new UnesiRezDIListener());

        JPanel panE = new JPanel();
        panE.setPreferredSize(new Dimension(220,30));
        MainWindow.getInstance().getBoxCentar().add(panE);
        MainWindow.getInstance().getBoxCentar().add(dalje);


        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }


}
