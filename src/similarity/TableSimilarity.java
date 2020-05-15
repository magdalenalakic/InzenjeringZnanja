package similarity;

import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class TableSimilarity implements LocalSimilarityFunction {

    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        return 0;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }
}
