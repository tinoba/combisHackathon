package combis.hackathon.ui.home;

import java.util.List;

import combis.hackathon.domain.model.MovieInfo;

public interface HomeView {

    void showData(List<MovieInfo> movieInfo);
}
