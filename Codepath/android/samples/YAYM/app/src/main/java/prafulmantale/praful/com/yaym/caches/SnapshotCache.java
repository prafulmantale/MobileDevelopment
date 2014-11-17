package prafulmantale.praful.com.yaym.caches;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prafulmantale.praful.com.yaym.interfaces.SnapshotUpdateListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 11/3/14.
 */
public class SnapshotCache {
    private static final String TAG = SnapshotCache.class.getSimpleName();

    private static SnapshotCache INSTANCE = new SnapshotCache();

    private Map<String, RWPositionSnapshot> cache;
    private List<SnapshotUpdateListener> listeners;


    private SnapshotCache(){
        cache = new LinkedHashMap<String, RWPositionSnapshot>();
        listeners = new ArrayList<SnapshotUpdateListener>();
    }

    public static SnapshotCache getInstance(){
        return INSTANCE;
    }

    public void update(List<RWPositionSnapshot> list){

        cache.clear();
        for(RWPositionSnapshot snapshot : list){
            cache.put(snapshot.getCurrencyPair(), snapshot);
        }

        notifyUpdate();
    }

    public List<RWPositionSnapshot> getSnapshots(){
        return new ArrayList<RWPositionSnapshot>(cache.values());
    }


    public void addListener(SnapshotUpdateListener listener){
        this.listeners.add(listener);
    }

    private void notifyUpdate(){

        for (SnapshotUpdateListener listener : listeners){
            listener.onSnapshotUpdated();
        }
    }
}
