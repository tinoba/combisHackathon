package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public final class LoginResponse {

    @SerializedName("id")
    public final long id;
    @SerializedName("ime")
    public final String ime;
    @SerializedName("email")
    public final String email;
    @SerializedName("current_hotel")
    public final int currentHotel;

    public LoginResponse(final long id, final String ime, final String email, final int currentHotel) {
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.currentHotel = currentHotel;
    }
}
