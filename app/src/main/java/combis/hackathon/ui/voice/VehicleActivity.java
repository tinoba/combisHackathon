package combis.hackathon.ui.voice;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.Vehicle;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;

public class VehicleActivity extends BaseActivity {

    @BindView(R.id.vehicle_list)
    ListView vehicleListView;
    private VehicleAdapter vehicleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        ButterKnife.bind(this);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        final List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Sun Tourist", 20, "08:00 - 17:00", R.drawable.sunturist));
        vehicleList.add(new Vehicle("Sun Tourist", 20, "08:00 - 17:00", R.drawable.sunturist));
        vehicleList.add(new Vehicle("Sun Tourist", 20, "08:00 - 17:00", R.drawable.sunturist));
        vehicleList.add(new Vehicle("Sun Tourist", 20, "08:00 - 17:00", R.drawable.sunturist));
        vehicleList.add(new Vehicle("Sun Tourist", 20, "08:00 - 17:00", R.drawable.sunturist));

        vehicleAdapter = new VehicleAdapter(this, vehicleList);
        vehicleListView.setAdapter(vehicleAdapter);
    }
}
