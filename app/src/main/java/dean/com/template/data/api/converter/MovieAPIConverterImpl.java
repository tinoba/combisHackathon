package dean.com.template.data.api.converter;


import java.util.ArrayList;
import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import dean.com.template.domain.model.MovieInfo;

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
