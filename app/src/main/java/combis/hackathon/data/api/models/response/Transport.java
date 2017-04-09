package combis.hackathon.data.api.models.response;

public final class Transport {

    public final String id;
    public final String transport_type;
    public final long start_id;
    public final long end_id;
    public final int duration;

    public Transport(final String id, final String transport_type, final long start_id, final long end_id, final int duration) {
        this.id = id;
        this.transport_type = transport_type;
        this.start_id = start_id;
        this.end_id = end_id;
        this.duration = duration;
    }
}
