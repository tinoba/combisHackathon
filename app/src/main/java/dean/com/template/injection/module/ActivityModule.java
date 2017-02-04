package dean.com.template.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dean.com.template.injection.scope.ForActivity;
import dean.com.template.ui.base.activities.BaseActivity;

@Module
public final class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @ForActivity
    @Provides
    public Activity provideActivity() {
        return baseActivity;
    }
}
