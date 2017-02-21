package dean.com.template.injection.component;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import dean.com.template.application.TemplateApplication;
import dean.com.template.data.api.converter.MovieAPIConverter;
import dean.com.template.data.service.NetworkService;
import dean.com.template.data.storage.TemplatePreferences;
import dean.com.template.device.ApplicationInformation;
import dean.com.template.device.DeviceInformation;
import dean.com.template.domain.usecase.MovieUseCase;
import dean.com.template.injection.module.ApiModule;
import dean.com.template.injection.module.ApplicationModule;
import dean.com.template.injection.module.DataModule;
import dean.com.template.injection.module.DeviceModule;
import dean.com.template.injection.module.ManagerModule;
import dean.com.template.injection.module.ThreadingModule;
import dean.com.template.injection.module.UseCaseModule;
import dean.com.template.manager.StringManager;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

import static dean.com.template.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static dean.com.template.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;


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
}
