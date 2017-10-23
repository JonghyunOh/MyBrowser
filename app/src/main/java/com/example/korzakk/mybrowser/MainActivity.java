package com.example.korzakk.mybrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private WebView myWebView = null;
    private EditText myHeaderText = null;
    private Button myMoveButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webViewID);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings ws = myWebView.getSettings();
        ws.setJavaScriptEnabled(true);

        myHeaderText = (EditText) findViewById(R.id.addressTxtID);
        myHeaderText.setOnEditorActionListener(this);


        myMoveButton = (Button) findViewById(R.id.moveBtnID);
        myMoveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "working fine", Toast.LENGTH_LONG).show();
        String url = "";
        url = myHeaderText.getText().toString();
        if (!url.toLowerCase().startsWith("http://")) {
            url = "http://" + url;
        }
        myWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            myMoveButton.callOnClick();
//            onClick(v);
        }
        return true;
    }


}
