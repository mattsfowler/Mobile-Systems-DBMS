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

public class ViewWarehouses extends AppCompatActivity {

    private final LinkedList<WarehouseMemberModel> mWarehouseList = new LinkedList<>();
    private RecyclerView rcyWarehouseList;
    private WarehouseListAdapter wlAdapter;
    private DatabaseManager DBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_warehouses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBManager = new DatabaseManager(this);
        mWarehouseList.addAll(DBManager.getAllWarehouses());
        rcyWarehouseList = findViewById(R.id.rcyWarehouseList);
        wlAdapter = new WarehouseListAdapter(this, mWarehouseList);
        rcyWarehouseList.setAdapter(wlAdapter);
        rcyWarehouseList.setLayoutManager(new LinearLayoutManager(this)); //default layout manager
    }

    public void onFABClick(View view) {
        Intent intent = new Intent(this, ViewWarehouseStock.class);
        Snackbar.make(view, "Opening warehouse stock view", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        intent.putExtra("id", -1);
        startActivity(intent);
        wlAdapter.notifyDataSetChanged();
    }

}
