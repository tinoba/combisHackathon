package dean.com.template.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dean.com.template.application.TemplateApplication;

@Module
public final class ApplicationModule {

    private final TemplateApplication templateApplication;

    public ApplicationModule(final TemplateApplication templateApplication) {
        this.templateApplication = templateApplication;
    }

    @Provides
    @Singleton
    TemplateApplication provideApplication() {
        return templateApplication;
    }
}
