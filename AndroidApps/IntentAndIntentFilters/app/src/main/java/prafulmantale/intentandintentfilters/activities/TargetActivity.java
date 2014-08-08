package prafulmantale.intentandintentfilters.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import prafulmantale.intentandintentfilters.R;

public class TargetActivity extends Activity {

    private TextView textView;
    private EditText editText;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
        StrictMode.ThreadPolicy policy = builder.permitAll().build();
        StrictMode.setThreadPolicy(policy);

        textView = (TextView)findViewById(R.id.messagedisplay);
        editText = (EditText)findViewById(R.id.sendbackmessage);
        imageView = (ImageView)findViewById(R.id.imageholder);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if(extras != null) {
            String message = intent.getStringExtra(MainActivity.MESSAGE_HOLDER);

            if(message != null) {
                textView.setText(message);
            }
        }

       if(intent.getAction() == Intent.ACTION_VIEW){
           Uri uri = intent.getData();
           URL url;

           try{
               url = new URL(uri.getScheme(), uri.getHost(), uri.getPath());
               BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

               String line = "";
               while ((line = reader.readLine()) != null){
                   //textView.append(line);
               }
           }
           catch (MalformedURLException ex){

           }
           catch (IOException ioException){

           }
       }


    }

    public void pickImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream inputStream = null;

        if(requestCode == 1 && resultCode == RESULT_OK){
            try{

                if(bitmap != null){
                    bitmap.recycle();
                }

                inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException ex){

            }
            finally {

                if(inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {

                    }
                }
            }
        }

    }

    public void sendBackMessage(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(MainActivity.BACK_MSG_HOLDER, editText.getText().toString());
        setResult(RESULT_OK, data);

        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.target, menu);
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
