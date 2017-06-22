package com.eulercarvalho.qualcurso;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;


/**
 * Created by TiagoCarvalho on 22/06/17.
 */

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);

        String url;

        if (getIntent() != null) {
            url = getIntent().getStringExtra("URL");
            Log.d("URL", url);
        }else {
            url = "www.google.com.br";
            Log.d("URL", "deu ruim");
        }


        Toast.makeText(WebViewActivity.this,
                "URL: " + url + "\n",
                Toast.LENGTH_LONG).show();

        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
