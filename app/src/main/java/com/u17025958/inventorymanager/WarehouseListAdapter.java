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

public class WarehouseListAdapter extends
        RecyclerView.Adapter<WarehouseListAdapter.WarehouseViewHolder> {

    private final LinkedList<WarehouseMemberModel> mWarehouseList;
    private LayoutInflater mInflater;

    public WarehouseListAdapter(Context context,
                                LinkedList<WarehouseMemberModel> warehouseList) {
        mInflater = LayoutInflater.from(context);
        this.mWarehouseList = warehouseList;
    }

    @NonNull
    @Override
    public WarehouseListAdapter.WarehouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.warehouselist_item, parent, false);
        return new WarehouseViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseListAdapter.WarehouseViewHolder holder, int position) {
        WarehouseMemberModel mCurrent = mWarehouseList.get(position);
        holder.warehouseItemView.setText(mCurrent.getLocation());
    }

    @Override
    public int getItemCount() {
        return mWarehouseList.size();
    }

    class WarehouseViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView warehouseItemView;
        final WarehouseListAdapter mAdapter;

        public WarehouseViewHolder(View itemView, WarehouseListAdapter adapter) {
            super(itemView);
            warehouseItemView = itemView.findViewById(R.id.warehouse);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            int mPosition = getLayoutPosition();
            WarehouseMemberModel element = mWarehouseList.get(mPosition);
            Intent intent = new Intent(context, ViewWarehouseStock.class);
            intent.putExtra("warehouse_id", element.getId());
            context.startActivity(intent);
        }
    }
}
