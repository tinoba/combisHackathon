package combis.hackathon.data.api.models.request;

import com.google.gson.annotations.SerializedName;

public final class ImageRequest {

    @SerializedName("hid")
    public final String hid;

    @SerializedName("code")
    public final String code;

    public ImageRequest(final String hid, final String code) {
        this.hid = hid;
        this.code = code;
    }
}
