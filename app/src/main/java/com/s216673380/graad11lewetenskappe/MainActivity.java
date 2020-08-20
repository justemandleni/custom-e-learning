package com.s216673380.graad11lewetenskappe;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private final String URL = "file:///android_asset/html/raw/contents.html";
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mWebView = findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setUseWideViewPort(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.loadUrl(URL);
        if(savedInstanceState==null)
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(URL);
                }
            });
        //mWebView.setWebViewClient(new WebViewClient(){
           //public boolean shouldOverrideUrlLoading(WebView view, String url) {
           //    view.loadUrl(url);
           //    return true;
           //}

           //public void onPageStarted(WebView view, String url, Bitmap favicon) {
           //    super.onPageStarted(view, url, favicon);
           //    MainActivity.this.mProgressBar.setVisibility(0);
           //}

           //public void onPageFinished(WebView view, String url) {
           //    super.onPageFinished(view, url);
           //    MainActivity.this.mProgressBar.setVisibility(8);
           //}

           //public void onReceivedError(WebView webView, WebResourceRequest resourceRequest, WebResourceError
        // resourceError) {
           //    webView.loadUrl("about:blank");
           //}
        //});
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() on called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() on called");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged(newConfig) called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(Bundle) called");
        mWebView.saveState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i(TAG, "onRestoreInstanceState(Bundle) called");
        mWebView.restoreState(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        Log.d(TAG, "onKeyDown(keyCode, event) called");
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (mWebView.canGoBack())
                    mWebView.goBack();
                else {
                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
                    aBuilder.setMessage("Are you sure you want to close the application?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    AlertDialog alertDialog = aBuilder.create();
                    alertDialog.show();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
