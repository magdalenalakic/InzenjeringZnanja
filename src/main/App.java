package main;

import connector.CsvConnector;
import connector.ProveraPBUListi;
import connector.ProveraSimpUListi;
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
import view.DodatnaIspitivanja;
import view.MainWindow;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
            pacijent.getListaSimptoma().add(Simptomi.otezanoDisanje);
            pacijent.getListaSimptoma().add(Simptomi.vrtoglavica);
            pacijent.getListaSimptoma().add(Simptomi.gubitakSvesti);
            pacijent.getListaSimptoma().add(Simptomi.umor);

            pacijent.getPorodicneBolesti().add(PorodicneBolesti.infarktMiokarda);

//            pacijent.getListaSimptoma().add(Simptomi.umor);

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
        simConfig.addMapping(new Attribute("porodicneBolesti", Pacijent.class), new ProveraPBUListi());
        simConfig.addMapping(new Attribute("listaSimptoma", Pacijent.class), new ProveraSimpUListi());

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
        for (RetrievalResult nse : eval){
            System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());

        }
        System.out.println();
        System.out.println("Predlozena dodatna ispitivanja: ");
        List<DodatnaIspitivanjaEnum> dodatnaIspitivanja = new ArrayList<>();
        for(RetrievalResult nse : eval){
            Pacijent p = (Pacijent) nse.get_case().getDescription();
            for(DodatnaIspitivanjaEnum d : p.getListaDodatnihIspitivanja()){
                if(!dodatnaIspitivanja.contains(d)){
                    dodatnaIspitivanja.add(d);
                    System.out.println(d);
                }
            }
        }

    }



    @Override
    public void postCycle() throws ExecutionException {

    }
}
