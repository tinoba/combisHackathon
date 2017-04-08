package combis.hackathon.ui.survey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import combis.hackathon.R;

import static combis.hackathon.device.CombisFirebaseMessagingService.SURVEY_EXTRA;

public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        String surveyUrl = getIntent().getStringExtra(SURVEY_EXTRA);
        Toast.makeText(this, surveyUrl, Toast.LENGTH_SHORT).show();
    }
}
