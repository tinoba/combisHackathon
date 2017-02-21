package dean.com.template.data.service;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final TemplateAPI templateAPI;

    public NetworkServiceImpl(final TemplateAPI templateAPI) {
        this.templateAPI = templateAPI;
    }


    @Override
    public Single<List<MovieApiResponse>> movieInfo() {
        return Single.defer(() -> templateAPI.movieInfo());
    }


}
