/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    // product
    private Product product;

    private BigDecimal productPrice;

    private int quantity;

    private BigDecimal totalCost;

    private String currency;

    // discount
    private String discountCause;

    private BigDecimal discount;

    public OfferItem(Product product, BigDecimal productPrice, int quantity) {
        this(product, productPrice, quantity, null, null);
    }

    public OfferItem(Product product, BigDecimal productPrice,
            int quantity, BigDecimal discount, String discountCause) {
        this.product = product;

        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = productPrice.multiply(new BigDecimal(quantity))
                                     .subtract(discountValue);
    }

    public String getProductId() {
        return this.product.getProductId();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return this.product.getProductName();
    }

    public Date getProductSnapshotDate() {
        return this.product.getProductSnapshotDate();
    }

    public String getProductType() {
        return this.product.getProductType();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, discount, discountCause, product, productPrice, quantity, totalCost);
    }

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
        OfferItem other = (OfferItem) obj;
        return Objects.equals(currency, other.currency)
               && Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(product, other.product)
               && Objects.equals(productPrice, other.productPrice)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productPrice == null) {
            if (other.productPrice != null) {
                return false;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return false;
        }
        if (product.getProductName() == null) {
            if (other.product.getProductName() != null) {
                return false;
            }
        } else if (!product.getProductName().equals(other.product.getProductName())) {
            return false;
        }

        if (product.getProductId() == null) {
            if (other.product.getProductId() != null) {
                return false;
            }
        } else if (!product.getProductId().equals(other.product.getProductId())) {
            return false;
        }
        if (product.getProductType() == null) {
            if (other.product.getProductType() != null) {
                return false;
            }
        } else if (!product.getProductType().equals(other.product.getProductType())) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
