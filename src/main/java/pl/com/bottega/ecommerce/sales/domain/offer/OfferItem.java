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
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    private Product product;

    private int quantity;

    private Money totalCost;

    private Money discount;

    private String discountCause;


    public OfferItem(Product product, int quantity, String currency) {
        this(product, quantity, null, null, currency);
    }

    public OfferItem(Product product, int quantity, BigDecimal discount, String discountCause, String currency) {

        this.product = product;
        this.quantity = quantity;
        this.discount = new Money(discount, currency)
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = new Money(product.getProductPrice().multiply(new BigDecimal(quantity)).subtract(discountValue), currency);
    }

    public Product getProduct() {
        return product;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(discount, discountCause, product, quantity, totalCost);
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
        return Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(product, other.product)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {

        if (product.sameAs(other.product, delta)) {
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
