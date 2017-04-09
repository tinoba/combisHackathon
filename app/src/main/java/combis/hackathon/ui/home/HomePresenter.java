package combis.hackathon.ui.home;

public interface HomePresenter {

    void setView(HomeView view);

    void getUserPlans();

    void dispose();

}
