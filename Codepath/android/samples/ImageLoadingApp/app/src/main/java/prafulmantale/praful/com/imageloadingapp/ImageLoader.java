package prafulmantale.praful.com.imageloadingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by prafulmantale on 9/25/14.
 */
public class ImageLoader {
    public static void load(String url, ImageView imageView) {

        //Load image asynchronously



        new MyAsyncTask(imageView).execute(url);

    }


    /*
    param  - type passed to execute
    progress - progress state
    type - Example bitmap
     */
    private static class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{

        ImageView imageView;
        protected MyAsyncTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //1. Connect to Socket
            //2. Send bytes for the request
            //3. Open up stream
            //4. Download bytes from the stream
            //5. Decode bytes as Bitmap
            URL url = null;

            try {
                url = new URL(params[0]);
            }
            catch (MalformedURLException ex){

            }

            if(url == null){
                return null;
            }

            InputStream in = null;
            URLConnection urlConnection = null;

            try {

                urlConnection = url.openConnection();
                urlConnection.connect();

                in = urlConnection.getInputStream();
            }
            catch (IOException ex){

            }

            if(in == null){
                return null;
            }

            Bitmap bitmap = null;

            try {
                bitmap = BitmapFactory.decodeStream(in);
            }
            finally {
                in = null;
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //6. Transfer bitmap to UIThread
            //7. Insert bitmap into image image view
            imageView.setImageBitmap(bitmap);
        }
    }
}
