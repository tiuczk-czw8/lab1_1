package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private String currency;
    private BigDecimal value;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() { return value; }

    @Override public int hashCode() { return Objects.hash(currency, value); }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money other = (Money) obj;
        return Objects.equals(currency, other.currency) && Objects.equals(value, other.value);
    }

    public boolean sameAs(Money other, double delta) {
        BigDecimal max;
        BigDecimal min;
        if (value.compareTo(other.value) > 0) {
            max = value;
            min = other.value;
        } else {
            max = other.value;
            min = value;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }
}