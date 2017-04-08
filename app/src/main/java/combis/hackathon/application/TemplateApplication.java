package combis.hackathon.application;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import combis.hackathon.injection.ComponentFactory;
import combis.hackathon.injection.component.ApplicationComponent;
import timber.log.Timber;

public final class TemplateApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
        Timber.plant(new Timber.DebugTree());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
