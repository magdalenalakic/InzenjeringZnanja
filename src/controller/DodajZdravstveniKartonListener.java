package controller;

import model.Pacijent;
import model.PolEnum;
import model.TezinaEnum;
import view.DodatnaIspitivanja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajZdravstveniKartonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("***PODACI***");
        System.out.println(DodatnaIspitivanja.getInstance().getImePacijenta().getText());
        String ime = DodatnaIspitivanja.getInstance().getImePacijenta().getText();

        System.out.println(DodatnaIspitivanja.getInstance().getGodinePacijenta().getText());
        Integer godine = Integer.parseInt(DodatnaIspitivanja.getInstance().getGodinePacijenta().getText());

        PolEnum pol = null;
        if(DodatnaIspitivanja.getInstance().getPolZ().isSelected()){
            System.out.println("ZENSKO");
            pol = PolEnum.Z;
        }else{
            System.out.println("MUSKO");
            pol = PolEnum.M;
        }

        TezinaEnum tezina = null;
        if(DodatnaIspitivanja.getInstance().getTezinaNormalna().isSelected()){
            System.out.println("NORMALNE TEZINE");
            tezina = TezinaEnum.normalnaTezina;
        }else if(DodatnaIspitivanja.getInstance().getTezinaSmanjena().isSelected()){
            System.out.println("MRSAVA");
            tezina = TezinaEnum.smanjenaTezina;
        }else{
            System.out.println("DEBELA");
            tezina = TezinaEnum.povecanaTezina;
        }
        Boolean pusac = null;
        if(DodatnaIspitivanja.getInstance().getPusacDa().isSelected()){
            System.out.println("PUSI");
            pusac = new Boolean(true);
        }else{
            System.out.println("NE PUSI");
            pusac = new Boolean(false);
        }

        Boolean dijabeticar = null;
        if(DodatnaIspitivanja.getInstance().getDijabeticarDa().isSelected()){
            System.out.println("DIJABETICAR");
            dijabeticar = new Boolean(true);
        }else{
            System.out.println("NIJE DIJABETICAR");
            dijabeticar = new Boolean(false);
        }

        Boolean asmaticar = null;
        if(DodatnaIspitivanja.getInstance().getAsmaticarDa().isSelected()){
            System.out.println("ASMATICAR");
            asmaticar = new Boolean(true);
        }else{
            System.out.println("NIJE ASMATICAR");
            asmaticar = new Boolean(false);
        }

        Boolean fizickaAktivnost = null;
        if(DodatnaIspitivanja.getInstance().getFizickaAktivnostDa().isSelected()){
            System.out.println("FIZICKI AKTIVAN");
            fizickaAktivnost = new Boolean(true);
        }else{
            System.out.println("NIJE FIZICKI AKTIVAN");
            fizickaAktivnost = new Boolean(false);
        }

        Boolean trudnoca = null;
        if(DodatnaIspitivanja.getInstance().getTrudnocaDa().isSelected()){
            System.out.println("TRUDNA");
            trudnoca = new Boolean(true);
        }else{
            System.out.println("NIJE TRUDNA");
            trudnoca = new Boolean(false);
        }

        Boolean alergican = null;
        if(DodatnaIspitivanja.getInstance().getAlergijaDa().isSelected()){
            System.out.println("ALERGICAR");
            alergican = new Boolean(true);
        }else{
            System.out.println("NIJE ALERGICAR");
            alergican = new Boolean(false);
        }
        Pacijent pacijent = new Pacijent(ime, pol, godine, tezina, pusac, dijabeticar, asmaticar, fizickaAktivnost, trudnoca, alergican);
        DodatnaIspitivanja.getInstance().getListaPacijenata().add(pacijent);
        System.out.println(DodatnaIspitivanja.getInstance().getListaPacijenata().size());
        System.out.println(pacijent);
    }
}
