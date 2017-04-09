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

public interface NetworkService {

    Single<List<LoginResponse>> login(UserInformation userInformation);

    Single<List<PlansResponse>> getUserPlans(long id);

    Single<UploadImageResponse> uploadImage(final ImageRequest imageRequest);

    Single<List<Aktivnost>> getUserActivities(long id);

    Single<List<Transport>> getTransport(long id);

    Single<List<Hotel>> getHotel(long id);

    Single<FoodResponse> orderFood(FoodRequest foodRequest);
}
