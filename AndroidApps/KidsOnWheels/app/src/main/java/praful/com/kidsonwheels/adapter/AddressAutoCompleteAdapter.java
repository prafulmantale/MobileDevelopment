package praful.com.kidsonwheels.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class AddressAutoCompleteAdapter extends ArrayAdapter<AddressAutoCompleteAdapter.AddressAutoComplete> implements Filterable {

    private static final String TAG = AddressAutoCompleteAdapter.class.getSimpleName();

    private ArrayList<AddressAutoComplete> mResultList;
    private GoogleApiClient mGoogleApiClient;
    private LatLngBounds mBounds;
    private AutocompleteFilter mPlaceFilter;

    public AddressAutoCompleteAdapter(Context context, int resource, GoogleApiClient googleApiClient,
                                      LatLngBounds bounds, AutocompleteFilter filter) {
        super(context, resource);
        mGoogleApiClient = googleApiClient;
        mBounds = bounds;
        mPlaceFilter = filter;
    }

    public void setBounds(LatLngBounds bounds) {
        mBounds = bounds;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public AddressAutoComplete getItem(int position) {
        return mResultList.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null) {
                    mResultList = getAutoComplete(constraint);
                    if (mResultList != null) {
                        results.values = mResultList;
                        results.count = mResultList.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private ArrayList<AddressAutoComplete> getAutoComplete(CharSequence constraint) {
        if (mGoogleApiClient.isConnected()) {
            PendingResult<AutocompletePredictionBuffer> results =
                    Places.GeoDataApi
                            .getAutocompletePredictions(mGoogleApiClient, constraint.toString(),
                                    mBounds, mPlaceFilter);

            //todo Make the call on backgroud thread not the main UI thread. Block and wait for at most 60s
            AutocompletePredictionBuffer autoCompletePredictions = results
                    .await(60, TimeUnit.SECONDS);
            final Status status = autoCompletePredictions.getStatus();
            if (!status.isSuccess()) {
                autoCompletePredictions.release();
                return null;
            }
            Iterator<AutocompletePrediction> iterator = autoCompletePredictions.iterator();
            ArrayList resultList = new ArrayList<>(autoCompletePredictions.getCount());
            while (iterator.hasNext()) {
                AutocompletePrediction prediction = iterator.next();
                resultList.add(new AddressAutoComplete(prediction.getPlaceId(),
                        prediction.getDescription()));
            }

            autoCompletePredictions.release();
            return resultList;
        }
        return null;
    }

    public class AddressAutoComplete {

        public CharSequence placeId;
        public CharSequence description;

        AddressAutoComplete(CharSequence placeId, CharSequence description) {
            this.placeId = placeId;
            this.description = description;
        }

        @Override
        public String toString() {
            return description.toString();
        }
    }
}
