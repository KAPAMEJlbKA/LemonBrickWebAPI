package pro.gravit.simplecabinet.web.dto;

import pro.gravit.simplecabinet.web.model.BalanceTransaction;

import java.time.LocalDateTime;

public class BalanceTransactionDto {
    public final long id;
    public final long userId;
    public final long fromId;
    public final long toId;
    public final double fromCount;
    public final double toCount;
    public final boolean multicurrency;
    public final String comment;
    public final LocalDateTime createdAt;

    public BalanceTransactionDto(BalanceTransaction entity) {
        this.id = entity.getId();
        this.userId = entity.getUser() == null ? -1 : entity.getUser().getId();
        this.fromId = entity.getFrom() == null ? -1 : entity.getFrom().getId();
        this.toId = entity.getTo() == null ? -1 : entity.getTo().getId();
        this.fromCount = entity.getFromCount();
        this.toCount = entity.getToCount();
        this.multicurrency = entity.isMulticurrency();
        this.comment = entity.getComment();
        this.createdAt = entity.getCreatedAt();
    }
}
