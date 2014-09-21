package prafulmantale.praful.com.expensereport.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import prafulmantale.praful.com.expensereport.R;
import prafulmantale.praful.com.expensereport.models.ExpenseItem;

public class CreateExpenseActivity extends Activity {

    private ExpenseItem expenseItem;
    private EditText evExpense;
    private EditText evMemo;
    private EditText evCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        evExpense = (EditText)findViewById(R.id.etExpense);
        evMemo = (EditText)findViewById(R.id.etMemo);
        evCategory = (EditText)findViewById(R.id.etCategory);

        expenseItem = (ExpenseItem)getIntent().getSerializableExtra("EI");

        evCategory.setText(expenseItem.getCategory());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_expense, menu);


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

    public void onSubmit(MenuItem mi){

        expenseItem.setExpense(Double.parseDouble(evExpense.getText().toString()));
        expenseItem.setCategory(evCategory.getText().toString());
        expenseItem.setMemo(evMemo.getText().toString());

        Intent intent = new Intent();

        intent.putExtra("EI", expenseItem);

        setResult(RESULT_OK, intent);


        finish();
    }
}
