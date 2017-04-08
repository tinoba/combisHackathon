package combis.hackathon.injection.component;

import javax.inject.Named;
import javax.inject.Singleton;

import combis.hackathon.domain.usecase.LocalImagesUseCase;
import dagger.Component;
import combis.hackathon.application.TemplateApplication;
import combis.hackathon.data.api.converter.MovieAPIConverter;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.device.ApplicationInformation;
import combis.hackathon.device.DeviceInformation;
import combis.hackathon.domain.usecase.MovieUseCase;
import combis.hackathon.injection.module.ApiModule;
import combis.hackathon.injection.module.ApplicationModule;
import combis.hackathon.injection.module.DataModule;
import combis.hackathon.injection.module.DeviceModule;
import combis.hackathon.injection.module.ManagerModule;
import combis.hackathon.injection.module.ThreadingModule;
import combis.hackathon.injection.module.UseCaseModule;
import combis.hackathon.manager.StringManager;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                ManagerModule.class,
                DataModule.class,
                ThreadingModule.class,
                UseCaseModule.class,
                DeviceModule.class
        }
)

public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        private Initializer() {
        }

        public static ApplicationComponent init(final TemplateApplication templateApplication) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(templateApplication))
                    .apiModule(new ApiModule())
                    .build();
        }
    }

    @Named(OBSERVE_SCHEDULER)
    Scheduler getObserveScheduler();

    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler getSubscribeScheduler();

    StringManager getStringManager();

    MovieUseCase getMovieUseCase();

    OkHttpClient getOkHttpClient();

    DeviceInformation getDeviceInformation();

    ApplicationInformation getApplicationInformation();

    MovieAPIConverter getMovieApiConverter();

    TemplatePreferences getTemplatePreferences();

    NetworkService getNetworkService();

    LocalImagesUseCase getImagesUseCase();
}
