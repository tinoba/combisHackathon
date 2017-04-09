package combis.hackathon.ui.home;

import java.util.List;

import combis.hackathon.data.api.models.response.Aktivnost;
import combis.hackathon.data.api.models.response.Transport;

public interface HomeDetailsView {

    void showData(List<Aktivnost> aktivnostList);

    void showTransports(Transport transport);
}
