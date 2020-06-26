package controller;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import main.DijagnozeApp;
import model.Dijagnoze;
import model.DodatnaIspitivanjaEnum;
import model.IzabranaOpcija;
import model.Pacijent;
import sun.applet.Main;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PredloziDijagnozuListener implements ActionListener {

    private JIPEngine engine = new JIPEngine();

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
        engine.consultFile("prolog/projekat.pl");
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

            engine.consultFile("prolog/projekat.pl");

            JIPVariable dijagnoza = solution.getVariables()[0];
            System.out.println(dijagnoza.getValue().toString());
            if(!MainWindow.getInstance().getDijagnoze().contains(Dijagnoze.valueOf(dijagnoza.getValue().toString()))){
                MainWindow.getInstance().getDijagnoze().add(Dijagnoze.valueOf(dijagnoza.getValue().toString()));
            }

        }
        System.out.println(temp);
        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDijagnoza());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("+++++++++++++++");
        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaDodatnihIspitivanja());


        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255,0,0));

        UnesiRezDIWindow udi = UnesiRezDIWindow.getInstance();
        System.out.println("+++++++++++++++unetirez");
        System.out.println(udi.getUnetiRezDI());


        // -------------------------------------------

        //VALIDACIJA
        for(DodatnaIspitivanjaEnum di: udi.getUnetiRezDI()){
            if(di.equals(DodatnaIspitivanjaEnum.analizaKrvi)){
                if(udi.getRezNivoSecera().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo secera iskljucivo brojevima!");
                    return;
                }else if(udi.getRezNivoHol().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo holesterola iskljucivo brojevima!");
                    return;
                }else if(udi.getRezNivoTrig().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo triglicerida iskljucivo brojevima!");
                    return;
                }
                Double rns = null;
                Double rnh = null;
                Double rnt = null;
                try{
                    rns = Double.valueOf(udi.getRezNivoHol().getText());
                    rnh = Double.valueOf(udi.getRezNivoHol().getText());
                    rnt = Double.valueOf(udi.getRezNivoTrig().getText());

                }catch (NumberFormatException nfe){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polja za analizu krvi iskljucivo brojevima!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ekg)){
                if((!udi.getUredan().isSelected() && !udi.getNijeUredan().isSelected() && !udi.getNeodredjen().isSelected()) ||
                        (!udi.getUbrzan().isSelected() && !udi.getUsporen().isSelected() && !udi.getNormalan().isSelected())){
                    System.out.println("ekg");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }

            }else if(di.equals(DodatnaIspitivanjaEnum.ehokardiografija)){
                if(!udi.getUredanEh().isSelected() && !udi.getNijeUredanEh().isSelected()){
                    System.out.println("ehok");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ergometrija)){
                if(!udi.getNiskaOpt().isSelected() && !udi.getVisokaOpt().isSelected()){
                    System.out.println("ergometrija");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.koronarnaAngiografija)){
                if(!udi.getPozitivno().isSelected() && !udi.getNegativno().isSelected()){
                    System.out.println("KA");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.rendgen)){
                if(!udi.getNijeUredanRend().isSelected() && !udi.getUredanRend().isSelected()){
                    System.out.println("rendgen");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ct)){
                if(!udi.getNijeUredanCT().isSelected() && !udi.getUredanCT().isSelected()){
                    System.out.println("ct");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.holter24)){
                if((!udi.getPovisen().isSelected() && !udi.getSnizen().isSelected()) ||
                        (!udi.getPrisutno().isSelected() && !udi.getNijePr().isSelected()) ||
                        (!udi.getNormalanST().isSelected() && !udi.getNijeNorST().isSelected())){
                    System.out.println("holter24");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }
        }

        System.out.println("USPESNO//");
        for(Pacijent p : WelcomeWindow.getInstance().getListaPacijenata()){
            if(p.getIme().equals(MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme())){
                for(DodatnaIspitivanjaEnum di: udi.getUnetiRezDI()){
                    if(di.equals(DodatnaIspitivanjaEnum.analizaKrvi)){
                        List<String> lista = new ArrayList<>();
                        lista.add(UnesiRezDIWindow.getInstance().getRezNivoSecera().getText());
                        lista.add(UnesiRezDIWindow.getInstance().getRezNivoHol().getText());
                        lista.add(UnesiRezDIWindow.getInstance().getRezNivoTrig().getText());
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.ekg)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getUredan().isSelected()){
                            lista.add("uredan");
                        }else if(udi.getNeodredjen().isSelected()){
                            lista.add("neodredjen");
                        }else{
                            lista.add("nijeUredan");
                        }

                        if(udi.getUbrzan().isSelected()){
                            lista.add("ubrzan");
                        }else if(udi.getUsporen().isSelected()){
                            lista.add("usporen");
                        }else{
                            lista.add("normalan");
                        }

                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.ehokardiografija)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getUredanEh().isSelected()){
                            lista.add("uredan");
                        }else {
                            lista.add("nijeUredan");
                        }
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.ergometrija)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getNiskaOpt().isSelected()){
                            lista.add("niskaOpterecenja");
                        }else {
                            lista.add("visokaOpterecenja");
                        }
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.koronarnaAngiografija)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getPozitivno().isSelected()){
                            lista.add("pozitivno");
                        }else {
                            lista.add("negativno");
                        }
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.rendgen)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getUredanRend().isSelected()){
                            lista.add("uredan");
                        }else {
                            lista.add("nijeUredan");
                        }
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.ct)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getUredanCT().isSelected()){
                            lista.add("uredan");
                        }else {
                            lista.add("nijeUredan");
                        }
                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, lista);

                    }else if(di.equals(DodatnaIspitivanjaEnum.holter24)){
                        List<String> lista = new ArrayList<>();
                        if(udi.getPovisen().isSelected()){
                            lista.add("povisen");
                        }else {
                            lista.add("snizen");
                        }

                        if(udi.getPrisutno().isSelected()){
                            lista.add("prisutno");
                        }else {
                            lista.add("nijePrisutno");
                        }
                        if(udi.getNormalanST().isSelected()){
                            lista.add("normalan");
                        }else {
                            lista.add("nijeNormalan");
                        }

                        MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, lista);
                        p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, lista);

                    }
                }
            }
        }

        String pacijent = MainWindow.getInstance().getTrenutnoAktivanPacijent().getIme();


        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.RB)){
            for(DodatnaIspitivanjaEnum di: udi.getUnetiRezDI()){
                if(di.equals(DodatnaIspitivanjaEnum.analizaKrvi)){

                    List<String> lista = new ArrayList<>();
                    lista.add(UnesiRezDIWindow.getInstance().getRezNivoSecera().getText());
                    lista.add(UnesiRezDIWindow.getInstance().getRezNivoHol().getText());
                    lista.add(UnesiRezDIWindow.getInstance().getRezNivoTrig().getText());
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, lista);
                    try {
                        MainWindow.getInstance().upisiUPrologFile("rezAnalizeKrvi("+ pacijent, "rezAnalizeKrvi(" + pacijent +","+UnesiRezDIWindow.getInstance().getRezNivoSecera().getText() +","+UnesiRezDIWindow.getInstance().getRezNivoHol().getText()+","+UnesiRezDIWindow.getInstance().getRezNivoTrig().getText() +  ").");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }else if(di.equals(DodatnaIspitivanjaEnum.ekg)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getUredan().isSelected()){
                        lista.add("uredan");
                    }else if(udi.getNeodredjen().isSelected()){
                        lista.add("neodredjen");
                    }else{
                        lista.add("nijeUredan");
                    }

                    if(udi.getUbrzan().isSelected()){
                        lista.add("ubrzan");
                    }else if(udi.getUsporen().isSelected()){
                        lista.add("usporen");
                    }else{
                        lista.add("normalan");
                    }


                    try {
                        String izmena = "rezEkg(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezEkg("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }else if(di.equals(DodatnaIspitivanjaEnum.ehokardiografija)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getUredanEh().isSelected()){
                        lista.add("uredan");
                    }else {
                        lista.add("nijeUredan");
                    }
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, lista);
                    try {
                        String izmena = "rezEhokardiografije(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezEhokardiografije("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else if(di.equals(DodatnaIspitivanjaEnum.ergometrija)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getNiskaOpt().isSelected()){
                        lista.add("niskaOpterecenja");
                    }else {
                        lista.add("visokaOpterecenja");
                    }
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, lista);
                    try {
                        String izmena = "rezErgometrija(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezErgometrija("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else if(di.equals(DodatnaIspitivanjaEnum.koronarnaAngiografija)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getPozitivno().isSelected()){
                        lista.add("pozitivno");
                    }else {
                        lista.add("negativno");
                    }
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, lista);
                    try {
                        String izmena = "rezKA(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezKA("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else if(di.equals(DodatnaIspitivanjaEnum.rendgen)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getUredanRend().isSelected()){
                        lista.add("uredan");
                    }else {
                        lista.add("nijeUredan");
                    }
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, lista);
                    try {
                        String izmena = "rezRendgen(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezRendgen("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }else if(di.equals(DodatnaIspitivanjaEnum.ct)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getUredanCT().isSelected()){
                        lista.add("uredan");
                    }else {
                        lista.add("nijeUredan");
                    }
//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, lista);
                    try {
                        String izmena = "rezCT(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezCT("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }else if(di.equals(DodatnaIspitivanjaEnum.holter24)){
                    List<String> lista = new ArrayList<>();
                    if(udi.getPovisen().isSelected()){
                        lista.add("povisen");
                    }else {
                        lista.add("snizen");
                    }

                    if(udi.getPrisutno().isSelected()){
                        lista.add("prisutno");
                    }else {
                        lista.add("nijePrisutno");
                    }
                    if(udi.getNormalanST().isSelected()){
                        lista.add("normalan");
                    }else {
                        lista.add("nijeNormalan");
                    }

//                    MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, lista);
                    try {
                        String izmena = "rezHolter24(" + pacijent +",";
                        for(int i =0; i < lista.size(); i++){
                            if(i == lista.size()-1){
                                izmena+= lista.get(i);
                            }else{
                                izmena+= lista.get(i) + ",";
                            }
                        }
                        izmena += ").";
                        MainWindow.getInstance().upisiUPrologFile("rezHolter24("+ pacijent, izmena);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
        else if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            MainWindow.getInstance().upisiDodatnaIspitivanjaCBR();
        }

        System.out.println("DODATNA ISPITIVANJAA");
        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaRezultataDodatnihIspitivanja());


        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Rezultati ispitivanja uspesno sacuvani!");

        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            dijagnozaCBR();
        }else{
            dijagnozaRB();
        }

        PredloziDijagnozuWindow wz1 = PredloziDijagnozuWindow.getInstance();

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();
        wz1.init();


    }
}
