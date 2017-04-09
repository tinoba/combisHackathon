package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public final class PlansResponse {

    @SerializedName("id")
    public final long id;

    @SerializedName("plan_name")
    public final String name;

    @SerializedName("transport_id")
    public final long transportId;

    @SerializedName("hotel_name")
    public final String hotelName;

    @SerializedName("destination")
    public final String destination;

    public PlansResponse(final long id, final String name, final long transportId, final String hotelName, final String destination) {
        this.id = id;
        this.name = name;
        this.transportId = transportId;
        this.hotelName = hotelName;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTransportId() {
        return transportId;
    }
}
