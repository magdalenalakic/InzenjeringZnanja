package connector;

import controller.PacijentController;
import jdk.nashorn.internal.objects.NativeRegExp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;
import view.WelcomeWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CsvConnector implements Connector {

    public static PacijentController pacijentController = new PacijentController();

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


        for(int k = 0; k < WelcomeWindow.getInstance().getListaPacijenata().size(); k++){
            CBRCase cbrCase = new CBRCase();
            cbrCase.setDescription(WelcomeWindow.getInstance().getListaPacijenata().get(k));
//            WelcomeWindow.getInstance().getListaPacijenata().add(WelcomeWindow.getInstance().getListaPacijenata().get(k));
//            System.out.println("CSV connector");
//            System.out.println(listaPacijenata.get(k));
            cases.add(cbrCase);
        }
//        System.out.println("**************");
//        for(int k = 0; k < cases.size(); k++){
//
//            System.out.println(cases.get(k));
//
//        }
//        System.out.println("**************");

//        System.out.println("Cases size: " + cases.size());
//        System.out.println();
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
