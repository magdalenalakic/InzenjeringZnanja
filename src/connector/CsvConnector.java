package connector;

import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CsvConnector implements Connector {

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {

    }

    @Override
    public void close() {

    }

    @Override
    public void storeCases(Collection<CBRCase> collection) {

    }

    @Override
    public void deleteCases(Collection<CBRCase> collection) {

    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
        CBRCase cbrCase = new CBRCase();
        List<Pacijent> listaPacijenata = new ArrayList<>();
        //"csv-files/fizikalni-pregled.csv"
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("csv-files/fizikalni-pregled.csv")));
            if (br == null)
                throw new Exception("Error opening file");
            System.out.println("Fizikalni pregled.");
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
                pacijent.setAuskultacija(AuskultacijaEnum.valueOf(values[11]));
                pacijent.setGornjiPritisak(Integer.parseInt(values[12]));
                pacijent.setDonjiPritisak(Integer.parseInt(values[13]));
//                pacijent.setRezPritiska(RezPritiskaEnum.valueOf(values[14]));
                String[] listaSimptoma = values[14].split(",");
                for(int i = 0; i < listaSimptoma.length; i++){
                    pacijent.getListaSimptoma().add(Simptomi.valueOf(listaSimptoma[i]));
                }
                String[] porodicneBolesti = values[15].split(",");
                for(int i = 0; i < porodicneBolesti.length; i++){
                    pacijent.getPorodicneBolesti().add(PorodicneBolesti.valueOf(porodicneBolesti[i]));
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
                System.out.println(line);
            }
            System.out.println("Dodatna ispitivanja");
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
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.analizaKrvi, listaRezultata);
                            }else if(rez[0].equals("ekg")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ekg, listaRezultata);
                            }else if(rez[0].equals("ehokardiografija")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ehokardiografija, listaRezultata);
                            }else if(rez[0].equals("ergometrija")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ergometrija, listaRezultata);
                            }else if(rez[0].equals("koronarnaAngiografija")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.koronarnaAngiografija, listaRezultata);
                            }else if(rez[0].equals("rendgen")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.rendgen, listaRezultata);
                            }else if(rez[0].equals("holter24")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.holter24, listaRezultata);
                            }else if(rez[0].equals("ct")){
                                listaPacijenata.get(k).getListaRezultataDodatnihIspitivanja().put(DodatnaIspitivanjaEnum.ct, listaRezultata);
                            }
                        }
                        System.out.println(listaPacijenata.get(k));
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
            System.out.println("Dijagnoze");
            for(int b = 0; b <  linije.size() ; b++){
                String[] values = linije.get(b).split(";");
                for(int k = 0; k < listaPacijenata.size(); k++){
                    if(listaPacijenata.get(k).getId() == Long.parseLong(values[0])){
                        String[] listaDijagnoza = values[1].split(",");
                        for(int i = 0; i < listaDijagnoza.length; i++){
                            listaPacijenata.get(k).getListaDijagnoza().add(Dijagnoze.valueOf(listaDijagnoza[i]));
                        }
                        System.out.println(listaPacijenata.get(k));
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
            System.out.println("Lekovi");
            for(int b = 0; b < linije.size() ; b++){
                String[] values = linije.get(b).split(";");
                for(int k = 0; k < listaPacijenata.size(); k++){
                    if(listaPacijenata.get(k).getId() == Long.parseLong(values[0])){
                        String[] listaLekova = values[1].split(",");
                        for(int i = 0; i < listaLekova.length; i++){
                            listaPacijenata.get(k).getListaLekova().add(Lekovi.valueOf(listaLekova[i]));
                        }
                        System.out.println(listaPacijenata.get(k));
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int k = 0; k < listaPacijenata.size(); k++){
            cbrCase.setDescription(listaPacijenata.get(k));
        }
        cases.add(cbrCase);
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
