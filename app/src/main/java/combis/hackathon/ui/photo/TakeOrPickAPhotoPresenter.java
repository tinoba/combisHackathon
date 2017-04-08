package combis.hackathon.ui.photo;

import android.app.Activity;

import combis.hackathon.data.api.models.request.ImageRequest;

public interface TakeOrPickAPhotoPresenter {

    void setView(TakeOrPickAPhotoView view);

    void getImagesPath(Activity activity);

    void dispose();

    void uploadImage(ImageRequest imageRequest);
}
