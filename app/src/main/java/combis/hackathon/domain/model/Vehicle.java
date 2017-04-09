package combis.hackathon.domain.model;

public final class Vehicle {

    public final String agencyName;
    public final float cijena;
    public final String radnoVrijeme;
    public final int vehicleImage;

    public Vehicle(final String agencyName, final float cijena, final String radnoVrijeme, final int vehicleImage) {
        this.agencyName = agencyName;
        this.cijena = cijena;
        this.radnoVrijeme = radnoVrijeme;
        this.vehicleImage = vehicleImage;
    }
}
