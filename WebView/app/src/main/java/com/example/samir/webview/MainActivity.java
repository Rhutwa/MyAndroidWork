package com.example.samir.webview;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity
{
    private WebView webview1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview1 = (WebView) findViewById(R.id.webview1);
        webview1.loadUrl("http://www.google.com");
        webview1.setWebViewClient(new WebViewClient()
        {
            /*@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            //public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                view.loadUrl(url);
                return true;
            }*/
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                view.loadUrl(request.getUrl().toString());
                return true;

            }
        });
    }
}
