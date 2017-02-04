package dean.com.template.ui.home;

import java.util.List;

import javax.inject.Named;

import dean.com.template.data.api.converter.MovieAPIConverter;
import dean.com.template.domain.model.MovieInfo;
import dean.com.template.domain.usecase.MovieUseCase;
import dean.com.template.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;

import static dean.com.template.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static dean.com.template.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


public final class HomePresenterImpl extends BasePresenter implements HomePresenter {

    private HomeView view;

    private final MovieUseCase movieUseCase;

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final MovieAPIConverter movieAPIConverter;

    public HomePresenterImpl(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
            @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final MovieUseCase movieUseCase,
            final MovieAPIConverter movieAPIConverter) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.movieUseCase = movieUseCase;
        this.movieAPIConverter = movieAPIConverter;
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
        if (view != null) {
            view.showError(throwable);
        }
    }


    private void onGetPersonInfoSuccess(final List<MovieInfo> movieInfo) {
        if (view != null) {
            view.showData(movieInfo);
        }
    }
}
