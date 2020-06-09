package controller;


import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import main.DodatnaIspitivanjaApp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.FizikalniPregledWindow;
import view.MainWindow;
import view.PredloziDodatnaIspitivanjaWindow;
import view.WelcomeWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredloziDodatnaIspitivanjaListener implements ActionListener {

    public static PacijentController pacijentController = new PacijentController();

    public void dodatnaIspitivanjaCBR(){
        DodatnaIspitivanjaApp dia = new DodatnaIspitivanjaApp();

        try {

            dia.configure();
            dia.preCycle();
            CBRQuery query = new CBRQuery();
            System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().toString());
            query.setDescription(  MainWindow.getInstance().getTrenutnoAktivanPacijent() );
            dia.cycle(query);
            dia.postCycle();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void dodatnaIspitivanjaRB(){


        String pacijent = MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme();
        List<Simptomi> simptomi = MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma();
        List<String> simp = new ArrayList<>();
        for(Simptomi p : simptomi){
            simp.add(p.toString());
        }
        String pritG = FizikalniPregledWindow.getInstance().getPritisakG().getText();
        String pritD = FizikalniPregledWindow.getInstance().getPritisakD().getText();
        String ask = "";
        if(FizikalniPregledWindow.getInstance().getUredna().isSelected()){
            ask = AuskultacijaEnum.uredna.toString();
        }else if(FizikalniPregledWindow.getInstance().getPostojiSum().isSelected()){
            ask = AuskultacijaEnum.postojiSum.toString();
        }else if(FizikalniPregledWindow.getInstance().getPoremecajRitma().isSelected()){
            ask = AuskultacijaEnum.poremecajRitma.toString();
        }

        List<PorodicneBolesti> pb = MainWindow.getInstance().getTrenutnoAktivanPacijent().getPorodicneBolesti();
        List<String> pbs = new ArrayList<>();
        for(PorodicneBolesti p : pb){
            pbs.add(p.toString());
        }
        try {
            MainWindow.getInstance().upisiUPrologFile("pritisak("+ pacijent, "pritisak(" + pacijent +","+pritG +","+pritD +  ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            MainWindow.getInstance().upisiUPrologFile("auskultacija("+ pacijent, "auskultacija(" + pacijent +"," + ask +  ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            System.out.println("por bol: "+pbs);
            MainWindow.getInstance().upisiUPrologFile("porodicneBolesti("+ pacijent, "porodicneBolesti(" + pacijent +"," + pbs +  ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        List<String> lista = new ArrayList<>();
        for(Simptomi s : simptomi){
            lista.add(s.toString());
        }

        System.out.println("****************************************");
        System.out.println(lista);
        String temp = "dodatnoIspitivanje(listaSimptoma(" + pacijent +"," + lista + "), Y)";

        System.out.println(temp);

        JIPEngine engine = new JIPEngine();
        engine.consultFile("prolog/projekat.pl");
        JIPQuery query = engine.openSynchronousQuery(temp);
        JIPTerm solution;
        MainWindow.getInstance().setDodatnaIspitivanja(new ArrayList<>());
        while ( (solution = query.nextSolution()) != null  ) {
            JIPVariable dodatnoIspitivanje = solution.getVariables()[0];
            System.out.println("dod ispit: "+ dodatnoIspitivanje.getValue().toString());
            MainWindow.getInstance().getDodatnaIspitivanja().add(DodatnaIspitivanjaEnum.valueOf(dodatnoIspitivanje.getValue().toString()));
//            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.valueOf(dodatnoIspitivanje.getValue().toString()));
//            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.valueOf(dodatnoIspitivanje.getValue().toString()));
        }

//        MainWindow.getInstance().setDodatnaIspitivanja(dodatnaIspitivanja);

        System.out.println(MainWindow.getInstance().getDodatnaIspitivanja());
//        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255,0,0));


        if(FizikalniPregledWindow.getInstance().getPritisakG() == null || FizikalniPregledWindow.getInstance().getPritisakG().getText().equals("") ||
                FizikalniPregledWindow.getInstance().getPritisakD() == null || FizikalniPregledWindow.getInstance().getPritisakD().getText().equals("")){
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        Integer pritisakGornji = null;
        Integer pritisakDonji = null;
        AuskultacijaEnum auskultacija = null;
        try{
            pritisakGornji = Integer.parseInt(FizikalniPregledWindow.getInstance().getPritisakG().getText());
            pritisakDonji = Integer.parseInt(FizikalniPregledWindow.getInstance().getPritisakD().getText());
        }catch (NumberFormatException nfe){
            MainWindow.getInstance().getStatusLinija().setText("Popunite polje za pritisak iskljucivo brojevima!");
            return;
        }

        pritisakGornji = Integer.valueOf(FizikalniPregledWindow.getInstance().getPritisakG().getText());
        pritisakDonji = Integer.valueOf(FizikalniPregledWindow.getInstance().getPritisakD().getText());
        if(FizikalniPregledWindow.getInstance().getUredna().isSelected()){
            auskultacija = AuskultacijaEnum.uredna;
        }else if(FizikalniPregledWindow.getInstance().getPostojiSum().isSelected()){
            auskultacija = AuskultacijaEnum.postojiSum;
        }else if(FizikalniPregledWindow.getInstance().getPoremecajRitma().isSelected()){
            auskultacija = AuskultacijaEnum.poremecajRitma;
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        MainWindow.getInstance().getTrenutnoAktivanPacijent().setRezPritiska(pacijentController.racunanjeRezultataPritiska(pritisakGornji,pritisakDonji));
        MainWindow.getInstance().getTrenutnoAktivanPacijent().setAuskultacija(auskultacija);

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Podaci su sacuvani!");

        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            dodatnaIspitivanjaCBR();
        }else{
            dodatnaIspitivanjaRB();
        }

        PredloziDodatnaIspitivanjaWindow wz1 = PredloziDodatnaIspitivanjaWindow.getInstance();


        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();

        wz1.init();
    }
}
