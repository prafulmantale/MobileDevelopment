package prafulmantale.listexplore.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import prafulmantale.listexplore.R;

public class CursorAdapterActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor = getContacts();
        startManagingCursor(cursor);

        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, new String [] { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                new int[] {android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cursor_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Cursor getContacts(){

        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String [] projection = new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};

        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + ("1") + "'";

        String [] selectionArgs = null;

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }
}
