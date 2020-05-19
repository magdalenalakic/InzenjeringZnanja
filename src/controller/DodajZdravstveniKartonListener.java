package controller;

import model.Pacijent;
import model.PolEnum;
import model.TezinaEnum;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajZdravstveniKartonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("***PODACI***");
        System.out.println(MainWindow.getInstance().getImePacijenta().getText());
        String ime = MainWindow.getInstance().getImePacijenta().getText();

        System.out.println(MainWindow.getInstance().getGodinePacijenta().getText());
        Integer godine = Integer.parseInt(MainWindow.getInstance().getGodinePacijenta().getText());

        PolEnum pol = null;
        if(MainWindow.getInstance().getPolZ().isSelected()){
            System.out.println("ZENSKO");
            pol = PolEnum.Z;
        }else{
            System.out.println("MUSKO");
            pol = PolEnum.M;
        }

        TezinaEnum tezina = null;
        if(MainWindow.getInstance().getTezinaNormalna().isSelected()){
            System.out.println("NORMALNE TEZINE");
            tezina = TezinaEnum.normalnaTezina;
        }else if(MainWindow.getInstance().getTezinaSmanjena().isSelected()){
            System.out.println("MRSAVA");
            tezina = TezinaEnum.smanjenaTezina;
        }else{
            System.out.println("DEBELA");
            tezina = TezinaEnum.povecanaTezina;
        }
        Boolean pusac = null;
        if(MainWindow.getInstance().getPusacDa().isSelected()){
            System.out.println("PUSI");
            pusac = new Boolean(true);
        }else{
            System.out.println("NE PUSI");
            pusac = new Boolean(false);
        }

        Boolean dijabeticar = null;
        if(MainWindow.getInstance().getDijabeticarDa().isSelected()){
            System.out.println("DIJABETICAR");
            dijabeticar = new Boolean(true);
        }else{
            System.out.println("NIJE DIJABETICAR");
            dijabeticar = new Boolean(false);
        }

        Boolean asmaticar = null;
        if(MainWindow.getInstance().getAsmaticarDa().isSelected()){
            System.out.println("ASMATICAR");
            asmaticar = new Boolean(true);
        }else{
            System.out.println("NIJE ASMATICAR");
            asmaticar = new Boolean(false);
        }

        Boolean fizickaAktivnost = null;
        if(MainWindow.getInstance().getFizickaAktivnostDa().isSelected()){
            System.out.println("FIZICKI AKTIVAN");
            fizickaAktivnost = new Boolean(true);
        }else{
            System.out.println("NIJE FIZICKI AKTIVAN");
            fizickaAktivnost = new Boolean(false);
        }

        Boolean trudnoca = null;
        if(MainWindow.getInstance().getTrudnocaDa().isSelected()){
            System.out.println("TRUDNA");
            trudnoca = new Boolean(true);
        }else{
            System.out.println("NIJE TRUDNA");
            trudnoca = new Boolean(false);
        }

        Boolean alergican = null;
        if(MainWindow.getInstance().getAlergijaDa().isSelected()){
            System.out.println("ALERGICAR");
            alergican = new Boolean(true);
        }else{
            System.out.println("NIJE ALERGICAR");
            alergican = new Boolean(false);
        }
        Pacijent pacijent = new Pacijent(ime, pol, godine, tezina, pusac, dijabeticar, asmaticar, fizickaAktivnost, trudnoca, alergican);
        WelcomeWindow.getInstance().getListaPacijenata().add(pacijent);
        System.out.println(WelcomeWindow.getInstance().getListaPacijenata().size());
        System.out.println(pacijent);
    }
}
