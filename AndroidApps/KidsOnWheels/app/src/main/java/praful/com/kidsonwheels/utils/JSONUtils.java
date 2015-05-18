package praful.com.kidsonwheels.utils;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by prafulmantale on 4/12/15.
 */
public final class JSONUtils {

    private static final String TAG = JSONUtils.class.getSimpleName();
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T parseJSON(String json, Class<T> clazz) {
        T value = null;
        if (TextUtils.isEmpty(json)) {
            return value;
        }
        try {
            value = mapper.readValue(json, clazz);
        } catch (JsonParseException jpe) {
            Log.d(TAG, "parseJSON - JsonParseException while parsing json: " + json);
        } catch (JsonMappingException jme) {
            Log.d(TAG, "parseJSON - JsonMappingException while parsing json: " + json);
        } catch (IOException ioe) {
            Log.d(TAG, "parseJSON - IOException while parsing json: " + json);
        }

        return value;
    }
}
