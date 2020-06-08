package controller;


import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import model.*;
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
import java.util.ArrayList;
import java.util.List;

public class PredloziDodatnaIspitivanjaListener implements ActionListener {

    public static PacijentController pacijentController = new PacijentController();

    public void dodatnaIspitivanjaCBR(){
        //pozvati funkciju
    }
    public void dodatnaIspitivanjaRB(){
        JIPEngine engine = new JIPEngine();

        String pacijent = MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme();
        List<Simptomi> simptomi = MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaSimptoma();
        List<String> lista = new ArrayList<>();
        for(Simptomi s : simptomi){
            lista.add(s.toString());
        }

        System.out.println("****************************************");
        System.out.println(lista);
        String temp = "dodatnoIspitivanje(listaSimptoma(" + pacijent +"," + lista + "), Y)";

        System.out.println(temp);

        JIPQuery query = engine.openSynchronousQuery(temp);
        JIPTerm solution;

        while ( (solution = query.nextSolution()) != null  ) {
            JIPVariable dodatnoIspitivanje = solution.getVariables()[0];
            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.valueOf(dodatnoIspitivanje.getValue().toString()));
        }
        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja());

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
