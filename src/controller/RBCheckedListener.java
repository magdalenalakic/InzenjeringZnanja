package controller;

import com.ugos.jiprolog.engine.*;
import model.*;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RBCheckedListener implements ActionListener {

    private static PacijentController pacijentController = new PacijentController();

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("-------------------- RB --------------------------");
        ucitajPacijente();
        MainWindow wz2 = MainWindow.getInstance();
        MainWindow.getInstance().setIzabranaOpcija(IzabranaOpcija.RB);
        wz2.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);
        System.out.println("-------------------- RB --------------------------");
    }

    public void ucitajPacijente(){
        List<Pacijent> pacijenti = new ArrayList<>();
        JIPEngine engine = new JIPEngine();
        engine.consultFile("prolog/projekat.pl");

        //IMENA PACIJENATA I ID
        Integer i = 1;
        JIPQuery query = engine.openSynchronousQuery("pacijent(X)");
        JIPTerm solution;
        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                Pacijent pacijent = new Pacijent();
                pacijent.setId(i.longValue());
                pacijent.setIme(var.getValue().toString());
                i++;
                pacijenti.add(pacijent);
            }
        }

        //POL
        JIPQuery query2 = engine.openSynchronousQuery("pol(X, Y)");
        JIPTerm solution2;
        while ( (solution2 = query2.nextSolution()) != null  ) {
            JIPVariable ime = solution2.getVariables()[0];
            JIPVariable pol = solution2.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setPol(PolEnum.valueOf(pol.getValue().toString()));
                }
            }
        }

        //GODINE
        JIPQuery query3 = engine.openSynchronousQuery("godine(X, Y)");
        JIPTerm solution3;
        while ( (solution3 = query3.nextSolution()) != null  ) {

            JIPVariable ime = solution3.getVariables()[0];
            JIPVariable godine = solution3.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setGodine(Integer.parseInt(godine.getValue().toString()));
                }
            }
        }

        //PUSAC
        JIPQuery query4 = engine.openSynchronousQuery("pusac(X, Y)");
        JIPTerm solution4;
        while ( (solution4 = query4.nextSolution()) != null  ) {
            JIPVariable ime = solution4.getVariables()[0];
            JIPVariable pusac = solution4.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(pusac.getValue().toString().equals("da")){
                        p.setPusac(true);
                    }else{
                        p.setPusac(false);
                    }
                }
            }
        }

        //TEZINA
        JIPQuery query5 = engine.openSynchronousQuery("tezina(X, Y)");
        JIPTerm solution5;
        while ( (solution5 = query5.nextSolution()) != null  ) {
            JIPVariable ime = solution5.getVariables()[0];
            JIPVariable tezina = solution5.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setTezina(TezinaEnum.valueOf(tezina.getValue().toString()));
                }
            }
        }

        //DIJABETICAR
        JIPQuery query6 = engine.openSynchronousQuery("dijabeticar(X, Y)");
        JIPTerm solution6;
        while ( (solution6 = query6.nextSolution()) != null  ) {
            JIPVariable ime = solution6.getVariables()[0];
            JIPVariable dijabeticar = solution6.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(dijabeticar.getValue().toString().equals("da")){
                        p.setDijabeticar(true);
                    }else{
                        p.setDijabeticar(false);
                    }
                }
            }
        }

        //ASMATICAR
        JIPQuery query7 = engine.openSynchronousQuery("asmaticar(X, Y)");
        JIPTerm solution7;
        while ( (solution7 = query7.nextSolution()) != null  ) {

            JIPVariable ime = solution7.getVariables()[0];
            JIPVariable asmaticar = solution7.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(asmaticar.getValue().toString().equals("da")){
                        p.setAsmaticar(true);
                    }else{
                        p.setAsmaticar(false);
                    }
                }
            }
        }

        //FIZICKA AKTIVNOST
        JIPQuery query8 = engine.openSynchronousQuery("fizickaAktivnost(X, Y)");
        JIPTerm solution8;
        while ( (solution8 = query8.nextSolution()) != null  ) {
            JIPVariable ime = solution8.getVariables()[0];
            JIPVariable fizickaAktivnost = solution8.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(fizickaAktivnost.getValue().toString().equals("da")){
                        p.setFizickaAktivnost(true);
                    }else{
                        p.setFizickaAktivnost(false);
                    }
                }
            }
        }

        //TRUDNOCA
        JIPQuery query9 = engine.openSynchronousQuery("trudnoca(X, Y)");
        JIPTerm solution9;
        while ( (solution9 = query9.nextSolution()) != null  ) {
            JIPVariable ime = solution9.getVariables()[0];
            JIPVariable trudnoca = solution9.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(trudnoca.getValue().toString().equals("da")){
                        p.setTrudnoca(true);
                    }else{
                        p.setTrudnoca(false);
                    }
                }
            }
        }

        //ALERGICAN
        JIPQuery query10 = engine.openSynchronousQuery("alergican(X, Y)");
        JIPTerm solution10;
        while ( (solution10 = query10.nextSolution()) != null  ) {
            JIPVariable ime = solution10.getVariables()[0];
            JIPVariable alergican = solution10.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    if(alergican.getValue().toString().equals("da")){
                        p.setAlergican(true);
                    }else{
                        p.setAlergican(false);
                    }
                }
            }
        }

        //PORODICNE BOLESTI
        JIPQuery query11 = engine.openSynchronousQuery("porodicneBolesti(X, Y)");
        JIPTerm solution11;
        while ( (solution11 = query11.nextSolution()) != null  ) {

            JIPVariable ime = solution11.getVariables()[0];
            JIPEngine engine1 = new JIPEngine();
            JIPTermParser termParser = engine1.getTermParser();

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    JIPList list = (JIPList)termParser.parseTerm(String.valueOf(solution11.getVariables()[1]));
                    if(list.getHead() != null){
                        p.getPorodicneBolesti().add(PorodicneBolesti.valueOf(String.valueOf(list.getHead())));
                        while(!list.getTail().toString().equals("[]")){
                            list = (JIPList)termParser.parseTerm(String.valueOf(list.getTail()));
                            p.getPorodicneBolesti().add(PorodicneBolesti.valueOf(String.valueOf(list.getHead())));
                        }
                    }

                }
            }
        }

        //ASKULTACIJA
        JIPQuery query12 = engine.openSynchronousQuery("auskultacija(X, Y)");
        JIPTerm solution12;
        while ( (solution12 = query12.nextSolution()) != null  ) {
            JIPVariable ime = solution12.getVariables()[0];
            JIPVariable auskultacija = solution12.getVariables()[1];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setAuskultacija(AuskultacijaEnum.valueOf(auskultacija.getValue().toString()));
                }
            }
        }

        //PRITISAK
        JIPQuery query13 = engine.openSynchronousQuery("pritisak(X, Y, Z)");
        JIPTerm solution13;
        while ( (solution13 = query13.nextSolution()) != null  ) {
            JIPVariable ime = solution13.getVariables()[0];
            JIPVariable pritisakG = solution13.getVariables()[1];
            JIPVariable pritisakD = solution13.getVariables()[2];
            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    p.setGornjiPritisak(Integer.parseInt(pritisakG.getValue().toString()));
                    p.setDonjiPritisak(Integer.parseInt(pritisakD.getValue().toString()));
                    RezPritiskaEnum rez = pacijentController.racunanjeRezultataPritiska(p.getGornjiPritisak(), p.getDonjiPritisak());
                    if(!rez.equals(null)){
                        p.setRezPritiska(rez);
                    }

                }
            }
        }

        //REZ ANALIZE KRVI
        JIPQuery query14 = engine.openSynchronousQuery("rezAnalizeKrvi(X, Y, Z, F)");
        JIPTerm solution14;
        while ( (solution14 = query14.nextSolution()) != null  ) {
            JIPVariable ime = solution14.getVariables()[0];
            JIPVariable nivoSeceraUKrvi = solution14.getVariables()[1];
            JIPVariable nivoHolesterola = solution14.getVariables()[2];
            JIPVariable nivoTriglecirida = solution14.getVariables()[3];


            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(nivoSeceraUKrvi.getValue().toString());
                    rezultati.add(nivoHolesterola.getValue().toString());
                    rezultati.add(nivoTriglecirida.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, rezultati);
                }
            }
        }

        //REZ EKG
        JIPQuery query15 = engine.openSynchronousQuery("rezEkg(X, Y, Z)");
        JIPTerm solution15;
        while ( (solution15 = query15.nextSolution()) != null  ) {
            JIPVariable ime = solution15.getVariables()[0];
            JIPVariable nalaz = solution15.getVariables()[1];
            JIPVariable puls = solution15.getVariables()[2];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(nalaz.getValue().toString());
                    rezultati.add(puls.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, rezultati);
                }
            }
        }

        //REZ ERGOMETRIJA
        JIPQuery query16 = engine.openSynchronousQuery("rezErgometrija(X, Y)");
        JIPTerm solution16;
        while ( (solution16 = query16.nextSolution()) != null  ) {
            JIPVariable ime = solution16.getVariables()[0];
            JIPVariable ergometrija = solution16.getVariables()[1];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ergometrija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, rezultati);
                }
            }
        }

        //REZ EHOKARDIOGRAFIJA
        JIPQuery query17 = engine.openSynchronousQuery("rezEhokardiografije(X, Y)");
        JIPTerm solution17;
        while ( (solution17 = query17.nextSolution()) != null  ) {
            JIPVariable ime = solution17.getVariables()[0];
            JIPVariable ehokardiografija = solution17.getVariables()[1];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ehokardiografija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, rezultati);
                }
            }
        }

        //REZ KORONARNA ANGIOGRAFIJA
        JIPQuery query18 = engine.openSynchronousQuery("rezKA(X, Y)");
        JIPTerm solution18;
        while ( (solution18 = query18.nextSolution()) != null  ) {
            JIPVariable ime = solution18.getVariables()[0];
            JIPVariable koronarnaAngiografija = solution18.getVariables()[1];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(koronarnaAngiografija.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, rezultati);
                }
            }
        }

        //REZ RENDGENA
        JIPQuery query19 = engine.openSynchronousQuery("rezRendgen(X, Y)");
        JIPTerm solution19;
        while ( (solution19 = query19.nextSolution()) != null  ) {
            JIPVariable ime = solution19.getVariables()[0];
            JIPVariable rendgena = solution19.getVariables()[1];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(rendgena.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, rezultati);
                }
            }
        }

        //REZ HOLTER 24
        JIPQuery query20 = engine.openSynchronousQuery("rezHolter24(X, Y, Z, H)");
        JIPTerm solution20;
        while ( (solution20 = query20.nextSolution()) != null  ) {
            JIPVariable ime = solution20.getVariables()[0];
            JIPVariable srcanaFrekvencija = solution20.getVariables()[1];
            JIPVariable poremecajRitma = solution20.getVariables()[2];
            JIPVariable segment = solution20.getVariables()[3];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(srcanaFrekvencija.getValue().toString());
                    rezultati.add(poremecajRitma.getValue().toString());
                    rezultati.add(segment.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, rezultati);
                }
            }
        }

        //REZ CT
        JIPQuery query21 = engine.openSynchronousQuery("rezCT(X, Y)");
        JIPTerm solution21;
        while ( (solution21 = query21.nextSolution()) != null  ) {
            JIPVariable ime = solution21.getVariables()[0];
            JIPVariable ct = solution21.getVariables()[1];

            for(Pacijent p : pacijenti){
                if(p.getIme().equals(ime.getValue().toString())){
                    List<String> rezultati = new ArrayList<>();
                    rezultati.add(ct.getValue().toString());
                    p.getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, rezultati);
                }
            }
        }

        System.out.println("---------pacijentiii");
        WelcomeWindow.getInstance().setListaPacijenata(pacijenti);

        for(Pacijent p : WelcomeWindow.getInstance().getListaPacijenata()){
            System.out.println(p);
        }
        System.out.println("---------pacijentiii");

    }
}
