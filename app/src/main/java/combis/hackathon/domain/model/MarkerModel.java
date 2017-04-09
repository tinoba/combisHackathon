package combis.hackathon.domain.model;

import com.google.android.gms.maps.model.LatLng;

public class MarkerModel {

    private LatLng latLng;
    private String landMarkTitle;
    private String smallDescription;
    private int landMarkId;
    private String bigDescription;

    public MarkerModel(final LatLng latLng, final String landMarkTitle, final String smallDescription, final int landMarkId, final String bigDescription) {
        this.latLng = latLng;
        this.landMarkTitle = landMarkTitle;
        this.smallDescription = smallDescription;
        this.landMarkId = landMarkId;
        this.bigDescription = bigDescription;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getLandMarkTitle() {
        return landMarkTitle;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public int getLandMarkId() {
        return landMarkId;
    }

    public String getBigDescription() {
        return bigDescription;
    }
}
