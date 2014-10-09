package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import prafulmantale.praful.com.twitterapp.R;

/**
 * Created by prafulmantale on 10/8/14.
 */
public class TimelineCursorAdapter extends CursorAdapter {


    public TimelineCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_timeline_row, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
