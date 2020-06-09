package controller;

import model.Pacijent;
import model.PolEnum;
import model.TezinaEnum;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajZdravstveniKartonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255,0,0));

        if(MainWindow.getInstance().getImePacijenta() == null || MainWindow.getInstance().getImePacijenta().getText().equals("") ||
           MainWindow.getInstance().getGodinePacijenta().getText().equals("") || MainWindow.getInstance().getGodinePacijenta() == null){
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        Integer godine = null;
        try{
            godine = Integer.parseInt(MainWindow.getInstance().getGodinePacijenta().getText());
        }catch (NumberFormatException nfe){
            MainWindow.getInstance().getStatusLinija().setText("Popunite polje za godine iskljucivo brojevima!");
            return;
        }
        String ime = MainWindow.getInstance().getImePacijenta().getText();


        PolEnum pol = null;
        Boolean trudnoca = null;
        TezinaEnum tezina = null;
        Boolean pusac = null;
        Boolean dijabeticar = null;
        Boolean asmaticar = null;
        Boolean fizickaAktivnost = null;
        Boolean alergican = null;

        if(MainWindow.getInstance().getPolZ().isSelected()){

            pol = PolEnum.Z;
        }else if(MainWindow.getInstance().getPolM().isSelected()){
            trudnoca = new Boolean(false);
            pol = PolEnum.M;
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }

        if(MainWindow.getInstance().getTezinaNormalna().isSelected()){

            tezina = TezinaEnum.normalnaTezina;
        }else if(MainWindow.getInstance().getTezinaSmanjena().isSelected()){

            tezina = TezinaEnum.smanjenaTezina;
        }else if(MainWindow.getInstance().getTezinaPovecana().isSelected()){

            tezina = TezinaEnum.povecanaTezina;
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }

        if(MainWindow.getInstance().getPusacDa().isSelected()){

            pusac = new Boolean(true);
        }else if(MainWindow.getInstance().getPusacNe().isSelected()){

            pusac = new Boolean(false);
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }


        if(MainWindow.getInstance().getDijabeticarDa().isSelected()){

            dijabeticar = new Boolean(true);
        }else if(MainWindow.getInstance().getDijabeticarNe().isSelected()){

            dijabeticar = new Boolean(false);
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }


        if(MainWindow.getInstance().getAsmaticarDa().isSelected()){

            asmaticar = new Boolean(true);
        }else if(MainWindow.getInstance().getAsmaticarNe().isSelected()){

            asmaticar = new Boolean(false);
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }


        if(MainWindow.getInstance().getFizickaAktivnostDa().isSelected()){

            fizickaAktivnost = new Boolean(true);
        }else if(MainWindow.getInstance().getFizickaAktivnostNe().isSelected()){

            fizickaAktivnost = new Boolean(false);
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }


        if(MainWindow.getInstance().getTrudnocaDa().isSelected()){

            trudnoca = new Boolean(true);
        }else if(MainWindow.getInstance().getTrudnocaNe().isSelected()){

            trudnoca = new Boolean(false);
        }else if(MainWindow.getInstance().getTrudnocaDa().isEnabled() && MainWindow.getInstance().getTrudnocaNe().isEnabled()){
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }


        if(MainWindow.getInstance().getAlergijaDa().isSelected()){

            alergican = new Boolean(true);
        }else if(MainWindow.getInstance().getAlergijaNe().isSelected()){

            alergican = new Boolean(false);
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        Pacijent pacijent = new Pacijent(ime, pol, godine, tezina, pusac, dijabeticar, asmaticar, fizickaAktivnost, trudnoca, alergican);
        WelcomeWindow.getInstance().getListaPacijenata().add(pacijent);

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Pacijent uspesno dodat!");

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();
        MainWindow.getInstance().getBoxRight().revalidate();
        MainWindow.getInstance().getBoxRight().repaint();
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

        MainWindow.getInstance().dodajZdravstveniKartonView();
        MainWindow.getInstance().getBoxRight().revalidate();
        MainWindow.getInstance().getBoxRight().repaint();
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }
}
