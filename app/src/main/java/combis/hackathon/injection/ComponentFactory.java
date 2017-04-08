package combis.hackathon.injection;


import combis.hackathon.application.TemplateApplication;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.injection.component.ApplicationComponent;
import combis.hackathon.ui.base.activities.BaseActivity;

public final class ComponentFactory {

    private ComponentFactory() { }

    public static ApplicationComponent createApplicationComponent(final TemplateApplication application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final TemplateApplication application, final BaseActivity activity) {
        return ActivityComponent.Initializer.init(application.getApplicationComponent(), activity);
    }
}
