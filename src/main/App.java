package main;

import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import view.MainWindow;


public class App implements StandardCBRApplication {

    Connector _connector;
    CBRCaseBase _caseBase;
    NNConfig simConfig;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MainWindow gp = MainWindow.getInstance();
        gp.setVisible(true);

    }


    @Override
    public void configure() throws ExecutionException {

    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        return null;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {

    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
