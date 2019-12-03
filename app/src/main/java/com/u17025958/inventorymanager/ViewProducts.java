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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ViewProducts extends AppCompatActivity {

    private final LinkedList<String> mProductList = new LinkedList<>();
    private RecyclerView rcyProductList;
    private ProductListAdapter plAdapter;
    private DatabaseManager DBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBManager = new DatabaseManager(this);
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
        intent.putExtra("id", -1);
        startActivity(intent);
    }

    public void getProductList(){
        ArrayList<ProductMemberModel> products = DBManager.getAllProducts();
        Iterator i = products.iterator();
        while (i.hasNext()) {
            ProductMemberModel product = (ProductMemberModel)i.next();
            mProductList.addLast(product.getName());
        }
    }

}
