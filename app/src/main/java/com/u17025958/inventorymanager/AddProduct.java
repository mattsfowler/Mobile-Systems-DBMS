package com.u17025958.inventorymanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtPrice = findViewById(R.id.txtPrice);
        txtPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(9, 2)});
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
        //confirm changes made to the entry.
    }
    public void onSetImageClick(View view)
    {
        //open a directory browser dialogue
    }

}
