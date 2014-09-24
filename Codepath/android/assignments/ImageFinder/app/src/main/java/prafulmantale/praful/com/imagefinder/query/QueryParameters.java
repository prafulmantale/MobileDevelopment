package prafulmantale.praful.com.imagefinder.query;

import com.loopj.android.http.RequestParams;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Container class for query parameters and filters
 */
public class QueryParameters {

    private static QueryParameters INSTANCE = new QueryParameters();

    private String version;
    private String queryText;

    private int resultsPerPage;
    private int startIndex;

    private QueryFilters queryFilters;

    private QueryParameters() {
        version = "1.0";
        queryText = "";
        resultsPerPage = 8;
        startIndex = 0;

        queryFilters = new QueryFilters();
    }

    public static QueryParameters getInstance(){

        return INSTANCE;
    }

    public void reset(){
        INSTANCE = new QueryParameters();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public String getPageIndex() {
        return String.valueOf(startIndex);
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public QueryFilters getQueryFilters() {
        return queryFilters;
    }

    public void setQueryFilters(QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
    }

    public String getRSZ(){
        return String.valueOf(resultsPerPage);
    }

    /*
        Validates query parameters
    */
    public boolean isValid(){

        boolean isValid = true;

        if(queryText == null || queryText.trim().isEmpty()){
            isValid = false;
        }


        return isValid;
    }

    public void populateRequestParameters(RequestParams requestParams) {
        getQueryFilters().populateRequestParameters(requestParams);
    }

    @Override
    public String toString() {
        return "QueryParameters{" +
                "version='" + version + '\'' +
                ", queryText='" + queryText + '\'' +
                '}';
    }


    //To do
    public static class QueryBuilder{
        private String queryText;

    }
}
