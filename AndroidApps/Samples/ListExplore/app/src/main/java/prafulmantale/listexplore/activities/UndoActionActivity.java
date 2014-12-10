package prafulmantale.listexplore.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import prafulmantale.listexplore.R;

public class UndoActionActivity extends Activity {

    private View viewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undo_action);

        ListView listView = (ListView)findViewById(R.id.listview);
        String []values = new String[]{"Mac", "Windows", "Unix", "Linix", "WhatNot"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);


        viewContainer = findViewById(R.id.undobar);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showUndo(viewContainer);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.undo_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        return super.onOptionsItemSelected(item);

        showUndo(viewContainer);
        return true;
    }

    public void onClick(View view){
        Toast.makeText(this, "Deletion undone", Toast.LENGTH_LONG).show();
        viewContainer.setVisibility(View.GONE);
    }


    public static void showUndo(final View viewContainer){

        viewContainer.setVisibility(View.VISIBLE);
        viewContainer.setAlpha(1);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {

            viewContainer.animate().setDuration(5000).alpha(0.4f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    viewContainer.setVisibility(View.GONE);
                }
            });
        }
        else {
            viewContainer.animate().alpha(0.4f).setDuration(5000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    viewContainer.setVisibility(View.GONE);
                }
            });
        }
    }
}
