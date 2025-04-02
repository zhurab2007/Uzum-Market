package Model;

import java.io.*;
import java.util.UUID;

public class Card implements Externalizable {
    private UUID id;
    private String card_number;
    private Integer balance;
    private String userId;
    private transient User user;

    public Card() {
    }
    public Card(String card_number, Integer balance, User user) {
        this.id = UUID.randomUUID();
        this.card_number = card_number;
        this.balance = balance;
        this.user = user;
        this.userId = user.getId().toString();
    }
    public String getCard_number() {
        return card_number;
    }
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId().toString();
    }
    public String getUserId() {
        return userId;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id.toString());
        out.writeObject(card_number);
        out.writeInt(balance);
        out.writeObject(userId);
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = UUID.fromString((String) in.readObject());
        this.card_number = (String) in.readObject();
        this.balance = in.readInt();
        this.userId = String.valueOf(UUID.fromString((String) in.readObject()));
    }

}
