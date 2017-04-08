package combis.hackathon.data.service;

import java.util.List;

import combis.hackathon.data.api.models.response.MovieApiResponse;
import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final TemplateAPI templateAPI;

    public NetworkServiceImpl(final TemplateAPI templateAPI) {
        this.templateAPI = templateAPI;
    }
}
