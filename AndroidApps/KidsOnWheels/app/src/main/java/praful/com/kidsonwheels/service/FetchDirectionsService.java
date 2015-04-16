package praful.com.kidsonwheels.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.manager.DirectionsManager;
import praful.com.kidsonwheels.model.DirectionsResult;
import praful.com.kidsonwheels.model.Student;
import praful.com.kidsonwheels.rest.KOWRestClient;
import praful.com.kidsonwheels.utils.Constants;
import praful.com.kidsonwheels.utils.JSONUtils;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class FetchDirectionsService extends IntentService{

    private static final String TAG = FetchDirectionsService.class.getSimpleName();


    public FetchDirectionsService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //For testing
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String errorMessage = "";

        List<Student> students = DataManager.getInstance().getStudents();
        if (students == null || students.size() == 0) {
            errorMessage = getString(R.string.no_student_addresses_specified);
            Log.wtf(TAG, errorMessage);
            return;
        }
        StringBuilder builder = new StringBuilder();

        //origin=600 rainbow dr mountain view CA&destination=600 rainbow dr mountain view CA&waypoints=
        builder.append("origin=")
                .append(DirectionsManager.getInstance().getLastLocation().latitude)
                .append(",")
                .append(DirectionsManager.getInstance().getLastLocation().longitude)
                .append("&destination=")
                .append(DataManager.getInstance().getSchoolDetails().getAddress().getQueryAddress());


        builder.append("&waypoints=optimize:true");
        StringBuilder builder1 = new StringBuilder();
        for(Student student : students){
            builder1.append("|").append(student.getAddress().getQueryAddress());
        }
        try {
            String str = URLEncoder.encode(builder1.toString(), "UTF-8");
            builder.append(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        builder.append("&key=" + Constants.GMAP_API_KEY);

        new KOWRestClient().getDirections(builder.toString(), jsonHandler);

    }



    private JsonHttpResponseHandler jsonHandler = new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            Log.i(TAG, "Directions results received ");
            String respString = response.toString()/*.replaceAll("<[^>]*>", "")*/;
            DirectionsManager.getInstance().setDirectionsResult(JSONUtils.parseJSON(respString, DirectionsResult.class));
            System.out.println("Directions: " + DirectionsManager.getInstance().getDirectionsResult());
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
        }
    };

}
