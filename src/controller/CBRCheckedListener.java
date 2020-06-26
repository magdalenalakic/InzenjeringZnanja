package controller;

import main.App;
import model.*;
import sun.applet.Main;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.util.FileIO;
import view.MainWindow;
import view.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CBRCheckedListener implements ActionListener {
    public static PacijentController pacijentController = new PacijentController();

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("-------------------------- CBR ------------------------------------------- ");
        ucitajPacijente();
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(new ImageIcon("Cardiology.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        MainWindow.getInstance().getBoxCentar().add(imgLabel);

        Label labelaTekst = new Label("Kardiologija          ");
        JTextArea tekst = new JTextArea("Kardiologija je grana medicine koja se bavi liječenjem bolesti srca i krvnih žila. Njih možemo podijeliti na: bolesti endokarda (najčešće endokarditis), bolesti miokarda (kardiomiopatije, angina pectoris, infarkt miokarda), bolesti perikarda (najčešće perikarditis), poremećaji rada srca (aritmije), greške srčanih zalistaka (urođene i stečene), srčana insuficijencija (dekompenzacija), bolesti krvnih žila (ateroskleroza, tromboza, tromboflebitis).");
        labelaTekst.setFont(new Font("Verdana", Font.BOLD, 14));
        tekst.setFont(new Font("Verdana", Font.ITALIC, 13));
        tekst.setLineWrap(true);
        tekst.setWrapStyleWord(true);
        tekst.setBackground(null);
        MainWindow.getInstance().getBoxRight().add(labelaTekst);
        MainWindow.getInstance().getBoxRight().add(tekst);

        MainWindow.getInstance().getStatusLinija().setText("");
        MainWindow.getInstance().getPanCenter().revalidate();
        MainWindow.getInstance().getPanCenter().repaint();


        MainWindow wz2 = MainWindow.getInstance();
        MainWindow.getInstance().setIzabranaOpcija(IzabranaOpcija.CBR);
        wz2.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);
    }


    public void ucitajPacijente(){
        List<Pacijent> listaPacijenata = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("csv-files/fizikalni-pregled.csv")));
            if (br == null)
                throw new Exception("Error opening file");

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

                Pacijent pacijent = new Pacijent();
                pacijent.setId(Long.parseLong(values[0]));
                pacijent.setIme(values[1]);
                pacijent.setPol(PolEnum.valueOf(values[2]));
                pacijent.setGodine(Integer.parseInt(values[3]));
                pacijent.setTezina(TezinaEnum.valueOf(values[4]));
                pacijent.setPusac(Boolean.parseBoolean(values[5]));
                pacijent.setDijabeticar(Boolean.parseBoolean(values[6]));
                pacijent.setAsmaticar(Boolean.parseBoolean(values[7]));
                pacijent.setFizickaAktivnost(Boolean.parseBoolean(values[8]));
                pacijent.setTrudnoca(Boolean.parseBoolean(values[9]));
                pacijent.setAlergican(Boolean.parseBoolean(values[10]));

                if(!values[11].equals(" ")){
                    pacijent.setAuskultacija(AuskultacijaEnum.valueOf(values[11]));
                }
                if(!values[12].equals(" ")){
                    pacijent.setGornjiPritisak(Integer.parseInt(values[12]));
                }
                if(!values[13].equals(" ")){
                    pacijent.setDonjiPritisak(Integer.parseInt(values[13]));
                }
                if(!values[12].equals(" ") && !values[13].equals(" ") ){
                    RezPritiskaEnum rez = pacijentController.racunanjeRezultataPritiska(pacijent.getGornjiPritisak(), pacijent.getDonjiPritisak());
                    if(!rez.equals(null)){
                        pacijent.setRezPritiska(rez);
                    }
                }
                if(!values[14].equals(" ")){
                    String[] listaSimptoma = values[14].split(",");
                    for(int i = 0; i < listaSimptoma.length; i++){
                        pacijent.getListaSimptoma().add(Simptomi.valueOf(listaSimptoma[i]));
                    }
                }

                if(!values[15].equals(" ")){
                    String[] porodicneBolesti   = values[15].split(",");
                    for(int i = 0; i < porodicneBolesti.length; i++){
                        pacijent.getPorodicneBolesti().add(PorodicneBolesti.valueOf(porodicneBolesti[i]));
                    }
                }

                listaPacijenata.add(pacijent);
                System.out.println(pacijent);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //"csv-files/dodatna-ispitivanja.csv"
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("csv-files/dodatna-ispitivanja.csv")));
            if (br == null)
                throw new Exception("Error opening file");
            List<String> linije = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                linije.add(line);
            }
//            System.out.println("Dodatna ispitivanja");
            for(int b = 0; b < linije.size(); b++){
                String[] values = linije.get(b).split(";");
                for(int k = 0; k < listaPacijenata.size(); k++){
                    if(listaPacijenata.get(k).getId().equals(Long.parseLong(values[0])) ){
                        String[] listaRez = values[1].split(",");
                        for(int i = 0; i < listaRez.length; i++){
                            String[] rez = listaRez[i].split("=");
                            String[] rezultati = rez[1].split("&");
                            List<String> listaRezultata = new ArrayList<>();
                            for(int j = 0; j < rezultati.length; j++){
                                listaRezultata.add(rezultati[j]);
                            }

                            if(rez[0].equals("analizaKrvi")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.analizaKrvi);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, listaRezultata);
                            }else if(rez[0].equals("ekg")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.ekg);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, listaRezultata);
                            }else if(rez[0].equals("ehokardiografija")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.ehokardiografija);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, listaRezultata);
                            }else if(rez[0].equals("ergometrija")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.ergometrija);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, listaRezultata);
                            }else if(rez[0].equals("koronarnaAngiografija")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.koronarnaAngiografija);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, listaRezultata);
                            }else if(rez[0].equals("rendgen")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.rendgen);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, listaRezultata);
                            }else if(rez[0].equals("holter24")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.holter24);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, listaRezultata);
                            }else if(rez[0].equals("ct")){
                                listaPacijenata.get(k).getListaDodatnihIspitivanja().add(DodatnaIspitivanjaEnum.ct);
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, listaRezultata);
                            }
                        }
//                        System.out.println(listaPacijenata.get(k));
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //"csv-files/dijagnoza.csv"
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("csv-files/dijagnoza.csv")));
            if (br == null)
                throw new Exception("Error opening file");
            List<String> linije = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                linije.add(line);
            }
//            System.out.println("Dijagnoze");
            for(int b = 0; b <  linije.size() ; b++){
                String[] values = linije.get(b).split(";");
                for(int k = 0; k < listaPacijenata.size(); k++){
                    if(listaPacijenata.get(k).getId() == Long.parseLong(values[0])){
                        String[] listaDijagnoza = values[1].split(",");
                        for(int i = 0; i < listaDijagnoza.length; i++){
                            listaPacijenata.get(k).getListaDijagnoza().add(Dijagnoze.valueOf(listaDijagnoza[i]));
                        }
//                        System.out.println(listaPacijenata.get(k));
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //"csv-files/lekovi.csv"
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("csv-files/lekovi.csv")));
            if (br == null)
                throw new Exception("Error opening file");
            List<String> linije = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                linije.add(line);
            }
//            System.out.println("Lekovi");
            for(int b = 0; b < linije.size() ; b++){
                String[] values = linije.get(b).split(";");
                for(int k = 0; k < listaPacijenata.size(); k++){
                    if(listaPacijenata.get(k).getId() == Long.parseLong(values[0])){
                        String[] listaLekova = values[1].split(",");
                        for(int i = 0; i < listaLekova.length; i++){
                            listaPacijenata.get(k).getListaLekova().add(Lekovi.valueOf(listaLekova[i]));
                        }
//                        System.out.println(listaPacijenata.get(k));
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WelcomeWindow.getInstance().setListaPacijenata(listaPacijenata);
    }
}
