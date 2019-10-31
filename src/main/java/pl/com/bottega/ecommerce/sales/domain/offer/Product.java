package pl.com.bottega.ecommerce.sales.domain.offer;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product {
    private String productId;

    private String productName;

    private Date productSnapshotDate;

    private String productType;
}
