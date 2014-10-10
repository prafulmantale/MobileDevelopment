package prafulmantale.praful.com.yaym.networking;

import android.content.Context;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class YMHttpClient extends DefaultHttpClient {

    private static final String TAG = YMHttpClient.class.getName();

    private static List<Cookie> cookies;

    public YMHttpClient(ClientConnectionManager conman, HttpParams params) {
        super(conman, params);
    }

    public YMHttpClient() {
    }

    public static YMHttpClient getInstance(){
        YMHttpClient client = sslClient(new DefaultHttpClient());

        if(cookies != null){
            for (Cookie cookie : cookies){
                client.getCookieStore().addCookie(cookie);
            }
        }


        return client;
    }


    public static void storeCookies(DefaultHttpClient client, Context context){

        cookies = client.getCookieStore().getCookies();

        //IDC.printDebugMessage( "Storing cookies");

        // CookieSyncManager.createInstance(context);
        // if (cookies != null) {
        // for (Cookie cookie : cookies) {
        // String cookieString = cookie.getName() + "="
        // + cookie.getValue() + "; domain=" + cookie.getDomain();
        // CookieManager.getInstance().setCookie(cookie.getDomain(),
        // cookieString);
        // }
        // }
        // CookieSyncManager.getInstance().sync();

    }

    public static void clear() {
        //IDC.printDebugMessage( "Clearning cookies");
        cookies = null;
    }

    private static YMHttpClient sslClient(HttpClient client){

        try{

            X509TrustManager manager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{manager}, null);
            org.apache.http.conn.ssl.SSLSocketFactory factory = new YMSSLSocketFactory(context);
            factory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = client.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", factory, 443));

            return new YMHttpClient(ccm, client.getParams());

        }
        catch (Exception ex){
            return null;
        }

    }

}
