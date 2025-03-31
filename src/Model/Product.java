package Model;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.UUID;

public class Product implements Externalizable {
    private UUID id;
    private String name;
    private Integer price;

    public Product() {
    }
    public Product(String name, Integer price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " so‘m";
    }

    @Override
    public void writeExternal(java.io.ObjectOutput out) throws java.io.IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(price);
    }
    @Override
    public void readExternal(java.io.ObjectInput in) throws java.io.IOException, ClassNotFoundException {
        this.id = (UUID) in.readObject();
        this.name = (String) in.readObject();
        this.price = (Integer) in.readObject();
    }
}
