package combis.hackathon.data.api.models.request;

public class FoodRequest {

    public final long users_id;
    public final long hotel_id;
    public final String naziv;

    public FoodRequest(final long user_id, final long hotel_id, final String naziv) {
        this.users_id = user_id;
        this.hotel_id = hotel_id;
        this.naziv = naziv;
    }
}
