package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getCurrency() {
        return currency;
    }

    private BigDecimal totalCost;

    private String currency;
}
