package dean.com.template.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dean.com.template.data.service.NetworkService;
import dean.com.template.data.storage.TemplatePreferences;
import dean.com.template.domain.usecase.MovieUseCase;
import dean.com.template.domain.usecase.MovieUseCaseImpl;

@Module
public final class UseCaseModule {


    @Provides
    @Singleton
    MovieUseCase providePersonUseCase(final TemplatePreferences preferences, final NetworkService networkService) {
        return new MovieUseCaseImpl(networkService, preferences);
    }

}
