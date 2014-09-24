package prafulmantale.praful.com.imagefinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.enums.ImageColor;
import prafulmantale.praful.com.imagefinder.enums.ImageSize;
import prafulmantale.praful.com.imagefinder.enums.ImageType;
import prafulmantale.praful.com.imagefinder.query.QueryFilters;
import prafulmantale.praful.com.imagefinder.query.QueryParameters;

public class OptionsActivity extends Activity {

    private Spinner spImageSizes;
    private Spinner spImageTypes;
    private Spinner spColorFilters;
    private EditText etSiteFilter;
    private Button btnSave;
    private Button btnCancel;
    private QueryParameters queryParameters;
    private QueryFilters queryFilters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        initializeViews();

        queryParameters = QueryParameters.getInstance();
        queryFilters = queryParameters.getQueryFilters();

        populateData();
    }

    private void populateData() {

        spImageSizes.setSelection(queryFilters.getSize().getValue());
        spColorFilters.setSelection(queryFilters.getColor().getValue());
        spImageTypes.setSelection(queryFilters.getType().getValue());

        etSiteFilter.setText(queryFilters.getDomain());
    }

    private void initializeViews(){
        spImageSizes = (Spinner)findViewById(R.id.etImageSize);
        spImageTypes = (Spinner)findViewById(R.id.etImageType);
        spColorFilters = (Spinner)findViewById(R.id.etColorFilter);
        etSiteFilter = (EditText)findViewById(R.id.etSiteFilter);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
    }

    public void onButtonClick(View view){

        if(R.id.btnCancel == view.getId()){
            //No op
            finish();
        }
        else{
            //save items
            queryFilters.setSize(ImageSize.values()[spImageSizes.getSelectedItemPosition()]);
            queryFilters.setType(ImageType.values()[spImageTypes.getSelectedItemPosition()]);
            queryFilters.setColor(ImageColor.values()[spColorFilters.getSelectedItemPosition()]);
            queryFilters.setDomain(etSiteFilter.getText().toString());

           finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
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
}
