package com.u17025958.inventorymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context context,
                           LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView wordItemView;
        final ProductListAdapter mAdapter;

        public WordViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.lblProduct);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {}
    }
}
