package dean.com.template.ui.base.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import dean.com.template.application.TemplateApplication;
import dean.com.template.injection.component.ActivityComponent;
import dean.com.template.ui.base.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TemplateApplication templateApplication = (TemplateApplication) getActivity().getApplication();

        inject(((BaseActivity) getActivity()).getActivityComponent(templateApplication));
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
