package combis.hackathon.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.Hotel;
import combis.hackathon.data.api.models.response.Transport;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;

public class HomeDetailsActivity extends BaseActivity implements HomeDetailsView {

    @Inject
    HomeDetailsPresenter presenter;

    @BindView(R.id.activity_home_toolbar)
    Toolbar toolbar;

    @BindView(R.id.plan_item_hotel_name)
    TextView hotelName;
    @BindView(R.id.plan_item_hotel_address)
    TextView hotelAddress;
    @BindView(R.id.rating)
    RatingBar ratingBar;

    @BindView(R.id.plan_item_bus_type)
    TextView transportType;
    @BindView(R.id.plan_item_clock)
    TextView clock;

    @BindView(R.id.plan_item_activity)
    TextView activityPlan;
    @BindView(R.id.plan_item_description)
    TextView descriptionPlan;
    @BindView(R.id.plan_item_clock_activity)
    TextView clockActivity;

    public static Intent createIntent(final Context context, final long id, final long transportId) {
        return new Intent(context, HomeDetailsActivity.class).putExtra("id", id).putExtra("transportId", transportId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.plan_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.setView(this);
        final long id = getIntent().getLongExtra("id", 0);
        final long transportId = getIntent().getLongExtra("transportId", 0);
        if (id != 0 && transportId != 0) {
            presenter.getActivities(id);
            presenter.getTransport(transportId);
            presenter.getHotel();
        }
    }

    @Override
    public void showData(final List<Aktivnost> aktivnostList) {
        if (!aktivnostList.isEmpty()) {
            descriptionPlan.setText(aktivnostList.get(0).description);
            clockActivity.setText(String.valueOf(aktivnostList.get(0).duration));
            activityPlan.setText(aktivnostList.get(0).name);
        }
    }

    @Override
    public void showTransports(final Transport transport) {
        clock.setText(String.valueOf(transport.duration));
        transportType.setText(transport.transport_type);
    }

    @Override
    public void showTransports(final Hotel hotel) {
        hotelName.setText(hotel.hotel_name);
        hotelAddress.setText(hotel.hotel_address);
        ratingBar.setRating(hotel.stars);
    }
}
