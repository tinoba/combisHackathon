package combis.hackathon.ui.home;

public interface HomeDetailsPresenter {

    void setView(HomeDetailsView view);

    void getActivities(long id);

    void getTransport(long transportId);

    void getHotel();
}
