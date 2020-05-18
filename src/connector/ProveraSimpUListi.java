package connector;

import model.Simptomi;
import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.List;

public class ProveraSimpUListi implements LocalSimilarityFunction {


    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        List<Simptomi> lista1 = (List<Simptomi>) o;
        List<Simptomi> lista2 = (List<Simptomi>) o1;
        int brojac = 0;
        for(Simptomi s : lista2){
           if(lista1.contains(s)){
               System.out.println("Poklapaju se!");
               brojac++;
           }
        }
        double ret = (double) brojac/lista1.size();
        System.out.println(ret);

        if(lista2.size()==0){
            System.out.println("NEMAA");
            ret = 1.0;
        }
//        System.out.println("-------------------------------------------------------");
//        System.out.println("Lista 1: " + lista1.size());
//        for(Simptomi s : lista1){
//            System.out.println(s.toString());
//        }
//        System.out.println("-------------------------------------------------------");
//        System.out.println("*******************************************************");
//        System.out.println("Lista 2: " + lista2.size());
//        for(Simptomi s : lista2){
//            System.out.println(s.toString());
//        }
//        System.out.println("*******************************************************");
        return ret;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }
}
