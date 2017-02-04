package dean.com.template.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dean.com.template.injection.scope.ForActivity;
import dean.com.template.ui.home.HomeRouter;
import dean.com.template.ui.home.HomeRouterImpl;

@Module
public final class RouterModule {

    @ForActivity
    @Provides
    HomeRouter provideHomeRouter(final Activity activity) {
        return new HomeRouterImpl(activity);
    }

}
