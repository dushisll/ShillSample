package sample.shillsample.bean;

import java.util.List;

/**
 * Created by we on 2016/12/9.
 */

public class Girl {
    private boolean isError;
    private List<GirlData> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<GirlData> getResults() {
        return results;
    }

    public void setResults(List<GirlData> results) {
        this.results = results;
    }
}
