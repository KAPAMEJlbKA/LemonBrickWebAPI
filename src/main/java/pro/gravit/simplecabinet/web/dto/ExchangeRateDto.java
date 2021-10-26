package pro.gravit.simplecabinet.web.dto;

import pro.gravit.simplecabinet.web.model.ExchangeRate;

public class ExchangeRateDto {
    public final long id;
    public final String fromCurrency;
    public final String toCurrency;
    public final double value;

    public ExchangeRateDto(ExchangeRate entity) {
        this.id = entity.getId();
        this.fromCurrency = entity.getFromCurrency();
        this.toCurrency = entity.getToCurrency();
        this.value = entity.getValue();
    }
}
