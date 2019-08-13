package com.example.cityweather.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseAdapter <T, VB extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder>{
    Context mContext;
    ArrayList<T> listData;
    protected VB binding;

    public BaseAdapter(Context mContext, ArrayList<T> listData) {
        this.mContext = mContext;
        this.listData = listData;
    }

    public abstract int getLayoutId();
    public abstract int getId();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), viewGroup, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.setVariable(getId(), listData.get(i));
    }

    public static class BaseViewHolder<T, VB extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private VB binding;
        public BaseViewHolder(VB view) {
            super(view.getRoot());
            this.binding = view;
        }

        public void setVariable(int id, T data){
            binding.setVariable(id, data);
            binding.executePendingBindings();
        }
    }

    public void setListData(ArrayList<T> listData){
        this.listData = listData;
        notifyDataSetChanged();
    }
}
