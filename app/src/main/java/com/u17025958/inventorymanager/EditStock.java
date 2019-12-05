package com.u17025958.inventorymanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.LinkedList;

public class EditStock extends AppCompatActivity {

    DatabaseManager DBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int product_id = intent.getIntExtra("product_id", -1);
        String product_name = intent.getStringExtra("product_name");
        String amount = intent.getStringExtra("amount");

        DBManager = new DatabaseManager(this);
        Spinner spinner = findViewById(R.id.spnProduct);
        LinkedList<String> productsList = DBManager.getProductNames();
        String[] products = productsList.toArray(new String[productsList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, products);
        spinner.setAdapter(adapter);
    }

    public void onFABClick(View view) {

    }

}
