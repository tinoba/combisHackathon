package combis.hackathon.injection.module;

import javax.inject.Named;

import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.api.converter.MovieAPIConverter;
import combis.hackathon.domain.usecase.LocalImagesUseCase;
import combis.hackathon.domain.usecase.MovieUseCase;
import combis.hackathon.injection.scope.ForActivity;
import combis.hackathon.manager.StringManager;
import combis.hackathon.ui.home.HomePresenter;
import combis.hackathon.ui.home.HomePresenterImpl;
import combis.hackathon.ui.login.LoginPresenter;
import combis.hackathon.ui.login.LoginPresenterImpl;
import dagger.Module;
import dagger.Provides;
import combis.hackathon.ui.photo.TakeOrPickAPhotoPresenter;
import combis.hackathon.ui.photo.TakeOrPickAPhotoPresenterImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Module
public final class PresenterModule {

    @ForActivity
    @Provides
    HomePresenter provideHomePresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, final NetworkService networkService,
                                       @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, StringManager stringManager) {
        return new HomePresenterImpl(subscribeScheduler, observeScheduler, networkService, stringManager);
    }

    @ForActivity
    @Provides
    LoginPresenter provideLoginPresenter(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                                         @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final NetworkService networkService) {
        return new LoginPresenterImpl(networkService, subscribeScheduler, observeScheduler);
    }
    @ForActivity
    @Provides
    TakeOrPickAPhotoPresenter provideTakeOrPickAPhotoPresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler,
                                                               @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, LocalImagesUseCase localImagesUseCase, MovieAPIConverter movieAPIConverter, StringManager stringManager) {
        return new TakeOrPickAPhotoPresenterImpl(subscribeScheduler, observeScheduler,stringManager, localImagesUseCase);
    }

}
