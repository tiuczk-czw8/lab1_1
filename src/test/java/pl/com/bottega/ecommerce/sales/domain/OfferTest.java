package pl.com.bottega.ecommerce.sales.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.com.bottega.ecommerce.sales.domain.offer.Money;
import pl.com.bottega.ecommerce.sales.domain.offer.Offer;
import pl.com.bottega.ecommerce.sales.domain.offer.OfferItem;
import pl.com.bottega.ecommerce.sales.domain.offer.Product;

public class OfferTest {

    @Test
    public void areOffersTheSame() {
        // given
        final String YUAN = "CNY";
        final Money GENERAL_DISCOUNT = new Money(new BigDecimal(0), YUAN);
        final Money price1 = new Money(new BigDecimal(1232), YUAN);
        final Money price3 = new Money(new BigDecimal(421.81), YUAN);
        Product product1 = new Product("001", price1, "Lenovo Ideapad S130-14", "notebook");
        Product product2 = new Product("002", price1, product1.getName(), product1.getType());
        Product product3 = new Product("003", price3, "The Last of Us Part II", "video game");
        OfferItem item1 = new OfferItem(product1, 3, GENERAL_DISCOUNT);
        OfferItem item2 = new OfferItem(product2, 1, new Money(new BigDecimal(300), YUAN));
        OfferItem item3 = new OfferItem(product3, 0, GENERAL_DISCOUNT);

        List<OfferItem> availableItems = new ArrayList<>();
        availableItems.add(item1);
        availableItems.add(item2);
        availableItems.add(item3);
        Offer offer1 = new Offer(availableItems);
        Offer offer2 = new Offer(availableItems);

        // when
        final double DELTA = 5;
        boolean isSame1 = offer1.sameAs(offer1, DELTA);
        boolean isSame2 = offer2.sameAs(offer1, DELTA);

        // then
        Assert.assertTrue(isSame1);
        Assert.assertTrue(isSame2);
    }
}
