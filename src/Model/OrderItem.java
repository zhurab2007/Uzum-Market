package Model;

import java.io.Serializable;
import java.util.UUID;

public class OrderItem implements Serializable {
    private UUID id;
    private Product product;
    private Integer amount;
    private Integer price;

    public OrderItem() {
    }
    public OrderItem(Product product, Integer amount) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.amount = amount;
        this.price = product.getPrice() * amount;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
