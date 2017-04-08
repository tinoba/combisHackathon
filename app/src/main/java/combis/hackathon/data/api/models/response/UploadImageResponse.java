package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {
    @SerializedName("success")
    public final int success;

    public UploadImageResponse(final int success) {
        this.success = success;
    }
}
