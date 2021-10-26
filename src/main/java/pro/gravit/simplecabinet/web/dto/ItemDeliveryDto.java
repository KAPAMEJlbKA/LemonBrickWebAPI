package pro.gravit.simplecabinet.web.dto;

import java.util.List;

public class ItemDeliveryDto {
    public final long id;

    public final String itemName;
    public final String itemExtra;
    public final List<ItemEnchantDto> itemEnchants;
    public final String itemNbt;
    public final long part;
    public final boolean completed;

    public ItemDeliveryDto(long id, String itemName, String itemExtra, List<ItemEnchantDto> itemEnchants, String itemNbt, long part, boolean completed) {
        this.id = id;
        this.itemName = itemName;
        this.itemExtra = itemExtra;
        this.itemEnchants = itemEnchants;
        this.itemNbt = itemNbt;
        this.part = part;
        this.completed = completed;
    }

    public record ItemEnchantDto(String name, int level) {
    }
}
