package com.example.cityweather.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cityweather.Constant;
import com.example.cityweather.R;
import com.example.cityweather.base.BaseActivity;
import com.example.cityweather.databinding.ActivityWikiBinding;
import com.example.cityweather.model.WikiModel;
import com.example.cityweather.viewmodel.WikiViewModel;

public class WikiActivity extends BaseActivity<ActivityWikiBinding, WikiViewModel> {
    String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wiki;
    }

    @Override
    public Class<WikiViewModel> getClassViewModel() {
        return WikiViewModel.class;
    }

    @Override
    public void initView() {
        getExtra();
        final int[] progressLoading = {0};
        binding.setLifecycleOwner(this);
        viewModel.setData(new WikiModel(0));
        binding.setWikiModel(viewModel);
        binding.webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                viewModel.getData().setValue(new WikiModel(progress));
                // Return the app name after finish loading
                if (progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl(url);

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swiperefresh.setRefreshing(false);
                binding.webview.reload();
            }
        });
    }

    private void getExtra() {
        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.PLACE_WIKI_LINK);
    }
}
