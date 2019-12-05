package com.u17025958.inventorymanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProduct extends AppCompatActivity {

    private DatabaseManager DBManager;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtPrice = findViewById(R.id.txtPrice);
        TextView txtSize = findViewById(R.id.txtSize);
        txtPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(9, 2)});

        DBManager = new DatabaseManager(this);
        Intent intent = getIntent();
        this.id = intent.getIntExtra("id", -1);
        if (this.id > -1) {
            Log.d("AddProduct", "id = " + this.id);
            ProductMemberModel product = DBManager.getProductByID(id);
            txtName.setText(product.getName());
            txtPrice.setText(( ((Float)product.getPrice()).toString() ));
            txtSize.setText(( ((Float)product.getSize()).toString() ));
        }
    }

    // Taken from Stack Overflow: https://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext
    public class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }
    }
    // End of section taken from Stack Overflow

    public void onFABClick(View view)
    {
        String name = ((TextView)findViewById(R.id.txtName)).getText().toString();
        float price = Float.parseFloat(((TextView)findViewById(R.id.txtPrice)).getText().toString());
        float size = Float.parseFloat(((TextView)findViewById(R.id.txtSize)).getText().toString());
        if (this.id > -1) {
            DBManager.updateProduct(id, name, price, size, "");
            Snackbar.make(view, "Product updated", Snackbar.LENGTH_LONG).show();
        }
        else {
            this.id = (int) DBManager.addProduct(name, price, size, "");
            Snackbar.make(view, "New product created!", Snackbar.LENGTH_LONG).show();
        }
    }
    public void onSetImageClick(View view)
    {
        //open a directory browser dialogue
    }

}
