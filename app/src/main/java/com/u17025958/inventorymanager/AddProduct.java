package com.u17025958.inventorymanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProduct extends AppCompatActivity {

    private DatabaseManager DBManager;
    private int id;
    TextView txtName;
    TextView txtPrice;
    TextView txtSize;
    TextView txtWarehouses;
    ImageView imgImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtSize = findViewById(R.id.txtSize);
        txtWarehouses = findViewById(R.id.txtWarehouses);
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
            txtWarehouses.setText(DBManager.getWarehouseByID( product.getWarehouse() ));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_remove) {
            if (DBManager.removeProduct(this.id)) {
                Snackbar.make(findViewById(R.id.cslEditProduct), "Product removed", Snackbar.LENGTH_LONG).show();
                finish();
            } else {
                Snackbar.make(findViewById(R.id.cslEditProduct), "Could not be deleted", Snackbar.LENGTH_LONG).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        String name = txtName.getText().toString();
        float price = Float.parseFloat(txtPrice.getText().toString());
        float size = Float.parseFloat(txtSize.getText().toString());
        String warehouse = txtWarehouses.getText().toString();

        int warehouseID = DBManager.getWarehouseByLocation(warehouse);

        if (warehouseID > -1) {
            if (this.id > -1) {
                DBManager.updateProduct(id, name, price, size, warehouseID, "");
                Snackbar.make(view, "Product updated: " + warehouseID, Snackbar.LENGTH_LONG).show();
            } else {
                this.id = (int) DBManager.addProduct(name, price, size, "");
                Snackbar.make(view, "New product created!", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(view, "Invalid warehouse, not saved!", Snackbar.LENGTH_LONG).show();
        }
    }
    public void onSetImageClick(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivity(intent);
        Uri uri = intent.getData();

    }

}
