package dean.com.template.data.service;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import io.reactivex.Single;

public interface NetworkService {

    Single<List<MovieApiResponse>> movieInfo();
}
