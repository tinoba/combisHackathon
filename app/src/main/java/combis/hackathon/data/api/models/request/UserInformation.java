package combis.hackathon.data.api.models.request;

import com.google.gson.annotations.SerializedName;

public final class UserInformation {

    @SerializedName("ime")
    public final String name;
    @SerializedName("email")
    public final String email;

    public UserInformation(final String name, final String email) {
        this.name = name;
        this.email = email;
    }
}
