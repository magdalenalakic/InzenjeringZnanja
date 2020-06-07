package controller;

import model.Dijagnoze;
import model.DodatnaIspitivanjaEnum;
import model.Pacijent;
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
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer = new BufferedWriter(fileWriter);
            writer.write("#id;listaDijagnoza\n");
            for(Pacijent pacijent : list){
                String line = pacijent.getId() + ";";
                Integer flag = 0;
                for(Dijagnoze dijag : pacijent.getListaDijagnoza()){
                    flag++;
                    if(flag == pacijent.getListaDijagnoza().size()){
                        line += dijag +"\n";
                    }else{
                        line += dijag + ",\n";
                    }
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

    }
}
