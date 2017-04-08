package combis.hackathon.ui.voice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.ActivityModel;

public class ActivitiesActivity extends AppCompatActivity implements RecyclerViewAdapterActivities.Listener {

    @BindView(R.id.activity_activities_toolbar)
    Toolbar toolbar;

    RecyclerViewAdapterActivities recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    List<ActivityModel> activityModelList = new ArrayList<>();

    @BindView(R.id.activity_activities_recycler_view)
    RecyclerView recyclerViewPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.activities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewAdapter = new RecyclerViewAdapterActivities();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerViewPlans.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter.setListener(this);
        recyclerViewPlans.setAdapter(recyclerViewAdapter);

        // TODO test data
        activityModelList.clear();
        activityModelList.add(new ActivityModel(R.drawable.sports_android, "SPORTS", "INFO ONE", "INFO TWO"));
        activityModelList.add(new ActivityModel(R.drawable.theatre_android, "THEATRE", "INFO ONE", "INFO TWO"));
        activityModelList.add(new ActivityModel(R.drawable.music_android, "MUSIC", "INFO ONE", "INFO TWO"));
        activityModelList.add(new ActivityModel(R.drawable.relax_android, "RELAX", "INFO ONE", "INFO TWO"));
        activityModelList.add(new ActivityModel(R.drawable.sports_android, "SPORTS", "INFO ONE", "INFO TWO"));

        recyclerViewAdapter.setData(activityModelList);
    }

    @Override
    public void getActivityAtPosition(final int position) {
        Toast.makeText(this, "Open details? " + position, Toast.LENGTH_SHORT).show();

    }
}
