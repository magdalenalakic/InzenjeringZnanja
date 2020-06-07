package connector;

import model.PorodicneBolesti;
import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.List;

public class ProveraPBUListi  implements LocalSimilarityFunction {


    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        List<PorodicneBolesti> lista1 = (List<PorodicneBolesti>) o;
        List<PorodicneBolesti> lista2 = (List<PorodicneBolesti>) o1;
        int brojac = 0;

        if(lista2.size()==0){
            return 1.0;
        }
        for(PorodicneBolesti s : lista2){
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
