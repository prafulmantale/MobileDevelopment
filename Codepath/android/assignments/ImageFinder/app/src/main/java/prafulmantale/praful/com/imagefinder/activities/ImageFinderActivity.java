package prafulmantale.praful.com.imagefinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import prafulmantale.praful.com.imagefinder.R;


public class ImageFinderActivity extends Activity {

    private EditText etSearchQuery;
    private GridView gvImageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_finder);

        initialize();
    }


    private void initialize(){
        etSearchQuery = (EditText)findViewById(R.id.etSearchQuery);
        gvImageResult = (GridView)findViewById(R.id.gvImageResult);
    }

    public void onSearchClick(View view){

        String queryString = etSearchQuery.getText().toString();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.image_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.miSearch:
                //Do something
                return true;

            case R.id.miOptions:
                //Do something
                return true;

            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
