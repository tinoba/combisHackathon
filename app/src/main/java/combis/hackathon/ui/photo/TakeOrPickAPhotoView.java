package combis.hackathon.ui.photo;

import java.util.List;

public interface TakeOrPickAPhotoView {

    void showImagesPath(List<String> imageList);

    void goToPhotoDetails(String url);
}
