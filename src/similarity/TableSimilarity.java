package similarity;

import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.List;

public class TableSimilarity implements LocalSimilarityFunction {

    private double matrix[][];
    List<String> categories;

    public TableSimilarity(List<String> categories) {
        this.categories = categories;
        int n = categories.size();
        matrix = new double[n][n];
        for (int i=0; i<n; i++)
            matrix[i][i] = 1;
    }

    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        return 0;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }
}
