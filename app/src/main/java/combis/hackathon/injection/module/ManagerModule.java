package combis.hackathon.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.application.TemplateApplication;
import combis.hackathon.manager.StringManager;
import combis.hackathon.manager.StringManagerImpl;

@Module
public final class ManagerModule {

    @Provides
    @Singleton
    StringManager provideStringManager(final TemplateApplication application) {
        return new StringManagerImpl(application.getResources());
    }
}
