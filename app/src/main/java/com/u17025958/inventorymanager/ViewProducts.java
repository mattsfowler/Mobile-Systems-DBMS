package com.u17025958.inventorymanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.LinkedList;

public class ViewProducts extends AppCompatActivity {

    private final LinkedList<String> mProductList = new LinkedList<>();
    private RecyclerView rcyProductList;
    private ProductListAdapter plAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getProductList();
        rcyProductList = findViewById(R.id.rcyProductList);
        plAdapter = new ProductListAdapter(this, mProductList);
        rcyProductList.setAdapter(plAdapter);
        rcyProductList.setLayoutManager(new LinearLayoutManager(this)); //default layout manager
    }

    public void onFABClick(View view)
    {
        Intent intent = new Intent(this, AddProduct.class);
        Snackbar.make(view, "Opening add product", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        startActivity(intent);
    }

    public void getProductList(){

        // Should use the database manager to get products from the database
        // For now, we hardcode some basic values
        mProductList.addLast("Trampoline (3)");
        mProductList.addLast("Laptop (1)");
        mProductList.addLast("Blender (12)");
        mProductList.addLast("Tortoise (4)");
        mProductList.addLast("Avocado (333)");
    }

}
