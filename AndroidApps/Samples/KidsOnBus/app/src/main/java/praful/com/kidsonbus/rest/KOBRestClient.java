package praful.com.kidsonbus.rest;


import com.google.android.gms.maps.GoogleMap;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import praful.com.kidsonbus.caches.ApplicationData;
import praful.com.kidsonbus.model.Address;
import praful.com.kidsonbus.model.DirectionsResult;
import praful.com.kidsonbus.model.LocationDetails;
import praful.com.kidsonbus.utils.JSONUtils;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class KOBRestClient {

    //Helper to populate seed data
    //Server should send Lat Lang info of the student address
    public void getLatLang(final Address address) {

        String query = address.getQueryAddress();
        query = query.replaceAll(" ", "%20");
        RestClient.get("/geocode/json?address=" + query, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject firstResult = (response.getJSONArray("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location");
                    LocationDetails details = new LocationDetails(firstResult.getDouble("lat"), firstResult.getDouble("lng"));
                    address.setLocationDetails(details);
                    System.out.println("Address: " + address);
                } catch (JSONException ex) {

                }
            }
        });
    }

    public void getDirections(final GoogleMap googleMap) {

        String params = "optimize:true|";
        try {
            params = URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        RestClient.get("/directions/json?origin=600 rainbow dr mountain view CA&destination=3400 hillsview ave palo alto CA&waypoints=" + params +"450 N Mathilda Ave Sunnyvale CA &key=AIzaSyBq7iqgireVUrb20YUCnefFhfJt1WLutrA", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ApplicationData.getInstance().mDirectionsResult = JSONUtils.parseJSON(response.toString(), DirectionsResult.class);
                System.out.println("ROUTES:" + ApplicationData.getInstance().mDirectionsResult );

                googleMap.addPolyline(ApplicationData.getInstance().mDirectionsResult.getRoutes().get(0).getPolylineOptions());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }
        });

    }
}
