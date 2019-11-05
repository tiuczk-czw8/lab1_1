package pl.com.bottega.ecommerce.sales.domain.offer;

import org.jetbrains.annotations.Contract;

public class Product {

    private String id;
    private Money price;
    private String name;
    private String type;

    public Product(String id, Money price, String name, String type) {
        setId(id);
        setPrice(price);
        setName(name);
        setType(type);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setPrice(Money price) {
        this.price = price;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setType(String type) {
        this.type = type;
    }

    String getId() {
        return id;
    }

    @Contract(pure = true)
    Money getPrice() {
        return price;
    }

    @Contract(pure = true)
    public String getName() {
        return name;
    }

    @Contract(pure = true)
    public String getType() {
        return type;
    }

    boolean sameAs(Product other) {
        if (this.id == null || other.id == null) {
            return false;
        }

        if (!this.id.equals(other.getId())) {
            return false;
        }

        if (this.price == null || other.getPrice() == null) {
            return false;
        }

        if (!this.price.equals(other.getPrice())) {
            return false;
        }

        if (this.name == null || other.getName() == null) {
            return false;
        }

        if (!this.name.equals(other.getName())) {
            return false;
        }

        if (this.type == null || other.getType() == null) {
            return false;
        }

        return this.type.equals(other.getType());
    }
}
