package dean.com.template.injection;


import dean.com.template.application.TemplateApplication;
import dean.com.template.injection.component.ActivityComponent;
import dean.com.template.injection.component.ApplicationComponent;
import dean.com.template.ui.base.activities.BaseActivity;

public final class ComponentFactory {

    private ComponentFactory() { }

    public static ApplicationComponent createApplicationComponent(final TemplateApplication application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final TemplateApplication application, final BaseActivity activity) {
        return ActivityComponent.Initializer.init(application.getApplicationComponent(), activity);
    }
}
