package pl.com.bottega.ecommerce.sales.domain.offer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Money {
    private BigDecimal cost;

    private String currency;
}
