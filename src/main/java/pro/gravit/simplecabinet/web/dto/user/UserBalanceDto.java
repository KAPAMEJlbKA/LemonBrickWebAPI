package pro.gravit.simplecabinet.web.dto.user;

import pro.gravit.simplecabinet.web.model.user.UserBalance;

public class UserBalanceDto {
    public final long id;
    public final double balance;
    public final String currency;

    public UserBalanceDto(UserBalance entity) {
        this.id = entity.getId();
        this.balance = entity.getBalance();
        this.currency = entity.getCurrency();
    }
}
