package prafulmantale.praful.com.positionscale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends Activity {

    PlasticLinearLayout pll;
    PositionScale ps;
    YieldPercentageView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        pll = (PlasticLinearLayout)findViewById(R.id.pll);
       // ps = (PositionScale)findViewById(R.id.ps);
        gv = (YieldPercentageView)findViewById(R.id.gv);
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

    int count = 210;
    public void onTVClick(View view){
//        ps.setBars(count);
//        count ++;

       // gv.setAngle(count);
        count+=-10;

    }
}
