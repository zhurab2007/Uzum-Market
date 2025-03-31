package Service;

import Menu.MainMenu;
import Model.Card;
import Model.User;
import Utils.FileUtil;
import Utils.Input;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Service {
    private static final String USER_FILE = "data/users.txt";
    public static final String CARD_FILE = "data/cards.txt";
    public static User currentUser = null;

    public static void debugUsers(List<User> users) {
        System.out.println("🔍 Yuklangan userlar soni: " + users.size());
        for (User user : users) {
            System.out.println("👤 User: " + user.getUsername() + " | Ismi: " + user.getName());
        }
    }

    public static void signUp(List<User> users) {
        String name = Input.str("Ismingizni kiriting: ");
        String username = Input.str("Username kiriting: ");
        String password = Input.str("Parol kiriting: ");

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("❌ Bu username allaqachon mavjud! Iltimos, boshqa username tanlang.");
                return;
            }
        }

        User newUser = new User(UUID.randomUUID(), name, username, password);
        users.add(newUser);

        FileUtil.saveToFile(USER_FILE, users);

        System.out.println("✅ Yangi user qo‘shildi: " + newUser.getUsername());
    }


    public static void signIn() {
        List<User> users = FileUtil.loadFromFile(USER_FILE, User.class);
        debugUsers(users);

        String username = Input.str("Username kiriting: ");
        String password = Input.str("Parolni kiriting: ");

        currentUser = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (currentUser == null) {
            System.out.println("❌ Username yoki parol noto‘g‘ri!");
            return;
        }

        System.out.println("✅ Xush kelibsiz, " + currentUser.getName() + " ✔︎");

        List<Card> cards = FileUtil.loadFromFile(CARD_FILE, Card.class);
        List<Card> userCards = getUserCards(cards, users);

        if (userCards.isEmpty()) {
            System.out.println("❌ Sizda karta mavjud emas! Yangi karta yaratilmoqda...");
            Card newCard = CardService.createCard(cards, currentUser);
            cards.add(newCard);
            FileUtil.cards.add(newCard);
            saveCardToFile(newCard);
        }

        userCards = getUserCards(cards, users);
        System.out.println("✅ Sizda quyidagi kartalar bor:");
        for (Card card : userCards) {
            System.out.println("💳 " + card.getCard_number() + " | Balans: " + card.getBalance() + " so‘m");
        }

        MainMenu.menu();
    }



    public static List<Card> getUserCards(List<Card> cards, List<User> users) {
        if (currentUser == null) {
            System.out.println("❌ Foydalanuvchi tizimga kirmagan!");
            return List.of();
        }

        for (Card card : cards) {
            if (card.getUser() == null) {
                for (User user : users) {
                    if (user.getId().equals(card.getUserId())) {
                        card.setUser(user);
                        break;
                    }
                }
            }
        }

        return cards.stream()
                .filter(card -> card.getUser() != null && card.getUser().getId().equals(currentUser.getId()))
                .toList();
    }


    private static void saveCardToFile(Card card) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CARD_FILE, true))) {
            writer.write(card.getCard_number() + "," + card.getUser().getUsername() + "," + card.getBalance());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("❌ Faylga yozishda xatolik yuz berdi!");
        }
    }
}
