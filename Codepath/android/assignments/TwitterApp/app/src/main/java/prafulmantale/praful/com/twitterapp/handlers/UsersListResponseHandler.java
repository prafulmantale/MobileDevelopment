package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.fragments.UsersListFragment;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 10/6/14.
 */
public class UsersListResponseHandler extends JsonHttpResponseHandler {

    private static final String TAG = UsersListResponseHandler.class.getName();

    private ArrayAdapter<UserProfile> adapter;
    private UsersListFragment fragment;

    public UsersListResponseHandler(ArrayAdapter<UserProfile> adapter, UsersListFragment fragment){
        this.adapter = adapter;
        this.fragment = fragment;
    }

    @Override
    public void onSuccess(JSONObject response) {

        try {
            Log.d("UsersListResponseHandler", response.toString());

            String nextCur = response.optString("next_cursor", null);
            this.fragment.nextCursor = nextCur;
            JSONArray jsonArray = response.getJSONArray("users");
            adapter.addAll(UserProfile.fromJSON(jsonArray));

        } catch (JSONException ex) {

        }
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        super.onFailure(e, errorResponse);
    }

    @Override
    public void onFailure(Throwable e, JSONArray errorResponse) {
        super.onFailure(e, errorResponse);
    }
}
