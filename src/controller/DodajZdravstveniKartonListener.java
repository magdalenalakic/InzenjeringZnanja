package controller;

import view.DodatnaIspitivanja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajZdravstveniKartonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("***PODACI***");
        System.out.println(DodatnaIspitivanja.getInstance().getImePacijenta().getText());
        System.out.println(DodatnaIspitivanja.getInstance().getGodinePacijenta().getText());
        if(DodatnaIspitivanja.getInstance().getPolZ().isSelected()){
            System.out.println("ZENSKO");
        }else{
            System.out.println("MUSKO");
        }

        if(DodatnaIspitivanja.getInstance().getTezinaNormalna().isSelected()){
            System.out.println("NORMALNE TEZINE");
        }else if(DodatnaIspitivanja.getInstance().getTezinaSmanjena().isSelected()){
            System.out.println("MRSAVA");
        }else{
            System.out.println("MRSAVA");
        }

        if(DodatnaIspitivanja.getInstance().getPusacDa().isSelected()){
            System.out.println("PUSI");
        }else{
            System.out.println("NE PUSI");
        }

        if(DodatnaIspitivanja.getInstance().getDijabeticarDa().isSelected()){
            System.out.println("DIJABETICAR");
        }else{
            System.out.println("NIJE DIJABETICAR");
        }

        if(DodatnaIspitivanja.getInstance().getAsmaticarDa().isSelected()){
            System.out.println("ASMATICAR");
        }else{
            System.out.println("NIJE ASMATICAR");
        }

        if(DodatnaIspitivanja.getInstance().getFizickaAktivnostDa().isSelected()){
            System.out.println("FIZICKI AKTIVAN");
        }else{
            System.out.println("NIJE FIZICKI AKTIVAN");
        }

        if(DodatnaIspitivanja.getInstance().getTrudnocaDa().isSelected()){
            System.out.println("TRUDNA");
        }else{
            System.out.println("NIJE TRUDNA");
        }

        if(DodatnaIspitivanja.getInstance().getAlergijaDa().isSelected()){
            System.out.println("ALERGICAR");
        }else{
            System.out.println("NIJE ALERGICAR");
        }

    }
}
