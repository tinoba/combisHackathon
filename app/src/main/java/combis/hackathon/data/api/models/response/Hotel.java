package combis.hackathon.data.api.models.response;

public final class Hotel {

    public final long id;
    public final String hotel_name;
    public final String hotel_address;
    public final int stars;
    public final long loc_id;

    public Hotel(final long id, final String hotel_name, final String hotel_address, final int stars, final long loc_id) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.stars = stars;
        this.loc_id = loc_id;
    }
}
