package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.activities.HomeActivity;
import prafulmantale.praful.com.twitterapp.models.User;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class LoggedInUserResponseHandler extends JsonHttpResponseHandler {

    @Override
    public void onSuccess(JSONObject response) {

        User user = User.fromJSON(response);
        HomeActivity.loggedInUser = user;//NOT a good practice
        Log.d("USER" , user.toString());
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d("DEBUG: Failure \r\n", e.getMessage());
    }

    @Override
    protected void handleFailureMessage(Throwable e, String responseBody) {
        super.handleFailureMessage(e, responseBody);
    }
}
