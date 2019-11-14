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

    private Product product;
    private int quantity;
    private Money totalCost;
    private Money discount;

    public OfferItem(@NotNull Product product, int quantity, @NotNull Money discount) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        computeTotalCost();
    }

    private void computeTotalCost() {
        Money bigQuantity = new Money(new BigDecimal(quantity), discount.getCurrency());
        this.totalCost = new Money(product.getPrice().getValue(), discount.getCurrency());
        this.totalCost.multiply(bigQuantity);

        if (!discount.isNullable()) {
            this.totalCost.subtract(discount);
        }
    }

    @Contract(pure = true)
    Product getProduct() {
        return product;
    }

    @Contract(pure = true)
    private Money getTotalCost() {
        return totalCost;
    }

    @Contract(pure = true)
    private Money getDiscount() {
        return discount;
    }

    @Contract(pure = true)
    private int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, totalCost, discount);
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

        OfferItem other = (OfferItem) obj;

        return Objects.equals(product, other.getProduct())
               && Objects.equals(quantity, other.getQuantity())
               && Objects.equals(totalCost, other.getTotalCost())
               && Objects.equals(discount, other.getDiscount());
    }

    boolean sameAs(@NotNull OfferItem other, double acceptablePercentageDifference) {
        if (!product.sameAs(other.getProduct())) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        Money max = other.getTotalCost();
        Money min = totalCost;

        if (totalCost.compareTo(other.getTotalCost()) > 0) {
            max = totalCost;
            min = other.getTotalCost();
        }

        Money difference = new Money(max.getValue(), max.getCurrency());
        difference.subtract(min);
        Money acceptableDelta = new Money(max.getValue(), max.getCurrency());
        Money multiplicand = new Money(BigDecimal.valueOf(acceptablePercentageDifference / 100), max.getCurrency());
        acceptableDelta.multiply(multiplicand);
        return acceptableDelta.compareTo(difference) >= 0;
    }
}
