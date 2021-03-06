package controller;

import com.ugos.jiprolog.engine.*;
import main.DodatnaIspitivanjaApp;
import main.TerapijaApp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PredloziTerapijuListener implements ActionListener {

    private JIPEngine engine = new JIPEngine();

    public void predloziTerapijuCBR(){
        TerapijaApp dia = new TerapijaApp();

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
        MainWindow.getInstance().upisiDijagnozeCBR();

    }


    public void predloziTerapijuRB(){

        engine.consultFile("prolog/projekat.pl");
        JIPEngine engine = new JIPEngine();
        engine.consultFile("prolog/projekat.pl");
        String pacijent = MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme();
        String dijagnoza = PredloziDijagnozuWindow.getInstance().getDijagnoze().getSelectedItem().toString();
        //TERAPIJE

            String dijag = "terapija(" + pacijent +"," + dijagnoza + ",Y)";
            System.out.println(dijag);

            JIPQuery query24 = engine.openSynchronousQuery(dijag);
            JIPTerm solution24;

            while ( (solution24 = query24.nextSolution()) != null  ) {
                JIPTermParser termParser = engine.getTermParser();

                JIPList list = (JIPList)termParser.parseTerm(String.valueOf(solution24.getVariables()[0]));
                if(list.getHead() != null){
                    while(!list.getTail().toString().equals("[]")){
                        list = (JIPList)termParser.parseTerm(String.valueOf(list.getTail()));
                        if(!MainWindow.getInstance().getTerapija().contains(Lekovi.valueOf(String.valueOf(list.getHead())))){
                            MainWindow.getInstance().getTerapija().add(Lekovi.valueOf(String.valueOf(list.getHead())));
                        }

                    }
                }

            }


        System.out.println( MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaLekova());
        System.out.println("****************************************");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Dijagnoze selektovanaDijag = (Dijagnoze) PredloziDijagnozuWindow.getInstance().getDijagnoze().getSelectedItem();

        System.out.println("Selektovana dijagnoza : " + selektovanaDijag.toString());
        if(!MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza().contains(selektovanaDijag)){
            MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza().add(selektovanaDijag);
        }
        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            predloziTerapijuCBR();
        }else{
            predloziTerapijuRB();
        }



        PredloziTerapijuWindow wz1 = PredloziTerapijuWindow.getInstance();
        wz1.init();

    }
}
