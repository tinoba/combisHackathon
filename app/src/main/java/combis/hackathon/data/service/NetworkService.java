package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
import io.reactivex.Single;

public interface NetworkService {
    Single<List<LoginResponse>> login(UserInformation userInformation);

    Single<List<PlansResponse>> getUserPlans();
}
