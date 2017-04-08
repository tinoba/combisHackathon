package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {

    @SerializedName("success")
    public final int success;

    @SerializedName("response")
    public final String response;

    public UploadImageResponse(final int success, final String response) {
        this.success = success;
        this.response = response;
    }
}
