package dean.com.template.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dean.com.template.application.TemplateApplication;
import dean.com.template.manager.StringManager;
import dean.com.template.manager.StringManagerImpl;

@Module
public final class ManagerModule {

    @Provides
    @Singleton
    StringManager provideStringManager(final TemplateApplication application) {
        return new StringManagerImpl(application.getResources());
    }
}
