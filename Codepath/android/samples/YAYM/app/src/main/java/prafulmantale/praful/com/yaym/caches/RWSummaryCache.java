package prafulmantale.praful.com.yaym.caches;

import prafulmantale.praful.com.yaym.models.RWSummary;

/**
 * Created by prafulmantale on 11/21/14.
 */
public class RWSummaryCache {

    private static final String TAG = RWSummaryCache.class.getSimpleName();

    private static RWSummaryCache INSTANCE = new RWSummaryCache();

    private RWSummary cache;


    private RWSummaryCache(){
        cache = new RWSummary();
    }

    public static RWSummaryCache getInstance(){
        return INSTANCE;
    }

    public RWSummary getRWSummary() {
        return cache;
    }

    public void setRWSummary(RWSummary rwSummary) {
        this.cache = rwSummary;
    }
}
