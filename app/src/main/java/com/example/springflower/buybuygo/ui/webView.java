package com.example.springflower.buybuygo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.springflower.buybuygo.R;


public class webView extends Activity {

    WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        mWebView = (WebView)findViewById(R.id.webview);

        mWebView.setWebViewClient(mWebViewClient);
        switch(couponFrag.selectPos){
            case 0:
                mWebView.loadUrl("http://www.kfcclub.com.tw/Menu/Coupon/");
                break;
            case 1:
                mWebView.loadUrl("http://campaign.mcdonalds.com.tw/coupon20141201/m/");
                break;
            case 2:
                mWebView.loadUrl("http://tw.yahoo.com");
                break;
            case 3:
                mWebView.loadUrl("http://tw.yahoo.com");
                break;

        }

    }


    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };
}