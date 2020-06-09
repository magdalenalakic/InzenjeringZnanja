package controller;

import main.App;
import model.*;
import sun.applet.Main;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CBRCheckedListener implements ActionListener {
    public static PacijentController pacijentController = new PacijentController();

    @Override
    public void actionPerformed(ActionEvent e) {


        MainWindow wz2 = MainWindow.getInstance();
        MainWindow.getInstance().setIzabranaOpcija(IzabranaOpcija.CBR);
        wz2.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);


        App recommender = new App();
        try {
            System.out.println("-----");
            recommender.configure();
            recommender.preCycle();

//            CBRQuery query = new CBRQuery();
//            Pacijent pacijent = new Pacijent();
//            pacijent.setPol(PolEnum.M);
//            pacijent.setGodine(49);
//            pacijent.setRezPritiska(pacijentController.racunanjeRezultataPritiska(130, 95));
//            pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
//            pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
//            pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
//            pacijent.getListaSimptoma().add(Simptomi.umor);
//
//            pacijent.getPorodicneBolesti().add(PorodicneBolesti.infarktMiokarda);
//            List<String> l = new ArrayList<>();
//            l.add("10");
//            l.add("2.1");
//            l.add("3");
//            pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, l);
//            List<String> l2 = new ArrayList<>();
//            l2.add("nijeUredan");
//            l2.add("usporen");
//            pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, l2);
//            List<String> l3 = new ArrayList<>();
//            l3.add("nijeUredan");
//            pacijent.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, l3);
//
//            query.setDescription( pacijent );
//            recommender.cycle(query);
//            recommender.postCycle();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
