package prafulmantale.praful.com.themeswitcher;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by prafulmantale on 10/21/14.
 */
public class Utils {

    private static int sTheme;

    public final static int THEME_HOLO_LIGHT = 0;
    public final static int THEME_YOUR_CUSTOM_THEME = 1;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_HOLO_LIGHT:
                activity.setTheme(R.style.Theme_Holo_Light);
                break;
            case THEME_YOUR_CUSTOM_THEME:
                activity.setTheme(R.style.Theme_YOUR_CUSTOM_THEME);
                break;
        }
    }
}
