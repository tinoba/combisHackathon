package combis.hackathon.domain.usecase;


import java.util.List;

import combis.hackathon.data.api.models.response.MovieApiResponse;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import io.reactivex.Single;

public class MovieUseCaseImpl implements MovieUseCase {

    private final NetworkService networkService;

    private final TemplatePreferences preferences;


    public MovieUseCaseImpl(NetworkService networkService, TemplatePreferences preferences) {
        this.networkService = networkService;
        this.preferences = preferences;
    }

 /*   @Override
    public Single<List<MovieApiResponse>> getMovieInfo() {
        return Single
                .defer(() -> networkService.movieInfo());
    }*/

}
