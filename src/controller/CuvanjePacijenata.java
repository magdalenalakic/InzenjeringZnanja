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
            Integer fl = 0;
            for(Pacijent pacijent : list){
                fl++;
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
                if(fl != list.size()){
                    line += "\n";
                }
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
            Integer fl = 0;
            for(Pacijent pacijent : list){
                fl++;
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
                if(fl != list.size()){
                    line += "\n";
                }
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
            Integer fl = 0;
            for(Pacijent pacijent : list){
                fl++;
                String line = pacijent.getId() + ";";
                line += pacijent.getIme() + ";";
                line += pacijent.getPol() + ";";
                line += pacijent.getGodine() + ";";
                line += pacijent.getTezina() + ";";
                if(pacijent.getPusac()){
                    line +=  "da;";
                }else if(!pacijent.getPusac()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                if(pacijent.getDijabeticar()){
                    line +=  "da;";
                }else if(!pacijent.getDijabeticar()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                if(pacijent.getAsmaticar()){
                    line +=  "da;";
                }else if(!pacijent.getAsmaticar()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                if(pacijent.getFizickaAktivnost()){
                    line +=  "da;";
                }else if(!pacijent.getFizickaAktivnost()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                if(pacijent.getTrudnoca()){
                    line +=  "da;";
                }else if(!pacijent.getTrudnoca()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                if(pacijent.getAlergican()){
                    line +=  "da;";
                }else if(!pacijent.getAlergican()){
                    line +=  "ne;";
                }else{
                    line += ";";
                }

                line += pacijent.getAuskultacija() + ";";
                line += pacijent.getGornjiPritisak() + ";";
                line += pacijent.getDonjiPritisak() + ";";
                Integer flag = 0;
                if(pacijent.getListaSimptoma().isEmpty()){
                    line += ";";
                }else{
                    for(Simptomi simptom : pacijent.getListaSimptoma()){
                        flag++;
                        if(flag == pacijent.getListaSimptoma().size()){
                            line += simptom + ";";
                        }else{
                            line += simptom + ",";
                        }
                    }
                }

                flag = 0;
                if(pacijent.getPorodicneBolesti().isEmpty()){
                    line += ";";
                }else{
                    for(PorodicneBolesti bolest : pacijent.getPorodicneBolesti()){
                        flag++;
                        if(flag == pacijent.getPorodicneBolesti().size()){
                            line += bolest;
                        }else{
                            line += bolest + ",";
                        }
                    }
                }

                if(fl != list.size()){
                    line += "\n";
                }
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
            Integer fl = 0;
            for(Pacijent pacijent : list){
                fl++;
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

                if(fl != list.size()){
                    line += "\n";
                }
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
