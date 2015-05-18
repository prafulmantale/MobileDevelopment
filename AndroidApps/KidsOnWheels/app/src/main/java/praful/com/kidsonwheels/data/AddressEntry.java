package praful.com.kidsonwheels.data;

import android.provider.BaseColumns;

/**
 * Created by prafulmantale on 5/17/15.
 */
public class AddressEntry implements BaseColumns {
    public static final String TABLE_NAME = "address";
    public static final String COLUMN_PLACE_ID = "address_id";
    public static final String COLUMN_ADDRESS_STRING = "addr";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LANG = "lng";
}
