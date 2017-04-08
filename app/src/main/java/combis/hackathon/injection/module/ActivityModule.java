package combis.hackathon.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.injection.scope.ForActivity;
import combis.hackathon.ui.base.activities.BaseActivity;

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
