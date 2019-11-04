package pl.com.bottega.ecommerce.sales.domain.offer;

class Product {

    private String id;
    private Money price;
    private String name;
    private String type;

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    Money getPrice() {
        return price;
    }

    void setPrice(Money price) {
        this.price = price;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
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
