package combis.hackathon.ui.login;

import combis.hackathon.data.api.models.request.UserInformation;

public interface LoginPresenter {

    void login(UserInformation userInformation);

    void setView(LoginView loginView);

    long checkIfLoggedIn();
}
