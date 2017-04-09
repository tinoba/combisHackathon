package combis.hackathon.ui.voice;

import combis.hackathon.data.api.models.request.FoodRequest;
import combis.hackathon.data.api.models.response.FoodResponse;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

public class FoodPresenterImpl extends BasePresenter implements FoodPresenter {

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final NetworkService networkService;

    private final TemplatePreferences templatePreferences;

    public FoodPresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final NetworkService networkService,
                             final TemplatePreferences templatePreferences) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.networkService = networkService;
        this.templatePreferences = templatePreferences;
    }

    @Override
    public void sendFood(final String name) {

        addDisposable(networkService.orderFood(new FoodRequest(templatePreferences.getUserId(), templatePreferences.getHotelId(), name))
                                    .subscribeOn(subscribeScheduler)
                                    .observeOn(observeScheduler)
                                    .subscribe(this::onOrderFoodSuccess, this::onOrderFoodFailure));
    }

    private void onOrderFoodFailure(final Throwable throwable) {
        Timber.e(throwable);
    }

    private void onOrderFoodSuccess(final FoodResponse foodResponse) {
        Timber.e("Success ordering food");
    }
}
