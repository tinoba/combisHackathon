package combis.hackathon.domain.model;

public class PlanInfo {
    private String planName;
    private String planDate;
    private String hotelName;
    private String hotelInfo;

    public PlanInfo(final String planName, final String planDate, final String hotelName, final String hotelInfo) {
        this.planName = planName;
        this.planDate = planDate;
        this.hotelName = hotelName;
        this.hotelInfo = hotelInfo;
    }

    public String getPlanName() {
        return planName;
    }

    public String getPlanDate() {
        return planDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }
}
