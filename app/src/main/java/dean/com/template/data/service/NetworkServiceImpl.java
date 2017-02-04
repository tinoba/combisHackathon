package dean.com.template.data.service;


import java.util.List;

import dean.com.template.data.api.models.response.MovieApiResponse;
import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final InventoryAPI inventoryAPI;

    public NetworkServiceImpl(final InventoryAPI inventoryAPI) {
        this.inventoryAPI = inventoryAPI;
    }


    @Override
    public Single<List<MovieApiResponse>> movieInfo() {
        return Single.defer(() -> inventoryAPI.movieInfo());
    }


}
