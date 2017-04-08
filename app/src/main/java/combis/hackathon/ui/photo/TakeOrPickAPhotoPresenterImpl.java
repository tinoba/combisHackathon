package combis.hackathon.ui.photo;

import android.app.Activity;

import java.util.List;

import javax.inject.Named;

import combis.hackathon.R;
import combis.hackathon.domain.usecase.LocalImagesUseCase;
import combis.hackathon.manager.StringManager;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

public class TakeOrPickAPhotoPresenterImpl extends BasePresenter implements TakeOrPickAPhotoPresenter {

    private TakeOrPickAPhotoView view;

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final StringManager stringManager;

    private final LocalImagesUseCase localImagesUseCase;

    public TakeOrPickAPhotoPresenterImpl(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                                         @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final StringManager stringManager,
                                         final LocalImagesUseCase localImagesUseCase) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.stringManager = stringManager;
        this.localImagesUseCase = localImagesUseCase;
    }

    @Override
    public void setView(final TakeOrPickAPhotoView view) {
        this.view = view;
    }

    @Override
    public void getImagesPath(Activity activity) {
        if (view != null) {
            addDisposable(localImagesUseCase.getImagesPath(activity)
                                            .subscribeOn(subscribeScheduler)
                                            .observeOn(observeScheduler)
                                            .subscribe(this::onGetMovieInfoSuccess, this::onGetMovieInfoFailure));
        }
    }

    private void onGetMovieInfoFailure(final Throwable throwable) {
        Timber.e(stringManager.getString(R.string.fetch_movie_info_error), throwable);
    }

    private void onGetMovieInfoSuccess(final List<String> imageList) {
        if (view != null) {
            view.showImagesPath(imageList);
        }
    }
}


