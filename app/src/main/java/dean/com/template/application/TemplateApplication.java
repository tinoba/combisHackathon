package dean.com.template.application;

import android.app.Application;

import dean.com.template.injection.ComponentFactory;
import dean.com.template.injection.component.ApplicationComponent;
import timber.log.Timber;

public final class TemplateApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
        Timber.plant(new Timber.DebugTree());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
