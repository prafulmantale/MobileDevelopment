package prafulmantale.praful.com.expensereport.models;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/18/14.
 */
public class ExpenseItem implements Serializable{

    private double expense;
    private String memo;
    private String category;

    public ExpenseItem() {
        expense = 0;
        memo = "";
        category = "";
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ExpenseItem{" +
                "expense=" + expense +
                ", memo='" + memo + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
