package combis.hackathon.ui.photo;

import android.app.Activity;

public interface TakeOrPickAPhotoPresenter {

    void setView(TakeOrPickAPhotoView view);

    void getImagesPath(Activity activity);

    void dispose();
}
