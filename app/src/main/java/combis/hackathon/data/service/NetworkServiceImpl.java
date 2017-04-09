package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.FoodRequest;
import combis.hackathon.data.api.models.request.ImageRequest;
import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.FoodResponse;
import combis.hackathon.data.api.models.response.Hotel;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.data.api.models.response.Transport;
import combis.hackathon.data.api.models.response.UploadImageResponse;
import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final TemplateAPI templateAPI;

    public NetworkServiceImpl(final TemplateAPI templateAPI) {
        this.templateAPI = templateAPI;
    }

    @Override
    public Single<List<LoginResponse>> login(final UserInformation userInformation) {
        return Single.defer(() -> templateAPI.login(userInformation));
    }

    @Override
    public Single<List<PlansResponse>> getUserPlans(final long id) {
        return Single.defer(() -> templateAPI.getUserPlans(id));
    }

    @Override
    public Single<UploadImageResponse> uploadImage(final ImageRequest imageRequest) {
        return Single.defer(() -> templateAPI.uploadImage(imageRequest));
    }

    @Override
    public Single<List<Aktivnost>> getUserActivities(final long id) {
        return Single.defer(() -> templateAPI.getUserActivities(id));
    }

    @Override
    public Single<List<Transport>> getTransport(final long id) {
        return Single.defer(() -> templateAPI.getTransport(id));
    }

    @Override
    public Single<List<Hotel>> getHotel(final long id) {
        return Single.defer(() -> templateAPI.getHotel(id));
    }

    @Override
    public Single<FoodResponse> orderFood(final FoodRequest foodRequest) {
        return Single.defer(() -> templateAPI.sendFood(foodRequest));
    }
}
