package combis.hackathon.data.api.converter;


import java.util.List;

import combis.hackathon.data.api.models.response.MovieApiResponse;
import combis.hackathon.domain.model.MovieInfo;

public interface MovieAPIConverter {

    List<MovieInfo> convertToMovieInfo(List<MovieApiResponse> movieApiResponse);

}
