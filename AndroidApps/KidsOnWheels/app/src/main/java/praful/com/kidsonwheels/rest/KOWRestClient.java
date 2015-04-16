package praful.com.kidsonwheels.rest;


import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import praful.com.kidsonwheels.model.Address;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class KOWRestClient {

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
                    LatLng location = new LatLng(firstResult.getDouble("lat"), firstResult.getDouble("lng"));
                    address.setLocation(location);
                    System.out.println("Address: " + address);
                } catch (JSONException ex) {

                }
            }
        });
    }

    public void getDirections(String params, JsonHttpResponseHandler handler) {

//        try {
//            params = URLEncoder.encode(params, "UTF-8");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }

        RestClient.get("/directions/json?" + params, null, handler);

    }
}
