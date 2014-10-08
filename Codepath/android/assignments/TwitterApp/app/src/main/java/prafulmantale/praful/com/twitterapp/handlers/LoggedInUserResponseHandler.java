package prafulmantale.praful.com.twitterapp.handlers;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.activities.MainActivity;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class LoggedInUserResponseHandler extends JsonHttpResponseHandler {

    private Context context;

    public LoggedInUserResponseHandler(Context context){
        this.context = context;
    }
    @Override
    public void onSuccess(JSONObject response) {

        User user = User.fromJSON(response);
        UserProfile userProfile = UserProfile.fromJSON(response);
        MainActivity.loggedInUser = user;//NOT a good practice
        MainActivity.lp = userProfile;
        User.saveLoggedInUserDetails(context, user);
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
