package combis.hackathon.ui.voice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import combis.hackathon.R;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class VoiceActivity extends BaseActivity {

    @BindView(R.id.button_speak)
    Button speakButton;

    SpeechRecognizer recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        ButterKnife.bind(this);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @OnClick(R.id.button_speak)
    public void speak() {
        speakButton.setVisibility(View.GONE);
        startSpeaking();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSpeaking();
    }

    private void startSpeaking() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                        "combis.hackathon");

        recognizer = SpeechRecognizer
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

                    boolean found = false;
                    int count = 0;
                    for (String match : voiceResults) {
                        count++;
                        //Timber.e(match);
                        switch (match) {

                            case "call Hotel":
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:0953910882"));

                                if (ActivityCompat.checkSelfPermission(VoiceActivity.this,
                                                                       Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                                found = true;
                                break;
                            case "activities":
                                startActivity(new Intent(VoiceActivity.this, ActivitiesActivity.class));
                                found = true;
                                finish();
                                break;
                            case "discounts":
                                Toast.makeText(VoiceActivity.this, "Discounts", Toast.LENGTH_SHORT).show();
                                found = true;
                                break;
                            case "order food":
                                startActivity(FoodActivity.createIntent(VoiceActivity.this));
                                found = true;
                                finish();
                                break;
                            default:
                                if (count == voiceResults.size()) {
                                    Toast.makeText(VoiceActivity.this, "I don't understand this command", Toast.LENGTH_SHORT).show();
                                    speakButton.setVisibility(View.VISIBLE);
                                    found = true;
                                    break;
                                }
                        }
                        if (found) {
                            break;
                        }
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
                speakButton.setVisibility(View.VISIBLE);
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
