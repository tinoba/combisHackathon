package combis.hackathon.data.storage;

public interface PreferenceRepository {

    void setHotelId(long id);

    long getHotelId();

    void setUserId(long userId);

    long getUserId();

}
