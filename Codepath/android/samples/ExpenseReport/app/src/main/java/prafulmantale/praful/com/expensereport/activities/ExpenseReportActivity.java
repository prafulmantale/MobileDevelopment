package prafulmantale.praful.com.expensereport.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import prafulmantale.praful.com.expensereport.R;
import prafulmantale.praful.com.expensereport.models.ExpenseItem;


public class ExpenseReportActivity extends Activity {

    private final int REQUEST_CODE_CREATE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_report);
    }


    private void showCreateExpenseActivity(){
        Intent intent = new Intent(this, CreateExpenseActivity.class);

        ExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setCategory("Work");

        intent.putExtra("EI", expenseItem);

        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //request code
        //result code
        ExpenseItem expenseItem = (ExpenseItem)data.getSerializableExtra("EI");
        System.out.println("resultCode: " + resultCode + "requestCode: " + requestCode + "\r\n" + expenseItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_report, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){

            case R.id.miCreate:
                showCreateExpenseActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
