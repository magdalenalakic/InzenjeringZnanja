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
        if(lista2.size()==0){
            return 1.0;
        }
        for(Simptomi s : lista2){
           if(lista1.contains(s)){
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
