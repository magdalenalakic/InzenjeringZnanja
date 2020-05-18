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
        for(PorodicneBolesti s : lista2){
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

        return ret;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }

}
