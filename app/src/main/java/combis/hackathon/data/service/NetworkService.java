package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.ImageRequest;
import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.data.api.models.response.UploadImageResponse;
import io.reactivex.Single;

public interface NetworkService {

    Single<List<LoginResponse>> login(UserInformation userInformation);

    Single<List<PlansResponse>> getUserPlans();

    Single<UploadImageResponse> uploadImage(final ImageRequest imageRequest);
}
