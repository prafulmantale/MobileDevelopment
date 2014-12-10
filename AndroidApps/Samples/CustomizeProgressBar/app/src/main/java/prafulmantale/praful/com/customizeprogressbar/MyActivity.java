package prafulmantale.praful.com.customizeprogressbar;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class MyActivity extends Activity {

    private HorizontalSlider slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_my);
        setProgressBarVisibility(true);

        slider = (HorizontalSlider) this.findViewById(R.id.slider);
        slider.setOnProgressChangeListener(changeListener);

        ImageView img = (ImageView) findViewById(R.id.imageView1);
        ClipDrawable mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(10000);

        ImageView img1 = (ImageView) findViewById(R.id.imageView4);
        ClipDrawable mImageDrawable1 = (ClipDrawable) img1.getDrawable();
        mImageDrawable1.setLevel(10000);

    }

    private HorizontalSlider.OnProgressChangeListener changeListener = new HorizontalSlider.OnProgressChangeListener() {

        public void onProgressChanged(View v, int progress) {
            setProgress(progress);
        }

    };


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
}
