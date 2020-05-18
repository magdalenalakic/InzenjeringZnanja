package main;

import connector.CsvConnector;
import controller.PacijentController;
import model.Pacijent;
import model.PolEnum;
import model.RezPritiskaEnum;
import model.Simptomi;
import similarity.TableSimilarity;
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
import view.MainWindow;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class App implements StandardCBRApplication {

    public static PacijentController pacijentController = new PacijentController();
    Connector _connector;
    CBRCaseBase _caseBase;
    NNConfig simConfig;


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MainWindow gp = MainWindow.getInstance();
        gp.setVisible(true);

//        RezPritiskaEnum rez = pacijentController.racunanjeRezultataPritiska(130,100);
//        if(rez.equals(null)){
//            System.out.println("null");
//        }
//        System.out.println("REZULTAT PRITISKA: "+ rez.toString());


        App recommender = new App();
        try {
            System.out.println("-----");
            recommender.configure();
            recommender.preCycle();
            CBRQuery query = new CBRQuery();
            Pacijent movieDescription = new Pacijent();
            movieDescription.setPol(PolEnum.M);
            movieDescription.setGodine(31);
//            movieDescription.getListaSimptoma().add(Simptomi.vrtoglavica);
//            movieDescription.setScore(5);
            query.setDescription( movieDescription );
            recommender.cycle(query);
            recommender.postCycle();

//            System.out.println("-----");
//            recommender.setSimilarityConfigration2();
//            recommender.preCycle();
//            movieDescription = new MovieDescription();
//            movieDescription.setGenre("Action");
//            movieDescription.setGender("F");
//            movieDescription.setScore(1);
//            query.setDescription( movieDescription );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration3();
//            recommender.preCycle();
//            movieDescription = new MovieDescription();
//            movieDescription.setGenre("Drama");
//            movieDescription.setGender("M");
//            movieDescription.setScore(1);
//            query.setDescription( movieDescription );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration4();
//            recommender.preCycle();
//            movieDescription = new MovieDescription();
//            movieDescription.setTitle("Gladiator");
//            movieDescription.setGender("M");
//            movieDescription.setScore(1);
//            query.setDescription( movieDescription );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration5();
//            recommender.preCycle();
//            movieDescription = new MovieDescription();
//            movieDescription.setTitle("Titanic");
//            movieDescription.setGender("F");
//            query.setDescription( movieDescription );
//            recommender.cycle(query);
//            recommender.postCycle();


        } catch (Exception e) {
            e.printStackTrace();
        }

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
        simConfig.addMapping(new Attribute("tezina", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("pusac", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("dijabeticar", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("asmaticar", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("fizickaAktivnost", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("trudnoca", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("alergican", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("auskultacija", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("rezPritiska", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("bolUGrudima", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("mucnina", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("hladnoZnojenje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("pritiskanje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("vrtoglavica", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("gubitakSvesti", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("otezanoDisanje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("malaksalost", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("zamucenjeVida", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("znojenje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("umor", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("gusenje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("povracanje", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("kratakDah", Simptomi.class), new Equal());
        simConfig.addMapping(new Attribute("povisenaTemperatura", Simptomi.class), new Equal());

//        TableSimilarity pBolestiSimilarity = new TableSimilarity((Arrays.asList(new String[] {""})));
//        simConfig.addMapping(new Attribute("age", MovieDescription.class), new Interval(5));
//        simConfig.addMapping(new Attribute("score", MovieDescription.class), new Interval(1));
//        simConfig.addMapping(new Attribute("year", MovieDescription.class), new Interval(10));
//        TableSimilarity genreSimilarity = new TableSimilarity((Arrays.asList(new String[] {"Crime","Action","Thriller"})));
//        genreSimilarity.setSimilarity("Crime", "Action", .7);
//        genreSimilarity.setSimilarity("Crime", "Thriller", .7);
//        genreSimilarity.setSimilarity("Action", "Thriller", .9);
//        simConfig.addMapping(new Attribute("genre", MovieDescription.class), genreSimilarity);
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
//		for (CBRCase c: cases)
//			System.out.println(c.getDescription());
        return _caseBase;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), cbrQuery, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);
        System.out.println("Retrieved cases:");
        for (RetrievalResult nse : eval)
            System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
