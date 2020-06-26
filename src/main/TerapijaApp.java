package main;

import connector.*;
import model.Dijagnoze;
import model.DodatnaIspitivanjaEnum;
import model.Lekovi;
import model.Pacijent;
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
import java.util.Collection;
import java.util.List;

public class TerapijaApp implements StandardCBRApplication {
    Connector _connector;
    CBRCaseBase _caseBase;
    NNConfig simConfig;

    public void setSimilarityConfigration1() {
//        System.out.println("POZIVA SE KLIKOM NA DUGME");
        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
        simConfig.addMapping(new Attribute("pol", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("godine", Pacijent.class), new Interval(10));
        simConfig.addMapping(new Attribute("rezPritiska", Pacijent.class), new Equal());
        simConfig.addMapping(new Attribute("porodicneBolesti", Pacijent.class), new ProveraPBUListi());
        simConfig.addMapping(new Attribute("listaSimptoma", Pacijent.class), new ProveraSimpUListi());
        simConfig.addMapping(new Attribute("listaRezultataDodatnihIspitivanja", Pacijent.class), new ProveraRezDodIsUListi());
//        simConfig.addMapping(new Attribute("listaDijagnoza", Pacijent.class), new ProveraTerapija());

    }

    @Override
    public void configure() throws ExecutionException {
        _connector =  new CsvConnector();
        _caseBase = new LinealCaseBase();
        setSimilarityConfigration1();
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
        ArrayList<Lekovi> predlozeniLekovi = new ArrayList<>();

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
        MainWindow.getInstance().setTerapija(predlozeniLekovi);
        System.out.println();


    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
