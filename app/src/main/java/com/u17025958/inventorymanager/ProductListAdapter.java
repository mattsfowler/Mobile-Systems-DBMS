package com.u17025958.inventorymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final LinkedList<ProductMemberModel> mProductList;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context,
                           LinkedList<ProductMemberModel> productList) {
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
        ProductMemberModel mCurrent = mProductList.get(position);
        holder.productItemView.setText(mCurrent.getName());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView productItemView;
        final ProductListAdapter mAdapter;

        public ProductViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            int mPosition = getLayoutPosition();
            ProductMemberModel element = mProductList.get(mPosition);
            Intent intent = new Intent(context, AddProduct.class);
            intent.putExtra("id", element.getId());
            context.startActivity(intent);
        }
    }
}
