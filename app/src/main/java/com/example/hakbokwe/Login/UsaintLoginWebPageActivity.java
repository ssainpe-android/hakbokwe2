/*
Warning
1. WebView variable is not defined by class field.
2. WebView can getHtml() when its visibility is VISIBLE.

2023-11-20
TODO list check plz.
 */

package com.example.hakbokwe.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hakbokwe.databinding.ActivityLoginWebPageBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsaintLoginWebPageActivity extends AppCompatActivity {
    private String studentName;
    private String studentId;
    ActivityLoginWebPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginWebPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setLoginPage(binding.usaintLoginWv);
        loadLoginPage(binding.usaintLoginWv);
    }

    @SuppressLint("SetJavaScriptEnabled")
    void setLoginPage(@NonNull WebView webView) {
        webView.getSettings().setLoadWithOverviewMode(true);                                        // html content를 WebView 크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야 함
        webView.getSettings().setUseWideViewPort(true);                                             // setLoadWithOverviewMode 와 같이 써야 함
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);                                        // 줌 확대/축소 버튼 여부
        webView.getSettings().setSupportMultipleWindows(true);                                      // 멀티 윈도우 사용 여부
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);                       // javascript가 window.open()을 사용할 수 있도록 설정
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");               // name parameter must be same as view.loadUrl(*.Android.*)

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new UsaintWebViewClient());
    }

    void loadLoginPage(@NonNull WebView webView) {
        Log.d("park", "Starting to load loginPage...");
        webView.loadUrl("https://smartid.ssu.ac.kr/Symtra_sso/smln.asp?apiReturnUrl=https%3A%2F%2Fsaint.ssu.ac.kr%2FwebSSO%2Fsso.jsp");
    }

    private class UsaintWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (isLoginSuccessful(request)) {
                Log.d("park", "login success!");
                loadProgressBar();
            } else {
                Log.d("park", "Trying to login, Current loaded url: " + request.getUrl().toString());
            }
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (isLoginSuccessful(url)) {
                getInfoFromHtml(view);
                progressForResult();
                Intent intentForReturn = getIntent().putExtra("studentName", studentName).putExtra("studentId", studentId);
                setResult(RESULT_OK, intentForReturn);
                finish();
            }
            super.onPageFinished(view, url);
        }
    }

    void loadProgressBar() {
        binding.topStatusTv.setVisibility(View.GONE);
        binding.progressContent.setVisibility(View.VISIBLE);
        binding.usaintLoginWv.setVisibility(View.INVISIBLE);
    }

    private void getInfoFromHtml(WebView view) {
        view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('body')[0].innerHTML);");
    }

    void progressForResult() {
        while (studentId == null) {
            SystemClock.sleep(500);
        }
    }

    private boolean isLoginSuccessful(WebResourceRequest request) {
        return request.getUrl().toString().equals("https://saint.ssu.ac.kr/irj/portal");
    }

    private boolean isLoginSuccessful(String url) {
        return url.equals("https://saint.ssu.ac.kr/irj/portal");
    }

    private class MyJavaScriptInterface {
        @JavascriptInterface
        public void getHtml(String html) {
            Document doc = Jsoup.parse(html);

            studentName = doc.select("span.top_user").first().text().split("님")[0];
            Log.d("park", "Found StudentName:" + studentName);

            Elements scriptElements = doc.getElementsByTag("script");
            Pattern pattern = Pattern.compile("\"LogonUid\":\"([^,]*)\"");   //find the line which contains "LogonUid":"12345678",
            Matcher matcher = null;
            for (Element element : scriptElements) {
                if (element.data().contains("LogonUid")) {
                    matcher = pattern.matcher(element.data());
                    if (matcher.find()) {
                        studentId = matcher.group(1);
                        Log.d("park", "Found studentId:" + studentId);
                    }
                    break;
                }
            }
        }
    }
}