package connector;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

import java.net.URL;
import java.util.Collection;

public class CsvConnector implements Connector {

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {

    }

    @Override
    public void close() {

    }

    @Override
    public void storeCases(Collection<CBRCase> collection) {

    }

    @Override
    public void deleteCases(Collection<CBRCase> collection) {

    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        return null;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
