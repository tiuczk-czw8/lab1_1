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

import java.math.BigDecimal;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class OfferItem {

    // product
    private Product product;

    private int quantity;
    private BigDecimal totalCost;
    private String currency;
    // discount
    private String discountCause;
    private BigDecimal discount;

    public OfferItem(Product product, int quantity) {
        this(product, quantity, null, null);
    }

    private OfferItem(Product product, int quantity, BigDecimal discount, String discountCause) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        // this.totalCost = productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue);
        // TODO: count productPrice using price := Money
    }

    @Contract(pure = true)
    Product getProduct() {
        return product;
    }

    @Contract(pure = true)
    private BigDecimal getTotalCost() {
        return totalCost;
    }

    @Contract(pure = true)
    private String getTotalCostCurrency() {
        return currency;
    }

    @Contract(pure = true)
    private BigDecimal getDiscount() {
        return discount;
    }

    @Contract(pure = true)
    private String getDiscountCause() {
        return discountCause;
    }

    @Contract(pure = true)
    private int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, discount, discountCause, quantity, totalCost);
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
        return Objects.equals(currency, other.getTotalCostCurrency())
               && Objects.equals(discount, other.getDiscount())
               && Objects.equals(discountCause, other.getDiscountCause())
               && Objects.equals(product, other.getProduct())
               && quantity == other.getQuantity()
               && Objects.equals(totalCost, other.getTotalCost());
    }

    boolean sameAs(@NotNull OfferItem other, double acceptablePercentageDifference) {
        if (!product.sameAs(other.getProduct())) {
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
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(acceptablePercentageDifference / 100));
        return acceptableDelta.compareTo(difference) > 0;
    }
}
