package combis.hackathon.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import combis.hackathon.ui.photo.TakeOrPickAPhotoActivity;
import combis.hackathon.ui.voice.VoiceActivity;
import timber.log.Timber;

public class HomeActivity extends BaseActivity implements HomeView, RecyclerViewAdapterPlans.Listener {

    RecyclerViewAdapterPlans recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    List<PlansResponse> planInfoList = new ArrayList<>();

    @Inject
    HomePresenter presenter;

    @BindView(R.id.activity_home_toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.activity_home_list_of_plans)
    RecyclerView recyclerViewPlans;

    public static Intent createIntent(final Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.plan_summaries);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);

        recyclerViewAdapter = new RecyclerViewAdapterPlans();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerViewPlans.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter.setListener(this);
        recyclerViewPlans.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getUserPlans();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dispose();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.plan_summaries_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.takePickPhoto:
                startActivity(new Intent(HomeActivity.this, TakeOrPickAPhotoActivity.class));
                break;
            case R.id.voiceHelp:
                startActivity(new Intent(HomeActivity.this, VoiceActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void showData(final List<PlansResponse> plansResponses) {
        for (PlansResponse plansResponse : plansResponses) {
            Timber.e(plansResponse.name);
        }
        planInfoList.clear();
        planInfoList.addAll(plansResponses);
        recyclerViewAdapter.setData(planInfoList);
    }

    @Override
    public void getPlanIdAtPosition(final long id, final long transportId) {
        startActivity(HomeDetailsActivity.createIntent(this, id, transportId));
    }
}
