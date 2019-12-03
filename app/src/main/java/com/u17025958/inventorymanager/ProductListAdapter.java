package com.u17025958.inventorymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;


public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final LinkedList<String> mProductList;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context,
                           LinkedList<String> productList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = productList;
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.productlist_item, parent, false);
        return new ProductViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        String mCurrent = mProductList.get(position);
        holder.productItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView productItemView;
        public final int productID;
        final ProductListAdapter mAdapter;

        public ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String element = mProductList.get(mPosition);
            // open the edit product activity with the selected product
            Snackbar.make(v, "Clicked on: " + element, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            Intent intent = new Intent(this, AddProduct.class);
            intent.putExtra("id", this.productID);
        }
    }
}
