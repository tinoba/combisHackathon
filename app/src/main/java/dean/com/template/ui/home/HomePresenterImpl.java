package dean.com.template.ui.home;

import java.util.List;

import javax.inject.Named;

import dean.com.template.R;
import dean.com.template.data.api.converter.MovieAPIConverter;
import dean.com.template.domain.model.MovieInfo;
import dean.com.template.domain.usecase.MovieUseCase;
import dean.com.template.manager.StringManager;
import dean.com.template.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

import static dean.com.template.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static dean.com.template.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

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
            addDisposable(movieUseCase.getMovieInfo()
                                      .map(movieAPIConverter::convertToMovieInfo)
                                      .subscribeOn(subscribeScheduler)
                                      .observeOn(observeScheduler)
                                      .subscribe(this::onGetPersonInfoSuccess, this::onGetPersonInfoFailure));
        }
    }

    private void onGetPersonInfoFailure(final Throwable throwable) {
        Timber.e(stringManager.getString(R.string.fetch_movie_info_error), throwable);
    }

    private void onGetPersonInfoSuccess(final List<MovieInfo> movieInfo) {
        if (view != null) {
            view.showData(movieInfo);
        }
    }
}
