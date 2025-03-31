package Model;

import java.io.*;
import java.util.UUID;

public class User implements Externalizable {

    private UUID id;
    private String name;
    private String username;
    private String password;

    public User() {

    }

    public User(UUID id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(username);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (UUID) in.readObject();
        this.name = (String) in.readObject();
        this.username = (String) in.readObject();
        this.password = (String) in.readObject();
    }



}
