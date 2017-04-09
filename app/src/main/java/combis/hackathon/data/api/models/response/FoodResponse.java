package combis.hackathon.data.api.models.response;

public class FoodResponse {

    public final long id;
    public final long users_id;
    public final long hotel_id;
    public final String naziv;
    public final int cnt;
    public final String hotel_name;

    public FoodResponse(final long id, final long users_id, final long hotel_id, final String naziv, final int cnt, final String hotel_name) {
        this.id = id;
        this.users_id = users_id;
        this.hotel_id = hotel_id;
        this.naziv = naziv;
        this.cnt = cnt;
        this.hotel_name = hotel_name;
    }
}
