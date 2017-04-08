package combis.hackathon.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import combis.hackathon.injection.scope.ForActivity;
import combis.hackathon.ui.home.HomeRouter;
import combis.hackathon.ui.home.HomeRouterImpl;

@Module
public final class RouterModule {

    @ForActivity
    @Provides
    HomeRouter provideHomeRouter(final Activity activity) {
        return new HomeRouterImpl(activity);
    }

}
