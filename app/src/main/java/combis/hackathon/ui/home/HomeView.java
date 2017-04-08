package combis.hackathon.ui.home;

import java.util.List;

import combis.hackathon.data.api.models.response.PlansResponse;

public interface HomeView {

    void showData(List<PlansResponse> plansResponses);
}
