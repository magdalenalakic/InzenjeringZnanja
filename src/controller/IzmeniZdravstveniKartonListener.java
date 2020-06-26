package controller;

import model.*;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IzmeniZdravstveniKartonListener implements ActionListener {
    private Integer godine;
    private String ime;
    private PolEnum pol;
    private Boolean trudnoca;
    private TezinaEnum tezina;
    private Boolean pusac ;
    private Boolean dijabeticar;
    private Boolean asmaticar;
    private Boolean fizickaAktivnost;
    private Boolean alergican;

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255, 0,0));

        if(MainWindow.getInstance().getImePacijenta() == null || MainWindow.getInstance().getImePacijenta().getText().equals("") ||
                MainWindow.getInstance().getGodinePacijenta().getText().equals("") || MainWindow.getInstance().getGodinePacijenta() == null){
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        godine = null;
        try{
            godine = Integer.parseInt(MainWindow.getInstance().getGodinePacijenta().getText());
        }catch (NumberFormatException nfe){
            MainWindow.getInstance().getStatusLinija().setText("Popunite polje za godine iskljucivo brojevima!");
            return;
        }
        ime = MainWindow.getInstance().getImePacijenta().getText();
        pol = null;
        trudnoca = null;
        tezina = null;
        pusac = null;
        dijabeticar = null;
        asmaticar = null;
        fizickaAktivnost = null;
        alergican = null;

        if(MainWindow.getInstance().getPolZ().isSelected()){

            pol = PolEnum.Z;
        }else if(MainWindow.getInstance().getPolM().isSelected()){
            if(MainWindow.getInstance().getTrudnocaDa().isSelected()){
                MainWindow.getInstance().getStatusLinija().setText("Muska osoba ne moze da bude trudna!");
                return;
            }
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
        Pacijent noviPacijent = new Pacijent(ime, pol, godine, tezina, pusac, dijabeticar,
                asmaticar, fizickaAktivnost, trudnoca, alergican);

        for(Pacijent pacijent:WelcomeWindow.getInstance().getListaPacijenata()){
            if(pacijent.getIme().equals(String.valueOf(ime))){
                pacijent.setGodine(godine);
                pacijent.setPol(pol);
                pacijent.setTezina(tezina);
                pacijent.setPusac(pusac);
                pacijent.setDijabeticar(dijabeticar);
                pacijent.setAsmaticar(asmaticar);
                pacijent.setFizickaAktivnost(fizickaAktivnost);
                pacijent.setTrudnoca(trudnoca);
                pacijent.setAlergican(alergican);
                break;
            }
        }
        //izmena pacijenta
        if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.RB)){
            izmeniRB();
        }
        else if(MainWindow.getInstance().getIzabranaOpcija().equals(IzabranaOpcija.CBR)){
            MainWindow.getInstance().upisiFizikalniPregledCBR();
        }

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Pacijent uspesno izmenjen!");

        MainWindow.getInstance().getBoxCentar().removeAll();
        MainWindow.getInstance().getBoxRight().removeAll();
        MainWindow.getInstance().getBoxRight().revalidate();
        MainWindow.getInstance().getBoxRight().repaint();
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

        MainWindow.getInstance().OdabirPacijentaZaIzmenuZK();
        MainWindow.getInstance().getBoxRight().revalidate();
        MainWindow.getInstance().getBoxRight().repaint();
        MainWindow.getInstance().getBoxCentar().revalidate();
        MainWindow.getInstance().getBoxCentar().repaint();

    }


    public void izmeniRB(){
        try {
            MainWindow.getInstance().upisiUPrologFile("pacijent("+ ime, "pacijent(" + ime + ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(pol == PolEnum.Z){
                MainWindow.getInstance().upisiUPrologFile("pol("+ ime, "pol(" + ime +",z"+  ").");
            }else if(pol == PolEnum.M){
                MainWindow.getInstance().upisiUPrologFile("pol("+ ime, "pol(" + ime +",m"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            MainWindow.getInstance().upisiUPrologFile("godine("+ ime, "godine(" + ime + ","+godine.toString()+ ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            MainWindow.getInstance().upisiUPrologFile("tezina("+ ime, "tezina(" + ime + ","+tezina.toString()+ ").");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(pusac == true){
                MainWindow.getInstance().upisiUPrologFile("pusac("+ ime, "pusac(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("pusac("+ ime, "pusac(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(dijabeticar == true){
                MainWindow.getInstance().upisiUPrologFile("dijabeticar("+ ime, "dijabeticar(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("dijabeticar("+ ime, "dijabeticar(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(asmaticar == true){
                MainWindow.getInstance().upisiUPrologFile("asmaticar("+ ime, "asmaticar(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("asmaticar("+ ime, "asmaticar(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(fizickaAktivnost == true){
                MainWindow.getInstance().upisiUPrologFile("fizickaAktivnost("+ ime, "fizickaAktivnost(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("fizickaAktivnost("+ ime, "fizickaAktivnost(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(trudnoca == true){
                MainWindow.getInstance().upisiUPrologFile("trudnoca("+ ime, "trudnoca(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("trudnoca("+ ime, "trudnoca(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            if(alergican == true){
                MainWindow.getInstance().upisiUPrologFile("alergican("+ ime, "alergican(" + ime +",da"+  ").");
            }else{
                MainWindow.getInstance().upisiUPrologFile("alergican("+ ime, "alergican(" + ime +",ne"+ ").");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
