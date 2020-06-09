package controller;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import main.DijagnozeApp;
import main.DodatnaIspitivanjaApp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.FizikalniPregledWindow;
import view.MainWindow;
import view.UnesiRezDIWindow;
import view.WelcomeWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UnesiRezDIListener implements ActionListener {

    public void dijagnozaCBR(){
        DijagnozeApp dia = new DijagnozeApp();

        try {
            System.out.println("DIJAGNOZE RBR");
            dia.configure();
            dia.preCycle();
            CBRQuery query = new CBRQuery();

            System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().toString());
            query.setDescription( MainWindow.getInstance().getTrenutnoAktivanPacijent() );
            dia.cycle(query);
            dia.postCycle();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void dijagnozaRB(){
        JIPEngine engine = new JIPEngine();
        engine.consultFile("prolog/projekat.pl");

        String pacijent = MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme();

        String temp = "dijagnoza(" + pacijent + ", Y)";
        JIPQuery query = engine.openSynchronousQuery(temp);
        JIPTerm solution;
        MainWindow.getInstance().setDijagnoze(new ArrayList<>());
        System.out.println("dijagnoze predlozeneeee");
        while ( (solution = query.nextSolution()) != null  ) {

            System.out.println(solution.getVariables()[0]);
            JIPVariable dijagnoza = solution.getVariables()[0];
            System.out.println(dijagnoza.getValue().toString());
            MainWindow.getInstance().getDijagnoze().add(Dijagnoze.valueOf(dijagnoza.getValue().toString()));
            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza().add(Dijagnoze.valueOf(dijagnoza.getValue().toString()));
        }
        System.out.println(temp);
        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            dijagnozaCBR();
        }else{
            dijagnozaRB();
        }

        UnesiRezDIWindow wz1 = UnesiRezDIWindow.getInstance();
        wz1.init();

    }
}
