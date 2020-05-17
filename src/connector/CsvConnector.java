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
import java.util.Collection;
import java.util.LinkedList;

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
        Pacijent pacijent = new Pacijent();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/zadatak1.csv")));
            if (br == null)
                throw new Exception("Error opening file");

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

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
                pacijent.setRezPritiska(RezPritiskaEnum.valueOf(values[14]));
                String[] porodicneBolesti = values[15].split(",");
                for(int i = 0; i < porodicneBolesti.length; i++){
                    pacijent.getPorodicneBolesti().add(PorodicneBolesti.valueOf(porodicneBolesti[i]));
                }
                String[] listaSimptoma = values[16].split(",");
                for(int i = 0; i < listaSimptoma.length; i++){
                    pacijent.getListaSimptoma().add(Simptomi.valueOf(listaSimptoma[i]));
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/zadatak1.csv")));
            if (br == null)
                throw new Exception("Error opening file");

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");
                String[] listaRez = values[1].split(",");

                for(int i = 0; i < listaRez.length; i++){
                    String[] rez = listaRez[i].split("=");
//                    if(rez[0].equals("analizaKrvi")){
//
//                    }else if(rez[0].equals("ekg")){
//
//                    }else if(rez[0].equals("ehokardiografija")){
//
//                    }else if(rez[0].equals("ergometrija")){
//
//                    }else if(rez[0].equals("koronarnaAngiografija")){
//
//                    }else if(rez[0].equals("rendgen")){
//
//                    }else if(rez[0].equals("holter24")){
//
//                    }else if(rez[0].equals("ct")){
//
//                    }
                }



            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


//                cbrCase.setDescription(movieDescription);
//                cases.add(cbrCase);
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
