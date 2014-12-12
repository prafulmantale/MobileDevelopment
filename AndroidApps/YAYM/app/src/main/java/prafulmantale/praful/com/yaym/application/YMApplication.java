package prafulmantale.praful.com.yaym.application;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.Utils;
import prafulmantale.praful.com.yaym.models.LoginRequest;
import prafulmantale.praful.com.yaym.networking.RestClient;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class YMApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener{

    //Add preference for poll frequency, if user wants to refresh only on demand etc

    private static final String TAG = YMApplication.class.getSimpleName();

    private static final String REFRESH_SERVICE = "com.yaym.RefreshService";
    private String ymServer;
    private static String appBaseUrl;

    private RestClient client;
    private PersistentCookieStore cookieStore;

    private static LoginRequest loginRequest;

    private static final String LOGIN_URL = "admin/auth/login";
    private static final String RULES_URL = "fxiapi/riskwarehouse/rule";
    private static final String SNAPSHOt_URL = "fxiapi/riskwarehouse/snapshot";

    //Historical data
    //fxiapi/riskwarehouse/historicalData?ccyPair=EUR/USD&org=YMSBADemo&_=1416895669041
    private static final String HISTORICAL_DATA_URL = "fxiapi/riskwarehouse/historicalData";

    //Rate data
    //fxiapi/historicaldata/quoteHistory?instrument=EUR/USD&period=HOUR&count=1440&_=1416895669040
    private static final String RATE_DATA_URL = "fxiapi/historicaldata/quoteHistory";

    //Reference data
    //fxiapi/refdata/supportedCcypairs?_=1416895669037
    private static final String REF_DATA_URL = "fxiapi/refdata/supportedCcypairs";

//    private static final String RULES_URL = "rw/riskwarehouse/rule";
//    private static final String SNAPSHOt_URL = "rw/riskwarehouse/snapshot";

    public static List<Cookie> appCookies;

    private Typeface typeface;

    @Override
    public void onCreate() {
        super.onCreate();

        cookieStore = new PersistentCookieStore(this);
        client = new RestClient(cookieStore);
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        appBaseUrl = "https://demo.ym.integral.net/fxi/";
    }

    public RestClient getClient(){
        return client;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        sendBroadcast(new Intent(REFRESH_SERVICE));
    }


    public void setYmServer(String ymServer) {
        this.ymServer = ymServer;
        if(ymServer.equals(getString(R.string.server_demo3))){
            appBaseUrl = "https://demo3.ym.integral.net/fxi/";
        }
        if(ymServer.equals(getString(R.string.server_demo))){
            appBaseUrl = "https://demo.ym.integral.net/fxi/";
        }
    }

    public String getYmServer() {
        return ymServer;
    }

    public static String getAppBaseUrl() {
        return appBaseUrl;
    }

    public static String getLoginUrl(){
       return Utils.getAPIUrl(getAppBaseUrl(), LOGIN_URL);
    }

    public static String getRiskRulesUrl(LoginRequest request){
        loginRequest = request;

        String url = Utils.getAPIUrl(getAppBaseUrl(), RULES_URL);
        url += "?" + AppConstants.PARAM_KEY_ORG + "=" + request.getOrganization()
                + "&" + AppConstants.PARAM_KEY_NAMESPACE + "=" + request.getOrganization();

        return url;
    }

    public static String getRWSnapshotUrl(){
        String url = Utils.getAPIUrl(getAppBaseUrl(), SNAPSHOt_URL);
        url += "?" + AppConstants.PARAM_KEY_ORG + "=" + loginRequest.getOrganization();

        return url;
    }

    public static String getRefDataUrl(){

        String url = Utils.getAPIUrl(getAppBaseUrl(), REF_DATA_URL);

        return url;
    }

    //fxiapi/historicaldata/quoteHistory?instrument=EUR/USD&period=HOUR&count=1440&_=1416895669040
    public static String getRateDataUrl(String ccyPair){

        String url = Utils.getAPIUrl(getAppBaseUrl(), RATE_DATA_URL);

        url += "?" + AppConstants.PARAM_KEY_INSTRUMENT + "=" + ccyPair
                + "&" + AppConstants.PARAM_KEY_PERIOD + "=HOUR"
                + "&" + AppConstants.PARAM_KEY_COUNT + "=24";


        return url;
    }

    //Historical data
    //fxiapi/riskwarehouse/historicalData?ccyPair=EUR/USD&org=YMSBADemo&_=1416895669041
    public static String getHistoricalDataUrl(String ccyPair){

        String url = Utils.getAPIUrl(getAppBaseUrl(), HISTORICAL_DATA_URL);

        url += "?" + AppConstants.PARAM_KEY_CCYPAIR + "=" + ccyPair
                + "&" + AppConstants.PARAM_KEY_ORG + "=" +loginRequest.getOrganization();

        return url;
    }

    public Typeface getTypeface() {
        return typeface;
    }
}
