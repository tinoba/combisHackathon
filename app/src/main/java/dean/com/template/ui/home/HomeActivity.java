package dean.com.template.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dean.com.template.R;
import dean.com.template.domain.model.MovieInfo;
import dean.com.template.injection.component.ActivityComponent;
import dean.com.template.ui.base.activities.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeView {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Inject
    HomePresenter presenter;


    public static Intent createIntent(final Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getMovieInfo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dispose();
    }



    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage().toString());
    }

    @Override
    public void showData(List<MovieInfo> movieInfo) {
        Toast.makeText(this, movieInfo.get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
