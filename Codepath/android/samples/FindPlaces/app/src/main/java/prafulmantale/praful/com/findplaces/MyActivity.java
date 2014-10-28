package prafulmantale.praful.com.findplaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyActivity extends Activity {

    //https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Vict&types=geocode&language=fr&key=AIzaSyCrW18eNpNzaWeV1hARKaVQpwjLy5JU0_I

    ArrayList<String> placesList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, placesList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void search(View view){
        System.out.println("Search");
        EditText et = (EditText)findViewById(R.id.editText);
        String s1 = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=";
        String s2 = et.getText().toString();
        s2= s2.replace(" ", "%20");
        String s3 = "&types=establishment&language=en&key=AIzaSyCrW18eNpNzaWeV1hARKaVQpwjLy5JU0_I";

        RestClient client = new RestClient();
        System.out.println("Search:" + s1+s2+s3);
        adapter.clear();

        client.getMatches(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject response) {

                super.onSuccess(response);
                System.out.println("1:\r\n" + response);
            }

            @Override
            public void onSuccess(JSONArray response) {
                super.onSuccess(response);
                System.out.println("2:\r\n" + response);
            }

            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                super.onSuccess(statusCode, response);
                System.out.println("3:\r\n" + response);
                try {
                    JSONArray array = response.getJSONArray("predictions");

                    for(int i = 0; i < array.length(); i ++){
                        JSONObject obj = array.getJSONObject(i);
                        if(obj == null){
                            continue;
                        }

                        String str = obj.getString("description");
                        adapter.add(str);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, JSONArray response) {
                super.onSuccess(statusCode, response);
                System.out.println("4:\r\n" + response);
            }

            @Override
            public void onFailure(Throwable e, JSONArray errorResponse) {
                super.onFailure(e, errorResponse);
            }

            @Override
            protected void handleFailureMessage(Throwable e, String responseBody) {
                super.handleFailureMessage(e, responseBody);
            }
        }, s1+s2+s3);


    }
}
