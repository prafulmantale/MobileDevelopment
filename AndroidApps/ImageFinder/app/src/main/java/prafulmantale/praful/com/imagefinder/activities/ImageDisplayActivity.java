package prafulmantale.praful.com.imagefinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.models.SearchResult;

public class ImageDisplayActivity extends Activity {

    private ImageView ivImageFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        getActionBar().hide();
        initializeViews();

        SearchResult searchResult = (SearchResult)getIntent().getSerializableExtra("SR");

        Picasso.with(this).load(searchResult.getUrl()).into(ivImageFull);
    }

    private void initializeViews(){

        ivImageFull = (ImageView)findViewById(R.id.ivImageFull);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_display, menu);
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
