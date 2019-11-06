package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Product {

    private String productId;

    private Money productPrice;

    private String productName;

    private Date productSnapshotDate;

    private String productType;

    Product(String productId, BigDecimal productPrice, String currency, String productName, Date productSnapshotDate, String productType){
        this.productName=productName;
        this.productId=productId;
        this.productPrice=new Money(productPrice,currency);
        this.productSnapshotDate=productSnapshotDate;
        this.productType=productType;

    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice.getValue();
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public int hashCode(){
        return Objects.hash(productId, productName, productPrice, productSnapshotDate, productType);
    }
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
        Product other = (Product) obj;
        return  Objects.equals(productId, other.productId)
                && Objects.equals(productName, other.productName)
                && Objects.equals(productPrice, other.productPrice)
                && Objects.equals(productSnapshotDate, other.productSnapshotDate)
                && Objects.equals(productType, other.productType);

    }
    public boolean sameAs(Product other, double delta) {
        if (productPrice == null) {
            if (other.productPrice != null) {
                return false;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return false;
        }
        if (productName == null) {
            if (other.productName != null) {
                return false;
            }
        } else if (!productName.equals(other.productName)) {
            return false;
        }

        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        if (productType == null) {
            if (other.productType != null) {
                return false;
            }
        } else if (!productType.equals(other.productType)) {
            return false;
        }

      return  true;
    }
}
