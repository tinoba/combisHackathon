package combis.hackathon.injection.module;

import javax.inject.Singleton;

import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.domain.usecase.LocalImagesUseCase;
import combis.hackathon.domain.usecase.LocalImagesUseCaseImpl;
import combis.hackathon.domain.usecase.MovieUseCase;
import combis.hackathon.domain.usecase.MovieUseCaseImpl;
import dagger.Module;
import dagger.Provides;

@Module
public final class UseCaseModule {


    @Provides
    @Singleton
    MovieUseCase providePersonUseCase(final TemplatePreferences preferences, final NetworkService networkService) {
        return new MovieUseCaseImpl(networkService, preferences);
    }

    @Provides
    @Singleton
    LocalImagesUseCase provideLocalImagesUseCase(final TemplatePreferences preferences, final NetworkService networkService) {
        return new LocalImagesUseCaseImpl();
    }

}
