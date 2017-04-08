package combis.hackathon.data.api.converter;


import java.util.ArrayList;
import java.util.List;

import combis.hackathon.data.api.models.response.MovieApiResponse;
import combis.hackathon.domain.model.MovieInfo;

public class MovieAPIConverterImpl implements MovieAPIConverter {

    @Override
    public List<MovieInfo> convertToMovieInfo(final List<MovieApiResponse> movieApiResponse) {

        List<MovieInfo> movieInfoList = new ArrayList<>(movieApiResponse.size());

        for (MovieApiResponse apiResponse : movieApiResponse) {
            if (apiResponse == null) {
                movieInfoList.add(MovieInfo.EMPTY);
            } else {
                movieInfoList.add(new MovieInfo(apiResponse.getTitle(), apiResponse.getImage()));
            }
        }
        return  movieInfoList;
    }
}
