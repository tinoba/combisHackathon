package dean.com.template.injection.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dean.com.template.data.api.converter.MovieAPIConverter;
import dean.com.template.domain.usecase.MovieUseCase;
import dean.com.template.injection.scope.ForActivity;
import dean.com.template.manager.StringManager;
import dean.com.template.ui.home.HomePresenter;
import dean.com.template.ui.home.HomePresenterImpl;
import io.reactivex.Scheduler;

import static dean.com.template.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static dean.com.template.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


@Module
public final class PresenterModule {

    @ForActivity
    @Provides
    HomePresenter provideHomePresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler,
            @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, MovieUseCase movieUseCase, MovieAPIConverter movieAPIConverter, StringManager stringManager) {
        return new HomePresenterImpl(subscribeScheduler, observeScheduler, movieUseCase, movieAPIConverter, stringManager);
    }

}
