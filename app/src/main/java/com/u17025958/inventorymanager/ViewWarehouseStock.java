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
import android.widget.TextView;

import java.util.LinkedList;

public class ViewWarehouseStock extends AppCompatActivity {

    private final LinkedList<StockMemberModel> mStockList = new LinkedList<>();
    private RecyclerView rcyProductList;
    private StockListAdapter slAdapter;
    private DatabaseManager DBManager;
    private int warehouseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_warehouse_stock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        warehouseID = intent.getIntExtra("warehouse_id", 1);

        DBManager = new DatabaseManager(this);
        mStockList.addAll(DBManager.getStockOf(warehouseID));
        rcyProductList = findViewById(R.id.rcyProductList);
        slAdapter = new StockListAdapter(this, mStockList);
        rcyProductList.setAdapter(slAdapter);
        rcyProductList.setLayoutManager(new LinearLayoutManager(this)); //default layout manager

        TextView lblWarehouseName = findViewById(R.id.lblWarehouseName);
        lblWarehouseName.setText(DBManager.getWarehouseName(warehouseID));
    }

    public void onFABClick(View view) {
        Intent intent = new Intent(this, EditStock.class);
        Snackbar.make(view, "Opening edit stock", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        intent.putExtra("product_id", -1);
        intent.putExtra("product_name", "");
        intent.putExtra("amount", 0);
        startActivity(intent);
    }

}
