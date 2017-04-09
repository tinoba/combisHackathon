package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {

    @SerializedName("success")
    public final int success;

    @SerializedName("response")
    public final String response;

    @SerializedName("short_text")
    public final String shortText;

    @SerializedName("long_text")
    public final String longText;

    public UploadImageResponse(final int success, final String response, final String shortText, final String longText) {
        this.success = success;
        this.response = response;
        this.shortText = shortText;
        this.longText = longText;
    }
}
