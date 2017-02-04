package dean.com.template.domain.usecase;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import io.reactivex.Single;

public interface MovieUseCase {

    Single<List<MovieApiResponse>> getMovieInfo();

}
