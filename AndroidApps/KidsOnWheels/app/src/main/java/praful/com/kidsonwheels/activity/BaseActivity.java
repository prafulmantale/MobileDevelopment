package praful.com.kidsonwheels.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import praful.com.kidsonwheels.R;

public abstract class BaseActivity extends ActionBarActivity {

    @Optional @InjectView(R.id.toolbar) Toolbar mToolbar;

    ImageView mStartPickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ButterKnife.inject(this);
        initialize();
    }

    abstract int getLayoutId();

    protected void setContentView(){
        setContentView(getLayoutId());
    }

    protected void initialize(){
        initializeToolbar();
        initializeViews();
    }

    protected void initializeToolbar(){
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            mStartPickup = (ImageView)mToolbar.findViewById(R.id.start_pickup);

        }
    }

    abstract void initializeViews();
}
