package combis.hackathon.domain.model;

public final class Foods {

    public final String name;
    public final int idSlike;
    public final double price;
    public final float rating;
    public final String text;

    public Foods(final String name, final int idSlike, final double price, final float rating, final String text) {
        this.name = name;
        this.idSlike = idSlike;
        this.price = price;
        this.rating = rating;
        this.text = text;
    }
}
