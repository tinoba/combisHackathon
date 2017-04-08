package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public final class PlansResponse {

    @SerializedName("id")
    public final long id;

    @SerializedName("plan_name")
    public final String name;

    @SerializedName("transport_id")
    public final long transportId;

    public PlansResponse(final long id, final String name, final long transportId) {
        this.id = id;
        this.name = name;
        this.transportId = transportId;
    }
}
