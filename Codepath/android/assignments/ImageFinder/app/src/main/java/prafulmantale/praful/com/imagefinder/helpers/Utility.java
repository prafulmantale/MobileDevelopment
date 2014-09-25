package prafulmantale.praful.com.imagefinder.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by prafulmantale on 9/24/14.
 */
public class Utility {

    private static final String TAG = "Utility";

    private Utility(){

    }


    /*
        Not working
     */
    public static Uri getLocalBitmapUri(ImageView imageView){

        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = null;

        if(drawable instanceof BitmapDrawable){
            bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        }
        else{
            return null;
        }


        Uri uri = null;
        FileOutputStream outputStream = null;

        try{

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "share_image_" + System.currentTimeMillis() + ".png");

            //Create dir named by the file and parent directories if missing
            file.getParentFile().mkdirs();

            outputStream = new FileOutputStream(file);
            //0-100 quality; png ingores it
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);

            outputStream.close();

            uri = Uri.fromFile(file);
        }
        catch (IOException ex){

            ex.printStackTrace();

            Log.d(TAG, ex.getMessage());

            try{

                if(outputStream != null){
                    outputStream.close();
                }
            }
            catch (IOException e){
                Log.d(TAG, " Exception in while closing output stream");
            }
        }

        return uri;
    }

    /*
        //Not working
     */
    public static Uri getBitmapURI(ImageView imageView, Context context){

        try {
            Drawable drawable = imageView.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Resolver title", "Image Description");

            Uri uri = Uri.parse(path);

            return uri;
        }
        catch (Exception ex){
            ex.printStackTrace();

            Log.d(TAG, ex.getMessage());
        }

        return null;
    }

}
