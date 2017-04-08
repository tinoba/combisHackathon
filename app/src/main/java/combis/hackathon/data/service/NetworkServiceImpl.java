package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.api.models.response.PlansResponse;
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
    public Single<List<PlansResponse>> getUserPlans() {
        return Single.defer(templateAPI::getUserPlans);
    }
}
