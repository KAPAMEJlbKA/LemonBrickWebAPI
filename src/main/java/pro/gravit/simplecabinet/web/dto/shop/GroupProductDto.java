package pro.gravit.simplecabinet.web.dto.shop;

public class GroupProductDto {
    public  final String server;
    public final long id;
    public final double price;
    public final String currency;
    public final String displayName;
    public final String description;
    public final String pictureUrl;
    public final long expireDays;
    public final boolean available;

    public GroupProductDto(long id, String server, double price, String currency, String displayName, String description, String pictureUrl, long expireDays, boolean available) {
        this.id = id;
        this.server = server;
        this.price = price;
        this.currency = currency;
        this.displayName = displayName;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.expireDays = expireDays;
        this.available = available;
    }
}
