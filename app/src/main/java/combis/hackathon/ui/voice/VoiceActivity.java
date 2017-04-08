package combis.hackathon.ui.voice;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

import combis.hackathon.R;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class VoiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                        "combis.hackathon");

        SpeechRecognizer recognizer = SpeechRecognizer
                .createSpeechRecognizer(this.getApplicationContext());
        RecognitionListener listener = new RecognitionListener() {

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> voiceResults = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (voiceResults == null) {
                    Timber.e("No voice results");
                } else {
                    Timber.e("Printing matches: ");

                    for (String match : voiceResults) {
                        Timber.e(match);
                    }
                }
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                Timber.e("Ready for speech");
            }

            @Override
            public void onError(int error) {
                Timber.e("Error listening for speech: " + error);
            }

            @Override
            public void onBeginningOfSpeech() {
                Timber.e("Speech starting");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onEndOfSpeech() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // TODO Auto-generated method stub

            }
        };
        recognizer.setRecognitionListener(listener);
        recognizer.startListening(intent);
    }
}
