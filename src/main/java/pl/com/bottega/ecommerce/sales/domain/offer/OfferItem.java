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

    // product
    private Product product;
    private int quantity;
    private Money totalCost;

    // discount
    private Money discount;

    public OfferItem(Product product, int quantity) {
        this(product, quantity, null);
    }

    public OfferItem(Product product, int quantity, Money discount) {

        this.product = product;
        this.quantity = quantity;
        this.discount = discount;


        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount.getValue());
        }

        this.totalCost.setValue(product.getProductPrice().getValue().multiply(new BigDecimal(quantity)).subtract(discountValue));
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}