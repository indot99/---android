package org.richcode.koreatoron;

import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebBroswerActivity extends AppCompatActivity {

    AdView adView;
    WebView wb;

    String URL;

    OptionsFabLayout optionsFabLayout;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_broswer);

        adView = (AdView)findViewById(R.id.korea_banner_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intent = getIntent();
        URL = intent.getStringExtra("link");

        wb = (WebView)findViewById(R.id.webview);
        optionsFabLayout = (OptionsFabLayout)findViewById(R.id.fab_options);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        optionsFabLayout.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.green_fab
        );

        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl(URL);

        optionsFabLayout.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        optionsFabLayout.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {
                switch (fabItem.getItemId()){
                    case R.id.fab_link:
                        clipboardManager.setText(wb.getUrl());
                        break;
                    case R.id.fab_share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"title");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "content");

                        Intent chooser = Intent.createChooser(shareIntent, "공유");
                        startActivity(chooser);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && wb.canGoBack()){
            wb.goBack();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
