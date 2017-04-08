package combis.hackathon.ui.login;

import java.util.List;

import javax.inject.Named;

import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.data.api.models.response.LoginResponse;
import combis.hackathon.data.service.NetworkService;
import combis.hackathon.ui.base.presenter.BasePresenter;
import io.reactivex.Scheduler;
import timber.log.Timber;

import static combis.hackathon.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static combis.hackathon.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

public class LoginPresenterImpl extends BasePresenter implements LoginPresenter {

    private final NetworkService networkService;
    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;

    private LoginView loginView;

    public LoginPresenterImpl(final NetworkService networkService, @Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                              @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler) {
        this.networkService = networkService;
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
    }

    @Override
    public void setView(final LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(final UserInformation userInformation) {
        if (loginView != null) {
            addDisposable(networkService.login(userInformation)
                                        .observeOn(observeScheduler)
                                        .subscribeOn(subscribeScheduler)
                                        .subscribe(this::onLoginSuccess, this::onLoginFailure));
        }
    }

    private void onLoginFailure(final Throwable throwable) {
        Timber.e(throwable);
    }

    private void onLoginSuccess(final List<LoginResponse> loginResponse) {
        if (loginView != null) {
            loginView.goToHomeScreen();
        }
    }
}