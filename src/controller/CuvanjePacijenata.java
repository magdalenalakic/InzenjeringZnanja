package controller;

import model.*;
import ucm.gaia.jcolibri.util.FileIO;
import view.WelcomeWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ucm.gaia.jcolibri.util.FileIO.*;

public class CuvanjePacijenata extends AbstractAction {

    private List<Pacijent> list = WelcomeWindow.getInstance().getListaPacijenata();

    //"csv-files/dijagnoza.csv"
    public void cuvanjeDijagnozaCSV(){
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/dijagnoza.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaDijagnoza\n");
            for(Pacijent pacijent : list){
                String line = pacijent.getId() + ";";
                Integer flag = 0;
                for(Dijagnoze dijag : pacijent.getListaDijagnoza()){
                    flag++;
                    if(flag == pacijent.getListaDijagnoza().size()){
                        line += dijag;
                    }else{
                        line += dijag + ",";
                    }
                }
                line += "\n";
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Dijagnoze sacuvane u csv fajl");
    }

    //"csv-files/dodatna-ispitivanja.csv"
    public void cuvanjeDodatnihIspitivanjaCSV(){
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/dodatna-ispitivanja.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaRezultataDodatnihIspitivanja\n");

            for(Pacijent pacijent : list){
                String line = pacijent.getId() + ";";
                Integer flag = 0;
                for(DodatnaIspitivanjaEnum die : pacijent.getListaRezultataDodatnihIspitivanja().keySet()){
                    flag++;
                    if(flag == pacijent.getListaRezultataDodatnihIspitivanja().size()){
                        line += die + "=";
                        Integer flag2 = 0;
                        for(String s : pacijent.getListaRezultataDodatnihIspitivanja().get(die)){
                            flag2++;
                            if(flag2 == pacijent.getListaRezultataDodatnihIspitivanja().get(die).size()){
                                line += s;
                            }else{
                                line += s +"&";
                            }
                        }

                    }else{
                        line += die + "=";
                        Integer flag2 = 0;
                        for(String s : pacijent.getListaRezultataDodatnihIspitivanja().get(die)){
                            flag2++;
                            if(flag2 == pacijent.getListaRezultataDodatnihIspitivanja().get(die).size()){
                                line += s;
                            }else{
                                line += s +"&";
                            }
                        }
                        line += ",";
                    }

                }
                line += "\n";
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Dodatna ispitivanja sacuvana u csv fajl");
    }

    //"csv-files/fizikalni-pregled.csv"
    public void cuvanjeFizikalnihPregledaCSV(){
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/fizikalni-pregled.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;ime;pol;godine;tezina;pusac;dijabeticar;asmaticar;fizickaAktivnost;trudnoca;alergican;auskultacija;gornjiPritisak;donjiPritisak;listaSimptoma;porodicneBolesti\n");
            for(Pacijent pacijent : list){
                String line = pacijent.getId() + ";";
                line += pacijent.getIme() + ";";
                line += pacijent.getPol() + ";";
                line += pacijent.getGodine() + ";";
                line += pacijent.getTezina() + ";";
                if(pacijent.getPusac()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                if(pacijent.getDijabeticar()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                if(pacijent.getAsmaticar()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                if(pacijent.getFizickaAktivnost()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                if(pacijent.getTrudnoca()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                if(pacijent.getAlergican()){
                    line +=  "da;";
                }else{
                    line +=  "ne;";
                }

                line += pacijent.getAuskultacija() + ";";
                line += pacijent.getGornjiPritisak() + ";";
                line += pacijent.getDonjiPritisak() + ";";
                Integer flag = 0;
                for(Simptomi simptom : pacijent.getListaSimptoma()){
                    flag++;
                    if(flag == pacijent.getListaSimptoma().size()){
                        line += simptom + ";";
                    }else{
                        line += simptom + ",";
                    }
                }
                flag = 0;
                for(PorodicneBolesti bolest : pacijent.getPorodicneBolesti()){
                    flag++;
                    if(flag == pacijent.getPorodicneBolesti().size()){
                        line += bolest;
                    }else{
                        line += bolest + ",";
                    }
                }
                line += "\n";
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Fizikalni pregledi sacuvani u csv fajl");
    }

    //"csv-files/lekovi.csv"
    public void cuvanjeLekovaCSV(){
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        try {
            File file = new File("csv-files/lekovi.csv");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaTerapija\n");
            for(Pacijent pacijent : list){
                String line = pacijent.getId() + ";";
                Integer flag = 0;
//                System.out.println(pacijent.getListaLekova());

                for(Lekovi lek : pacijent.getListaLekova()){
                    flag++;
                    if(flag == pacijent.getListaLekova().size()){
                        line += lek;
                    }else{
                        line += lek + ",";
                    }
                }

                line += "\n";
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close BufferedWriter
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // close FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Lekovi sacuvani u csv fajl");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = new File("database/pacijenti.txt");
//        System.out.println(f.getAbsolutePath());
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(WelcomeWindow.getInstance().getListaPacijenata());
            objectOutputStream.close();
            System.out.println("sacuvana lista pacijenata");

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.cuvanjeDijagnozaCSV();
        this.cuvanjeDodatnihIspitivanjaCSV();
        this.cuvanjeFizikalnihPregledaCSV();
        this.cuvanjeLekovaCSV();

    }
}
