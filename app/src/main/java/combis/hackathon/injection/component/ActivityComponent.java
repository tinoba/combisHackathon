package combis.hackathon.injection.component;


import combis.hackathon.ui.login.LoginPresenter;
import combis.hackathon.ui.photo.TakeOrPickAPhotoPresenter;
import dagger.Component;
import combis.hackathon.injection.module.ActivityModule;
import combis.hackathon.injection.module.PresenterModule;
import combis.hackathon.injection.module.RouterModule;
import combis.hackathon.injection.scope.ForActivity;
import combis.hackathon.ui.base.activities.BaseActivity;
import combis.hackathon.ui.home.HomePresenter;
import combis.hackathon.ui.home.HomeRouter;

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

    LoginPresenter getLoginPresenter();
    TakeOrPickAPhotoPresenter getTakeOrPickAPhotoPresenter();


}

