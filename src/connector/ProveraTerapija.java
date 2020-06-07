package connector;

import model.Dijagnoze;
import model.Lekovi;
import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.ArrayList;
import java.util.List;

public class ProveraTerapija implements LocalSimilarityFunction {
    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        List<Dijagnoze> lista1 = (List<Dijagnoze>) o;
        List<Dijagnoze> lista2 = (List<Dijagnoze>) o1;
        int brojac = 0;
        if(lista2.size()==0){
            return 1.0;
        }
        for(Dijagnoze d : lista2){
            if(lista1.contains(d)){
                brojac++;
            }
        }
        double ret = (double) brojac/lista2.size();
        return ret;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }
}
