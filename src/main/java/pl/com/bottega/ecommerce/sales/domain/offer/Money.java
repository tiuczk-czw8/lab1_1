package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

import java.math.BigDecimal;

public class Money {

    private BigDecimal value;

    private String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}