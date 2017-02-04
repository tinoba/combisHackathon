package dean.com.template.domain.usecase;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import dean.com.template.data.service.NetworkService;
import dean.com.template.data.storage.InventoryPreferences;
import io.reactivex.Single;

public class MovieUseCaseImpl implements MovieUseCase {

    private final NetworkService networkService;

    private final InventoryPreferences preferences;


    public MovieUseCaseImpl(NetworkService networkService, InventoryPreferences preferences) {
        this.networkService = networkService;
        this.preferences = preferences;
    }

    @Override
    public Single<List<MovieApiResponse>> getMovieInfo() {
        return Single
                .defer(() -> networkService.movieInfo());
    }

}
