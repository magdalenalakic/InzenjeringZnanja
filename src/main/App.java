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
            Pacijent pacijent = new Pacijent();
            pacijent.setPol(PolEnum.M);
            pacijent.setGodine(49);
            pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
//            pacijent.getListaSimptoma().add(Simptomi.umor);
//            pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
//            pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
//            pacijent.setScore(5);
            query.setDescription( pacijent );
            recommender.cycle(query);
            recommender.postCycle();

//            System.out.println("-----");
//            recommender.setSimilarityConfigration2();
//            recommender.preCycle();
//            pacijent = new MovieDescription();
//            pacijent.setGenre("Action");
//            pacijent.setGender("F");
//            pacijent.setScore(1);
//            query.setDescription( pacijent );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration3();
//            recommender.preCycle();
//            pacijent = new MovieDescription();
//            pacijent.setGenre("Drama");
//            pacijent.setGender("M");
//            pacijent.setScore(1);
//            query.setDescription( pacijent );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration4();
//            recommender.preCycle();
//            pacijent = new MovieDescription();
//            pacijent.setTitle("Gladiator");
//            pacijent.setGender("M");
//            pacijent.setScore(1);
//            query.setDescription( pacijent );
//            recommender.cycle(query);
//            recommender.postCycle();
//
//            System.out.println("-----");
//            recommender.setSimilarityConfigration5();
//            recommender.preCycle();
//            pacijent = new MovieDescription();
//            pacijent.setTitle("Titanic");
//            pacijent.setGender("F");
//            query.setDescription( pacijent );
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
//        simConfig.addMapping(new Attribute("tezina", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("pusac", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("dijabeticar", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("asmaticar", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("fizickaAktivnost", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("trudnoca", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("alergican", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("auskultacija", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("rezPritiska", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("bolUGrudima", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("mucnina", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("hladnoZnojenje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("pritiskanje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("vrtoglavica", Pacijent.class), new Equal());
//        simConfig.addMapping(new Attribute("gubitakSvesti", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("otezanoDisanje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("malaksalost", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("zamucenjeVida", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("znojenje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("umor", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("gusenje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("povracanje", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("kratakDah", Simptomi.class), new Equal());
//        simConfig.addMapping(new Attribute("povisenaTemperatura", Simptomi.class), new Equal());

        TableSimilarity simptomiSimilarity = new TableSimilarity((Arrays.asList(new String[] {"bolUGrudima", "mucnina", "hladnoZnojenje", "pritiskanje", "vrtoglavica", "gubitakSvesti", "otezanoDisanje", "malaksalost", "zamucenjeVida", "znojenje", "umor", "gusenje", "povracanje", "kratakDah","povisenaTemperatura"})));

        simptomiSimilarity.setSimilarity("bolUGrudima", "mucnina", .5);
        simptomiSimilarity.setSimilarity("bolUGrudima", "hladnoZnojenje", .5);
        simptomiSimilarity.setSimilarity("bolUGrudima", "pritiskanje", .9);
        simptomiSimilarity.setSimilarity("bolUGrudima", "vrtoglavica", .7);
        simptomiSimilarity.setSimilarity("bolUGrudima", "gubitakSvesti", .6);
        simptomiSimilarity.setSimilarity("bolUGrudima", "otezanoDisanje", .9);
        simptomiSimilarity.setSimilarity("bolUGrudima", "malaksalost", .6);
        simptomiSimilarity.setSimilarity("bolUGrudima", "zamucenjeVida", .7);
        simptomiSimilarity.setSimilarity("bolUGrudima", "znojenje", .7);
        simptomiSimilarity.setSimilarity("bolUGrudima", "umor", .7);
        simptomiSimilarity.setSimilarity("bolUGrudima", "gusenje", .9);
        simptomiSimilarity.setSimilarity("bolUGrudima", "povracanje", .6);
        simptomiSimilarity.setSimilarity("bolUGrudima", "kratakDah", .9);
        simptomiSimilarity.setSimilarity("bolUGrudima", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("mucnina", "hladnoZnojenje", .9);
        simptomiSimilarity.setSimilarity("mucnina", "pritiskanje", .8);
        simptomiSimilarity.setSimilarity("mucnina", "vrtoglavica", .7);
        simptomiSimilarity.setSimilarity("mucnina", "gubitakSvesti", .8);
        simptomiSimilarity.setSimilarity("mucnina", "otezanoDisanje", .7);
        simptomiSimilarity.setSimilarity("mucnina", "malaksalost", .9);
        simptomiSimilarity.setSimilarity("mucnina", "zamucenjeVida", .7);
        simptomiSimilarity.setSimilarity("mucnina", "znojenje", .9);
        simptomiSimilarity.setSimilarity("mucnina", "umor", .8);
        simptomiSimilarity.setSimilarity("mucnina", "gusenje", .6);
        simptomiSimilarity.setSimilarity("mucnina", "povracanje", .9);
        simptomiSimilarity.setSimilarity("mucnina", "kratakDah", .6);
        simptomiSimilarity.setSimilarity("mucnina", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("hladnoZnojenje", "pritiskanje", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "vrtoglavica", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "gubitakSvesti", .8);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "otezanoDisanje", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "malaksalost", .8);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "zamucenjeVida", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "znojenje", .9);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "umor", .8);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "gusenje", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "povracanje", .6);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "kratakDah", .7);
        simptomiSimilarity.setSimilarity("hladnoZnojenje", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("pritiskanje", "vrtoglavica", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "gubitakSvesti", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "otezanoDisanje", .9);
        simptomiSimilarity.setSimilarity("pritiskanje", "malaksalost", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "zamucenjeVida", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "znojenje", .8);
        simptomiSimilarity.setSimilarity("pritiskanje", "umor", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "gusenje", .9);
        simptomiSimilarity.setSimilarity("pritiskanje", "povracanje", .7);
        simptomiSimilarity.setSimilarity("pritiskanje", "kratakDah", .9);
        simptomiSimilarity.setSimilarity("pritiskanje", "povisenaTemperatura", .6);

        simptomiSimilarity.setSimilarity("vrtoglavica", "gubitakSvesti", .9);
        simptomiSimilarity.setSimilarity("vrtoglavica", "otezanoDisanje", .7);
        simptomiSimilarity.setSimilarity("vrtoglavica", "malaksalost", .9);
        simptomiSimilarity.setSimilarity("vrtoglavica", "zamucenjeVida", .9);
        simptomiSimilarity.setSimilarity("vrtoglavica", "znojenje", .8);
        simptomiSimilarity.setSimilarity("vrtoglavica", "umor", .8);
        simptomiSimilarity.setSimilarity("vrtoglavica", "gusenje", .6);
        simptomiSimilarity.setSimilarity("vrtoglavica", "povracanje", .6);
        simptomiSimilarity.setSimilarity("vrtoglavica", "kratakDah", .5);
        simptomiSimilarity.setSimilarity("vrtoglavica", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("gubitakSvesti", "otezanoDisanje", .8);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "malaksalost", .9);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "zamucenjeVida", .9);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "znojenje", .8);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "umor", .8);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "gusenje", .7);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "povracanje", .6);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "kratakDah", .8);
        simptomiSimilarity.setSimilarity("gubitakSvesti", "povisenaTemperatura", .7);

        simptomiSimilarity.setSimilarity("otezanoDisanje", "malaksalost", .8);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "zamucenjeVida", .6);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "znojenje", .7);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "umor", .9);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "gusenje", .9);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "povracanje", .7);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "kratakDah", .9);
        simptomiSimilarity.setSimilarity("otezanoDisanje", "povisenaTemperatura", .7);

        simptomiSimilarity.setSimilarity("malaksalost", "zamucenjeVida", .8);
        simptomiSimilarity.setSimilarity("malaksalost", "znojenje", .8);
        simptomiSimilarity.setSimilarity("malaksalost", "umor", .8);
        simptomiSimilarity.setSimilarity("malaksalost", "gusenje", .6);
        simptomiSimilarity.setSimilarity("malaksalost", "povracanje", .9);
        simptomiSimilarity.setSimilarity("malaksalost", "kratakDah", .7);
        simptomiSimilarity.setSimilarity("malaksalost", "povisenaTemperatura", .7);

        simptomiSimilarity.setSimilarity("zamucenjeVida", "znojenje", .5);
        simptomiSimilarity.setSimilarity("zamucenjeVida", "umor", .8);
        simptomiSimilarity.setSimilarity("zamucenjeVida", "gusenje", .6);
        simptomiSimilarity.setSimilarity("zamucenjeVida", "povracanje", .6);
        simptomiSimilarity.setSimilarity("zamucenjeVida", "kratakDah", .5);
        simptomiSimilarity.setSimilarity("zamucenjeVida", "povisenaTemperatura", .7);

        simptomiSimilarity.setSimilarity("znojenje", "umor", .8);
        simptomiSimilarity.setSimilarity("znojenje", "gusenje", .7);
        simptomiSimilarity.setSimilarity("znojenje", "povracanje", .7);
        simptomiSimilarity.setSimilarity("znojenje", "kratakDah", .6);
        simptomiSimilarity.setSimilarity("znojenje", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("umor", "gusenje", .8);
        simptomiSimilarity.setSimilarity("umor", "povracanje", .6);
        simptomiSimilarity.setSimilarity("umor", "kratakDah", .9);
        simptomiSimilarity.setSimilarity("umor", "povisenaTemperatura", .6);

        simptomiSimilarity.setSimilarity("gusenje", "povracanje", .6);
        simptomiSimilarity.setSimilarity("gusenje", "kratakDah", .9);
        simptomiSimilarity.setSimilarity("gusenje", "povisenaTemperatura", .6);

        simptomiSimilarity.setSimilarity("povracanje", "kratakDah", .6);
        simptomiSimilarity.setSimilarity("povracanje", "povisenaTemperatura", .8);

        simptomiSimilarity.setSimilarity("kratakDah", "povisenaTemperatura", .7);

        simConfig.addMapping(new Attribute("listaSimptoma", Pacijent.class), simptomiSimilarity);

//        simConfig.addMapping(new Attribute("age", MovieDescription.class), new Interval(5));
//        simConfig.addMapping(new Attribute("score", MovieDescription.class), new Interval(1));
//        simConfig.addMapping(new Attribute("year", MovieDescription.class), new Interval(10));
//        TableSimilarity genreSimilarity = new TableSimilarity((Arrays.asList(new String[] {"Crime","Action","Thriller"})));

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
