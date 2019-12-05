package com.u17025958.inventorymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE_PRODUCTS = "products";
    private static final String PRODUCT_KEY_ID = "_id";
    private static final String PRODUCT_KEY_NAME = "NAME_COLUMN";
    private static final String PRODUCT_KEY_PRICE = "PRICE_COLUMN";
    private static final String PRODUCT_KEY_SIZE = "SIZE_COLUMN";
    private static final String PRODUCT_KEY_IMAGE = "IMAGE_COLUMN";
    private static final String CREATE_DATABASE_TABLE_PRODUCTS = "CREATE TABLE "
            + DATABASE_TABLE_PRODUCTS + "(" + PRODUCT_KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PRODUCT_KEY_NAME + " TEXT,"
            + PRODUCT_KEY_PRICE + " REAL,"
            + PRODUCT_KEY_SIZE + " REAL,"
            + PRODUCT_KEY_IMAGE + " TEXT );";

    private static final String DATABASE_TABLE_WAREHOUSES = "warehouses";
    private static final String WAREHOUSE_KEY_ID = "_id";
    private static final String WAREHOUSE_KEY_LOCATION = "LOCATION_COLUMN";
    private static final String WAREHOUSE_KEY_CAPACITY = "CAPACITY_COLUMN";
    private static final String CREATE_DATABASE_TABLE_WAREHOUSES = "CREATE TABLE "
            + DATABASE_TABLE_WAREHOUSES + "(" + WAREHOUSE_KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + WAREHOUSE_KEY_LOCATION + " TEXT,"
            + WAREHOUSE_KEY_CAPACITY + " REAL );";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_TABLE_PRODUCTS);
        db.execSQL(CREATE_DATABASE_TABLE_WAREHOUSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE_PRODUCTS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE_WAREHOUSES + "'");
        onCreate(db);
    }

    public LinkedList<ProductMemberModel> getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedList<ProductMemberModel> productList = new LinkedList<>();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            ProductMemberModel product = new ProductMemberModel();
            product.setId(cursor.getInt(cursor.getColumnIndex(PRODUCT_KEY_ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_NAME)));
            product.setPrice(cursor.getFloat(cursor.getColumnIndex(PRODUCT_KEY_PRICE)));
            product.setSize(cursor.getFloat(cursor.getColumnIndex(PRODUCT_KEY_SIZE)));
            product.setImage(cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_IMAGE)));
            productList.addLast(product);
        }
        return productList;
    }

    public LinkedList<WarehouseMemberModel> getAllWarehouses() {
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedList<WarehouseMemberModel> warehouseList = new LinkedList<>();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE_WAREHOUSES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            WarehouseMemberModel warehouse = new WarehouseMemberModel();
            warehouse.setId(cursor.getInt(cursor.getColumnIndex(WAREHOUSE_KEY_ID)));
            warehouse.setLocation(cursor.getString(cursor.getColumnIndex(WAREHOUSE_KEY_LOCATION)));
            warehouse.setCapacity(cursor.getFloat(cursor.getColumnIndex(WAREHOUSE_KEY_CAPACITY)));
            warehouseList.addLast(warehouse);
        }
        return warehouseList;
    }

    public long addProduct(String name, float price, float size, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(PRODUCT_KEY_NAME, name);
        newValues.put(PRODUCT_KEY_PRICE, price);
        newValues.put(PRODUCT_KEY_SIZE, size);
        newValues.put(PRODUCT_KEY_IMAGE, image);
        return db.insert(DATABASE_TABLE_PRODUCTS, null, newValues);
    }

    public void updateProduct(int id, String name, float price, float size, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(PRODUCT_KEY_NAME, name);
        updatedValues.put(PRODUCT_KEY_PRICE, price);
        updatedValues.put(PRODUCT_KEY_SIZE, size);
        updatedValues.put(PRODUCT_KEY_IMAGE, image);
        String where = PRODUCT_KEY_ID + " = ?";
        String whereArgs[] = new String[]{String.valueOf(id)};
        db.update(DATABASE_TABLE_PRODUCTS, updatedValues, where, whereArgs);
    }

    public LinkedList<String> getProductNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedList<String> names = new LinkedList<>();
        String selectQuery = "SELECT " + PRODUCT_KEY_NAME + " FROM " + DATABASE_TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            names.addLast(cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_NAME)));
        }
        return names;
    }

    public ProductMemberModel getProductByID(int id) {
        // TODO: does not deal with invalid ids

        SQLiteDatabase db = this.getReadableDatabase();
        // TODO: NOT SECURE!! Vulnerable to SQL injection, temporary solution
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE_PRODUCTS
                + " WHERE " + PRODUCT_KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToNext();

        ProductMemberModel product = new ProductMemberModel();
        product.setId(cursor.getInt(cursor.getColumnIndex(PRODUCT_KEY_ID)));
        product.setName(cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_NAME)));
        product.setPrice(cursor.getFloat(cursor.getColumnIndex(PRODUCT_KEY_PRICE)));
        product.setSize(cursor.getFloat(cursor.getColumnIndex(PRODUCT_KEY_SIZE)));
        product.setImage(cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_IMAGE)));
        return product;
    }
}
