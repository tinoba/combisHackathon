package combis.hackathon.domain.model;

public class ActivityModel {

    private int activityImageId;
    private String activityName;
    private String activityInfoOne;
    private String activityInfoTwo;

    public ActivityModel(final int activityImageId, final String activityName, final String activityInfoOne, final String activityInfoTwo) {
        this.activityImageId = activityImageId;
        this.activityName = activityName;
        this.activityInfoOne = activityInfoOne;
        this.activityInfoTwo = activityInfoTwo;
    }

    public int getActivityImageId() {
        return activityImageId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityInfoOne() {
        return activityInfoOne;
    }

    public String getActivityInfoTwo() {
        return activityInfoTwo;
    }
}
