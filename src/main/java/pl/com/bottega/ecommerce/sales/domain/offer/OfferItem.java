package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    private Product product;
    private int quantity;
    private Money totalCost;
    private String discountCause;
    private Money discount;

    public OfferItem(Product product, int quantity, String currency) {

        this(product, quantity, null, null, currency);
    }

    public OfferItem(Product product, int quantity, BigDecimal discount, String discountCause, String currency) {

        this.product = product;
        this.quantity = quantity;
        this.discount = new Money(discount, currency);
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = new Money(product.getProductPrice().multiply(new BigDecimal(quantity)).subtract(discountValue), currency);
    }

    public Product getProduct() { return product; }

    public BigDecimal getTotalCost() {
        return totalCost.getValue();
    }

    public BigDecimal getDiscount() {
        return discount.getValue();
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOfferCurrency() {
        return totalCost.getCurrency();
    }

    @Override public int hashCode() {
        return Objects.hash(discount, discountCause, product, quantity, totalCost);
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount) && Objects.equals(discountCause, other.discountCause) && Objects.equals(product,
                other.product) && quantity == other.quantity && Objects.equals(totalCost, other.totalCost);
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (!product.sameAs(other.product, delta)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        if (!totalCost.sameAs(other.totalCost, delta)) {
            return false;
        }
        return true;
    }

}
