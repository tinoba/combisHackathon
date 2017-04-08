package combis.hackathon.ui.home;

import java.util.List;

import javax.inject.Named;

import combis.hackathon.R;
import combis.hackathon.data.api.converter.MovieAPIConverter;
import combis.hackathon.domain.model.MovieInfo;
import combis.hackathon.domain.usecase.MovieUseCase;
import combis.hackathon.manager.StringManager;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

public final class HomePresenterImpl extends BasePresenter implements HomePresenter {

    private HomeView view;

    private final MovieUseCase movieUseCase;

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final MovieAPIConverter movieAPIConverter;

    private final StringManager stringManager;

    public HomePresenterImpl(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                             @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final MovieUseCase movieUseCase,
                             final MovieAPIConverter movieAPIConverter, final StringManager stringManager) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.movieUseCase = movieUseCase;
        this.movieAPIConverter = movieAPIConverter;
        this.stringManager = stringManager;
    }

    @Override
    public void setView(final HomeView view) {
        this.view = view;
    }

    @Override
    public void getMovieInfo() {
        if (view != null) {
           /* addDisposable(movieUseCase.getMovieInfo()
                                      .map(movieAPIConverter::convertToMovieInfo)
                                      .subscribeOn(subscribeScheduler)
                                      .observeOn(observeScheduler)
                                      .subscribe(this::onGetMovieInfoSuccess, this::onGetMovieInfoFailure));*/
        }
    }

    private void onGetMovieInfoFailure(final Throwable throwable) {
        Timber.e(stringManager.getString(R.string.fetch_movie_info_error), throwable);
    }

    private void onGetMovieInfoSuccess(final List<MovieInfo> movieInfo) {
        if (view != null) {
            view.showData(movieInfo);
        }
    }
}
