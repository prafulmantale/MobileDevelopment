package prafulmantale.praful.com.twitterapp.handlers;

import android.widget.ArrayAdapter;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 10/6/14.
 */
public class UsersListResponseHandler extends JsonHttpResponseHandler {

    private static final String TAG = UsersListResponseHandler.class.getName();

    private ArrayAdapter<UserProfile> adapter;

    public UsersListResponseHandler(ArrayAdapter<UserProfile> adapter){
        this.adapter = adapter;
    }

    @Override
    public void onSuccess(JSONObject response) {

        try {

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
