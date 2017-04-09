package combis.hackathon.injection.component;

import combis.hackathon.ui.home.HomeActivity;
import combis.hackathon.ui.home.HomeDetailsActivity;
import combis.hackathon.ui.login.LoginActivity;
import combis.hackathon.ui.photo.PhotoDetailsActivity;
import combis.hackathon.ui.photo.TakeOrPickAPhotoActivity;
import combis.hackathon.ui.voice.FoodActivity;
import combis.hackathon.ui.voice.VehicleActivity;
import combis.hackathon.ui.voice.VoiceActivity;

public interface ActivityComponentActivityInjects {

    void inject(HomeActivity homeActivity);

    void inject(LoginActivity loginActivity);

    void inject(TakeOrPickAPhotoActivity takeOrPickAPhotoActivity);

    void inject(VoiceActivity voiceActivity);

    void inject(FoodActivity foodActivity);

    void inject(HomeDetailsActivity homeDetailsActivity);

    void inject(PhotoDetailsActivity homeDetailsActivity);

    void inject(VehicleActivity vehicleActivity);
}
