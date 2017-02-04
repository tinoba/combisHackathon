package dean.com.template.data.api.converter;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import dean.com.template.domain.model.MovieInfo;

public interface MovieAPIConverter {

    List<MovieInfo> convertToMovieInfo(List<MovieApiResponse> movieApiResponse);

}
