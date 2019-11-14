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

import java.util.Objects;

public class OfferItem {

    private int quantity;

    private String discountCause;

    private Money discount;

    private Money totalCost;

    private Product product;

    public OfferItem(int quantity, String discountCause, Money discount, Money totalCost, Product product) {
        this.quantity = quantity;
        this.discountCause = discountCause;
        this.discount = discount;
        this.totalCost = totalCost;
        this.product = product;
    }

    public OfferItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public Money getDiscount() {
        return discount;
    }

    public void setDiscount(Money discount) {
        this.discount = discount;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Money totalCost) {
        this.totalCost = totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferItem offerItem = (OfferItem) o;
        return quantity == offerItem.quantity &&
                Objects.equals(discountCause, offerItem.discountCause) &&
                Objects.equals(discount, offerItem.discount) &&
                Objects.equals(totalCost, offerItem.totalCost) &&
                Objects.equals(product, offerItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, discountCause, discount, totalCost, product);
    }

    /**
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
