package prafulmantale.praful.com.yaym.caches;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 11/3/14.
 */
public class SnapshotCache {
    private static final String TAG = SnapshotCache.class.getSimpleName();

    private static SnapshotCache INSTANCE = new SnapshotCache();

    private Map<String, RWPositionSnapshot> cache;


    private SnapshotCache(){
        cache = new LinkedHashMap<String, RWPositionSnapshot>();
    }

    public static SnapshotCache getInstance(){
        return INSTANCE;
    }

    public void update(List<RWPositionSnapshot> list){

        for(RWPositionSnapshot snapshot : list){
            cache.put(snapshot.getCurrencyPair(), snapshot);
        }
    }
}
