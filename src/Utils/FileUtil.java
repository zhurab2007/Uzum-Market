package Utils;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<User> users = new ArrayList<>();
    public static List<Card> cards = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    public static void saveToFile(String filename, List<?> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("❌ Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromFile(String filename, Class<T> tClass) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Faylni o‘qishda xatolik: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public static void makingDatabase(String dataFolder) {
        if (!fileExists(dataFolder + "users.txt")) {
            saveToFile(dataFolder + "users.txt", new ArrayList<User>());
        }
        if (!fileExists(dataFolder + "cards.txt")) {
            saveToFile(dataFolder + "cards.txt", new ArrayList<Card>());
        }
        if (!fileExists(dataFolder + "products.txt")) {
            saveToFile(dataFolder + "products.txt", new ArrayList<Product>());
        }
        if (!fileExists(dataFolder + "orders.txt")) {
            saveToFile(dataFolder + "orders.txt", new ArrayList<Order>());
        }

        users = loadFromFile(dataFolder + "users.txt", User.class);
        cards = loadFromFile(dataFolder + "cards.txt", Card.class);
        products = loadFromFile(dataFolder + "products.txt", Product.class);
        orders = loadFromFile(dataFolder + "orders.txt", Order.class);
    }

    private static boolean fileExists(String filename) {
        return new File(filename).exists();
    }


}
