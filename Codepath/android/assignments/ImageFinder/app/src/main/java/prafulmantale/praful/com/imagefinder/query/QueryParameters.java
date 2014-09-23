package prafulmantale.praful.com.imagefinder.query;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Container class for query parameters and filters
 */
public class QueryParameters {

    private String version;
    private String queryText;

    private int resultsPerPage;
    private int startIndex;

    private QueryFilters queryFilters;

    public QueryParameters() {
        version = "1.0";
        queryText = "";
        resultsPerPage = 8;

        queryFilters = new QueryFilters();
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
