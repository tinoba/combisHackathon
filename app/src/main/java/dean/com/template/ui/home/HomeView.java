package dean.com.template.ui.home;

import java.util.List;

import dean.com.template.domain.model.MovieInfo;

public interface HomeView {

    void showData(List<MovieInfo> movieInfo);
}
