package Model;

import Service.Service;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.UUID;

public class Order implements Externalizable {
    private UUID id;
    private String productName;
    private int quantity;
    private int totalPrice;
    private OrderStatus orderStatus;
    private User user;
    private Card card;
    private Long createdTime;
    private Long userGetTime;

    public Order(String productName, int quantity, int totalPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.user = user;
        this.card = card;
        this.createdTime = createdTime;
        this.userGetTime = userGetTime;
    }
    public int getTotalPrice() { return totalPrice; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(productName);
        out.writeInt(quantity);
        out.writeInt(totalPrice);
        out.writeObject(orderStatus);
        out.writeObject(user);
        out.writeObject(card);
        out.writeLong(createdTime);
        out.writeLong(userGetTime);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (UUID) in.readObject();
        this.productName = (String) in.readObject();
        this.quantity = in.readInt();
        this.totalPrice = in.readInt();
        this.orderStatus = (OrderStatus) in.readObject();
        this.user = (User) in.readObject();
        this.card = (Card) in.readObject();
        this.createdTime = in.readLong();
        this.userGetTime = in.readLong();
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "\n📦  BUYURTMA MA'LUMOTLARI  📦\n" +
                "🆔 ID: " + id + "\n" +
                "📌 Mahsulot: " + productName + "\n" +
                "📦 Soni: " + quantity + " dona\n" +
                "💰 Umumiy narx: " + totalPrice + " so'm\n" +
                "📅 Buyurtma vaqti: " + createdTime + "\n" +
                "🚦 Holati: " + (orderStatus != null ? orderStatus : "Noma'lum") + "\n" +
                "👤 Foydalanuvchi: " + (Service.currentUser != null ? Service.currentUser.getUsername() : "Noma'lum") + "\n" +
                "💳 To‘lov kartasi: " + (card != null ? card.getCard_number() : "Noma'lum") + "\n" +
                "📦 Yetib kelish vaqti: " + (userGetTime != null ? userGetTime : "⏳ Hali yetib kelmadi") + "\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    }

}