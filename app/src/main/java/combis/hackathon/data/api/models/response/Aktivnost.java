package combis.hackathon.data.api.models.response;

import com.google.gson.annotations.SerializedName;

public final class Aktivnost {

    @SerializedName("id")
    public final long id;

    @SerializedName("activity_name")
    public final String name;

    @SerializedName("activity_description")
    public final String description;

    @SerializedName("season")
    public final String season;

    @SerializedName("duration")
    public final int duration;

    public Aktivnost(final long id, final String name, final String description, final String season, final int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.season = season;
        this.duration = duration;
    }
}
