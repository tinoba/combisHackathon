package combis.hackathon.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.domain.usecase.MovieUseCase;
import combis.hackathon.domain.usecase.MovieUseCaseImpl;

@Module
public final class UseCaseModule {


    @Provides
    @Singleton
    MovieUseCase providePersonUseCase(final TemplatePreferences preferences, final NetworkService networkService) {
        return new MovieUseCaseImpl(networkService, preferences);
    }

}
