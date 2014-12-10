package prafulmantale.praful.com.simplechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, "Xp7EHjjljeUja1j41nwoEtMvARVvEdERCgqjhAeF", "jTcuMnCDmY3pBxkXCs1oZODCWhFWrx5h1G0Ama7r");
    }
}
