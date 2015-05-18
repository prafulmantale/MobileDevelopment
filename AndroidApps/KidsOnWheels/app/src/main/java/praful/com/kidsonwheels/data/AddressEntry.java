package praful.com.kidsonwheels.data;

import android.provider.BaseColumns;

/**
 * Created by prafulmantale on 5/17/15.
 */
public class AddressEntry implements BaseColumns {
    public static final String TABLE_NAME = "address";
    public static final String COLUMN_HOUSE_NUMBER = "hn";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_PIN_CODE = "pin";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LANG = "lng";
}
