package main;

import connector.*;
import controller.PacijentController;
import model.*;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;
import view.WelcomeWindow;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class App implements StandardCBRApplication {


    Connector _connector;
    CBRCaseBase _caseBase;
    NNConfig simConfig;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        WelcomeWindow gp = WelcomeWindow.getInstance();
        gp.setVisible(true);
    }

    @Override
    public void configure() throws ExecutionException {
        _connector =  new CsvConnector();
        _caseBase = new LinealCaseBase();

        setSimilarityConfigration1();
    }

    public void setSimilarityConfigration1() {
        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
        simConfig.addMapping(new Attribute("pol", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("godine", Pacijent.class), new Interval(10));
        simConfig.addMapping(new Attribute("rezPritiska", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("porodicneBolesti", Pacijent.class), new ProveraPBUListi());
        simConfig.addMapping(new Attribute("listaSimptoma", Pacijent.class), new ProveraSimpUListi());
        simConfig.addMapping(new Attribute("listaRezultataDodatnihIspitivanja", Pacijent.class), new ProveraRezDodIsUListi());
        simConfig.addMapping(new Attribute("listaDijagnoza", Pacijent.class), new ProveraTerapija());
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        return _caseBase;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), cbrQuery, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);
        System.out.println("Retrieved cases:");
        for (RetrievalResult nse : eval){
            System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());

        }
        System.out.println();
        System.out.println("Predlozena dodatna ispitivanja: ");
        List<DodatnaIspitivanjaEnum> dodatnaIspitivanja = new ArrayList<>();
        List<Dijagnoze> predlozeneDijagnoze = new ArrayList<>();
        List<Lekovi> predlozeniLekovi = new ArrayList<>();
        for(RetrievalResult nse : eval){
            Pacijent p = (Pacijent) nse.get_case().getDescription();
            for(DodatnaIspitivanjaEnum d : p.getListaDodatnihIspitivanja()){
                if(!dodatnaIspitivanja.contains(d)){
                    dodatnaIspitivanja.add(d);
                    System.out.println(d);
                }
            }
        }
        System.out.println();
        System.out.println("Predlozene dijagnoze: ");
        for(RetrievalResult nse : eval){
            Pacijent p = (Pacijent) nse.get_case().getDescription();

            for(Dijagnoze d : p.getListaDijagnoza()){
                if(!predlozeneDijagnoze.contains(d)){
                    predlozeneDijagnoze.add(d);
                    System.out.println(d);
                }
            }

        }
        System.out.println();
        System.out.println("Predlozena terapija: ");
        for(RetrievalResult nse : eval){
            Pacijent p = (Pacijent) nse.get_case().getDescription();

            for(Lekovi d : p.getListaLekova()){
                if(!predlozeniLekovi.contains(d)){
                    predlozeniLekovi.add(d);
                    System.out.println(d);
                }
            }

        }

    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
