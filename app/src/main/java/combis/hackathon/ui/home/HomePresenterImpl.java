package combis.hackathon.ui.home;

import java.util.List;

import javax.inject.Named;

import combis.hackathon.R;
import combis.hackathon.data.api.models.response.PlansResponse;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.data.storage.TemplatePreferences;
import combis.hackathon.manager.StringManager;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

public final class HomePresenterImpl extends BasePresenter implements HomePresenter {

    private HomeView view;

    private final Scheduler subscribeScheduler;

    private final Scheduler observeScheduler;

    private final StringManager stringManager;

    private final NetworkService networkService;

    private final TemplatePreferences templatePreferences;

    public HomePresenterImpl(@Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                             @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, final NetworkService networkService, final StringManager stringManager,
                             final TemplatePreferences templatePreferences) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.networkService = networkService;
        this.stringManager = stringManager;
        this.templatePreferences = templatePreferences;
    }

    @Override
    public void setView(final HomeView view) {
        this.view = view;
    }

    @Override
    public void getUserPlans() {
        if (view != null) {
            addDisposable(networkService.getUserPlans(templatePreferences.getUserId())
                                        .subscribeOn(subscribeScheduler)
                                        .observeOn(observeScheduler)
                                        .subscribe(this::onGetPlansSuccess, this::onGetPlansFailure));
        }
    }

    private void onGetPlansFailure(final Throwable throwable) {
        Timber.e(stringManager.getString(R.string.fetch_movie_info_error), throwable);
    }

    private void onGetPlansSuccess(final List<PlansResponse> plansResponses) {
        if (view != null) {
            view.showData(plansResponses);
        }
    }
}
