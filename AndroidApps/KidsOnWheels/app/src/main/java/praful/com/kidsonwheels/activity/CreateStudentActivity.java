package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import praful.com.kidsonwheels.R;

public class CreateStudentActivity extends ActionBarActivity {

    @InjectView(R.id.avatar) ImageView mAvatar;
    @InjectView(R.id.save_button) Button mSaveButton;

    public static final int REQUEST_SELECT_PHOTO = 503;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.avatar)
    void onAvatarClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_SELECT_PHOTO);
    }

    @OnClick(R.id.save_button)
    void onSave(View view) {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_PHOTO) {
            mAvatar.setImageURI(data.getData());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_from_bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null) {
            return super.onTouchEvent(event);
        }
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
