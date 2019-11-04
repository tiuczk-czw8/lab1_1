package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Offer {

    private List<OfferItem> availableItems;
    private List<OfferItem> unavailableItems;

    @Contract(pure = true)
    public Offer(List<OfferItem> availableItems, List<OfferItem> unavailableItems) {
        this.availableItems = availableItems;
        this.unavailableItems = unavailableItems;
    }

    public List<OfferItem> getAvailableItems() {
        return availableItems;
    }

    public List<OfferItem> getUnavailableItems() {
        return unavailableItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (availableItems == null ? 0 : availableItems.hashCode());
        return result;
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
        if (availableItems == null) {
            if (other.availableItems != null) {
                return false;
            }
        } else if (!availableItems.equals(other.availableItems)) {
            return false;
        }
        return true;
    }

    public boolean sameAs(@NotNull Offer seenOffer, double acceptableDifferenceInPercent) {
        if (availableItems.size() != seenOffer.getAvailableItems()
                                              .size()) {
            return false;
        }

        for (OfferItem item : availableItems) {
            OfferItem sameItem = seenOffer.findItem(item.getProduct()
                                                        .getId());

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
