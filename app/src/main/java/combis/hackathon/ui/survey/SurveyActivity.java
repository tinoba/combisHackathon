package combis.hackathon.ui.survey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;

import static combis.hackathon.device.CombisFirebaseMessagingService.SURVEY_EXTRA;

public class SurveyActivity extends AppCompatActivity {

    @BindView(R.id.survey_webview)
    protected WebView surveyWebView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        ButterKnife.bind(this);

        String surveyUrl = getIntent().getStringExtra(SURVEY_EXTRA);
        if (surveyUrl != null) {
            url = surveyUrl;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setWebView();
    }

    private void setWebView() {
        surveyWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        surveyWebView.getSettings().setUseWideViewPort(true);
        surveyWebView.getSettings().setJavaScriptEnabled(true);
        surveyWebView.setWebViewClient(new WebViewClient());
        surveyWebView.requestFocus();
        surveyWebView.loadUrl(url);

    }
}
