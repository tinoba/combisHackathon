package dean.com.template.injection.component;


import dagger.Component;
import dean.com.template.injection.module.ActivityModule;
import dean.com.template.injection.module.PresenterModule;
import dean.com.template.injection.module.RouterModule;
import dean.com.template.injection.scope.ForActivity;
import dean.com.template.ui.base.activities.BaseActivity;
import dean.com.template.ui.home.HomePresenter;
import dean.com.template.ui.home.HomeRouter;

@ForActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class,
                PresenterModule.class,
                RouterModule.class
        }
)
public interface ActivityComponent extends ActivityComponentActivityInjects, ActivityComponentFragmentsInjects {

    final class Initializer {

        private Initializer() {
        }

        public static ActivityComponent init(final ApplicationComponent applicationComponent, final BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

    HomeRouter getHomeRouter();

    HomePresenter getHomePresenter();


}

