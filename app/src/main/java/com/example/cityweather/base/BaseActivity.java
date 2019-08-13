package com.example.cityweather.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity<VB extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    public abstract int getLayoutId();
    protected VB binding;
    protected VM viewModel;

    public abstract Class<VM> getClassViewModel();
    public abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = ViewModelProviders.of(this).get(getClassViewModel());
        initView();
    }

    public void onClick(View view){

    }
}
