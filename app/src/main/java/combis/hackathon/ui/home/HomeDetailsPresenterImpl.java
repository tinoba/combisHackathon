package combis.hackathon.ui.home;

import java.util.List;

import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.Hotel;
import combis.hackathon.data.api.models.response.Transport;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

public class HomeDetailsPresenterImpl extends BasePresenter implements HomeDetailsPresenter {

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final NetworkService networkService;

    private final TemplatePreferences templatePreferences;

    public HomeDetailsPresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final NetworkService networkService,
                                    final TemplatePreferences templatePreferences) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.networkService = networkService;
        this.templatePreferences = templatePreferences;
    }

    private HomeDetailsView view;

    @Override
    public void setView(final HomeDetailsView view) {
        this.view = view;
    }

    @Override
    public void getActivities(final long id) {
        if (view != null) {
            addDisposable(networkService.getUserActivities(id)
                                        .subscribeOn(subscribeScheduler)
                                        .observeOn(observeScheduler)
                                        .subscribe(this::onGetPlansSuccess, this::onGetPlansFailure));
        }
    }

    private void onGetPlansFailure(final Throwable throwable) {
        Timber.e(throwable);
    }

    private void onGetPlansSuccess(final List<Aktivnost> plansResponses) {
        if (view != null) {
            view.showData(plansResponses);
        }
    }

    @Override
    public void getTransport(final long transportId) {
        if (view != null) {
            addDisposable(networkService.getTransport(transportId)
                                        .subscribeOn(subscribeScheduler)
                                        .observeOn(observeScheduler)
                                        .subscribe(this::onGetTransportSuccess, this::onGetTransportFailure));
        }
    }

    private void onGetTransportFailure(final Throwable throwable) {
        Timber.e(throwable);
    }

    private void onGetTransportSuccess(final List<Transport> transports) {
        if (view != null) {
            view.showTransports(transports.get(0));
        }
    }

    @Override
    public void getHotel() {
        if (view != null) {
            addDisposable(networkService.getHotel(templatePreferences.getHotelId())
                                        .subscribeOn(subscribeScheduler)
                                        .observeOn(observeScheduler)
                                        .subscribe(this::onGetHotelSuccess, this::onGetHotelFailure));
        }
    }

    private void onGetHotelFailure(final Throwable throwable) {
        Timber.e(throwable);
    }

    private void onGetHotelSuccess(final List<Hotel> hotels) {
        if (view != null) {
            view.showTransports(hotels.get(0));
        }
    }
}
