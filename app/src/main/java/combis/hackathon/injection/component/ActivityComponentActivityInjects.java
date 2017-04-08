package combis.hackathon.injection.component;

import combis.hackathon.ui.home.HomeActivity;
import combis.hackathon.ui.login.LoginActivity;
import combis.hackathon.ui.photo.TakeOrPickAPhotoActivity;
import combis.hackathon.ui.voice.VoiceActivity;

public interface ActivityComponentActivityInjects {

    void inject(HomeActivity homeActivity);

    void inject(LoginActivity loginActivity);

    void inject(TakeOrPickAPhotoActivity takeOrPickAPhotoActivity);

    void inject(VoiceActivity voiceActivity);
}
