package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class Money {

    private BigDecimal value;
    private String currency;

    Money(BigDecimal value, String currency) {
        setValue(value);
        setCurrency(currency);
    }

    @Contract(pure = true)
    BigDecimal getValue() {
        return value;
    }

    @Contract(pure = true)
    String getCurrency() {
        return currency;
    }

    private void setValue(BigDecimal value) {
        this.value = value;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    @Contract(pure = true)
    boolean isNullable() {
        return value == null || currency == null;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Money other = (Money) obj;
        return Objects.equals(value, other.getValue()) && Objects.equals(currency, other.getCurrency());
    }

    void subtract(@NotNull Money subtrahend) {
        setValue(value.subtract(subtrahend.getValue()));
    }

    void multiply(@NotNull Money multiplicand) {
        setValue(value.multiply(multiplicand.getValue()));
    }

    int compareTo(@NotNull Money value) {
        return this.value.compareTo(value.getValue());
    }
}
