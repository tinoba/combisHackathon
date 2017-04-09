package combis.hackathon.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import combis.hackathon.R;
import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.Hotel;
import combis.hackathon.data.api.models.response.Transport;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class HomeDetailsActivity extends BaseActivity implements HomeDetailsView {

    @Inject
    HomeDetailsPresenter presenter;

    public static Intent createIntent(final Context context, final long id, final long transportId) {
        return new Intent(context, HomeDetailsActivity.class).putExtra("id", id).putExtra("transportId", transportId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
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
        Timber.e(aktivnostList.get(0).name);
    }

    @Override
    public void showTransports(final Transport transport) {
        Timber.e(transport.transport_type);
    }

    @Override
    public void showTransports(final Hotel hotel) {
        Timber.e(hotel.hotel_name);
    }
}
