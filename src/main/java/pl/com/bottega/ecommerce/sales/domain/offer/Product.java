package pl.com.bottega.ecommerce.sales.domain.offer;

class Product {

    private String id;
    // TODO: uses price := Money
    private String name;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    boolean sameAs(Product other) {
        if (this.id == null || other.id == null) {
            return false;
        }

        if (!this.id.equals(other.id)) {
            return false;
        }

        // TODO: provide price:=Money
        // if (this.price == null || other.price == null) {
        // return false;
        // }

        // TODO: provide comparer for price:=Money
        // if (!this.price.equals(other.price)) {
        // return false;
        // }

        if (this.name == null || other.name == null) {
            return false;
        }

        if (!this.name.equals(other.name)) {
            return false;
        }

        if (this.type == null || other.type == null) {
            return false;
        }

        return this.type.equals(other.type);
    }
}
