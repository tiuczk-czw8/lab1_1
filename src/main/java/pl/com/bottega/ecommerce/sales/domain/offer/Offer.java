package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Offer {

    private List<OfferItem> availableItems;

    @Contract(pure = true)
    public Offer(List<OfferItem> availableItems) {
        this.availableItems = availableItems;
    }

    @Contract(pure = true)
    private List<OfferItem> getAvailableItems() {
        return availableItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + (availableItems == null ? 0 : availableItems.hashCode());
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

        Offer other = (Offer) obj;

        if (availableItems == null || other.getAvailableItems() == null) {
            return false;
        }

        return availableItems.equals(other.getAvailableItems());
    }

    public boolean sameAs(@NotNull Offer seenOffer, double acceptableDifferenceInPercent) {
        if (availableItems.size() != seenOffer.getAvailableItems()
                                              .size()) {
            return false;
        }

        for (OfferItem item : availableItems) {
            Product sameProduct = item.getProduct();

            if (sameProduct == null) {
                return false;
            }

            // Products must have distinct IDs to be recognizable.
            OfferItem sameItem = seenOffer.findItem(sameProduct.getId());

            if (sameItem == null) {
                return false;
            }

            if (!sameItem.sameAs(item, acceptableDifferenceInPercent)) {
                return false;
            }
        }

        return true;
    }

    @Nullable
    private OfferItem findItem(String productId) {
        for (OfferItem item : availableItems) {
            if (item.getProduct()
                    .getId()
                    .equals(productId)) {
                return item;
            }
        }

        return null;
    }
}
