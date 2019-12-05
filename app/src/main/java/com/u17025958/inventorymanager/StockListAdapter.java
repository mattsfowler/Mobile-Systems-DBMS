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

public class StockListAdapter extends
        RecyclerView.Adapter<StockListAdapter.StockViewHolder> {

    private final LinkedList<String> mStockList;
    private LayoutInflater mInflater;

    public StockListAdapter(Context context,
                              LinkedList<String> stockList) {
        mInflater = LayoutInflater.from(context);
        this.mStockList = stockList;
    }

    @NonNull
    @Override
    public StockListAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.productlist_item, parent, false);
        return new StockViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        String mCurrent = mStockList.get(position);
        holder.stockItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mStockList.size();
    }

    class StockViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView stockItemView;
        final StockListAdapter mAdapter;

        public StockViewHolder(View itemView, StockListAdapter adapter) {
            super(itemView);
            stockItemView = itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // In initial design, clicking on a specific item would take you to the stock-editor for that item.
            // In redesign, the stock cannot be edited from this activity.
        }
    }
}

