package Menu;
import Model.*;
import Service.*;
import Utils.*;
import java.util.*;
import static Service.Service.currentUser;
public class MainMenu {
    private static final List<Order> basket = new ArrayList<>();
    public static void menu() {
        while (true) {
            System.out.println("""
                    🛍 Buyumlar bo'limiga xush kelibsiz ✅
                    
                    1 - Kiyimlar 🧥
                    2 - Maishiy Texnika 📺
                    3 - Avto Buyumlar 🛞
                    4 - Aksessuarlar 📲
                    5 - Elektronika ☎️
                    6 - Oziq-ovqat 🍐
                    7 - Uy jihozlari 🏡
                    8 - Sport tovarlar ⚽️
                    9 - O'yinchoqlar 🧸
                    10 - Parfyumeriya 🎀
                    11 - Savatcha 🛒
                    0 - Chiqish ❌
                    
                    """);

            int choice = Input.num("👉 Tanlov: ");
            if (choice == 0) {
                System.out.println("🚪 Dasturdan chiqildi.");
                return;
            }
            if (choice == 11) {
                showBasket();
                continue;
            }
            List<Product> products = ProductCategory.getProducts(choice);
            if (products == null) {
                System.out.println("❌ Noto'g'ri tanlov! Qaytadan urinib ko'ring.");
                continue;
            }
            processCategory(products);
        }

    }

    private static void processCategory(List<Product> products) {
        while (true) {
            System.out.println(" ");
            System.out.println("📜 Mahsulotlar ro‘yxati:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
            System.out.println("0 - Orqaga 🔙");

            int choice = Input.num("👉 Mahsulot tanlang: ");
            if (choice == 0) return;
            if (choice < 1 || choice > products.size()) {
                System.out.println("❌ Noto‘g‘ri tanlov!");
                continue;
            }

            Product selectedProduct = products.get(choice - 1);
            int quantity = Input.num("🛒 Nechta olasiz? ");

            if (quantity <= 0) {
                System.out.println("❌ Noto‘g‘ri miqdor!");
                continue;
            }

            int totalPrice = selectedProduct.getPrice() * quantity;
            Order order = new Order(selectedProduct.getName(), quantity, totalPrice);
            basket.add(order);

            System.out.println("✅ Mahsulot savatchaga qo‘shildi: " + selectedProduct.getName());
        }
    }

    private static void showBasket() {
        if (basket.isEmpty()) {
            System.out.println("🛒 Savatchangiz bo‘sh!");
            return;
        }

        System.out.println("📦Sizning savatchangiz: ");
        int totalSum = 0;
        for (Order order : basket) {
            System.out.println(order);
            totalSum += order.getTotalPrice();
        }
        System.out.println("💰Umumiy summa: " + totalSum + " so‘m");

        if (FileUtil.cards.isEmpty()) {
            System.out.println("🚨 Sizda karta mavjud emas! Iltimos, avval karta qo‘shing.");
            return;
        }

        int choice = Input.num("""
                Buyurtmani tasdiqlaysizmi?
                1 - Ha, buyurtmani tasdiqlayman ✅
                2 - Yo‘q, bekor qilaman ❌
                """);
        if (choice == 1) {
            confirmOrder(totalSum);
        } else {
            System.out.println("❌ Buyurtma bekor qilindi.");
        }
    }

    private static void confirmOrder(int totalSum) {
        List<Card> userCards = Service.getUserCards(FileUtil.cards, FileUtil.loadFromFile("data/users.txt", User.class));

        if (userCards.isEmpty()) {
            System.out.println("❌ Sizda karta mavjud emas! Iltimos, yangi karta qo‘shing.");
            return;
        }

        System.out.println("💳Mavjud kartalar: ");
        for (int i = 0; i < userCards.size(); i++) {
            System.out.println((i + 1) + ". " + userCards.get(i).getCard_number() + " (💰 " + userCards.get(i).getBalance() + " so‘m)");
        }

        int cardIndex = Input.num("👉 Qaysi kartadan foydalanasiz? ") - 1;
        if (cardIndex < 0 || cardIndex >= userCards.size()) {
            System.out.println("❌ Noto‘g‘ri tanlov!");
            return;
        }

        Card selectedCard = userCards.get(cardIndex);
        if (selectedCard.getBalance() < totalSum) {
            System.out.println("❌ Mablag‘ yetarli emas!");
            return;
        }

        for (Order order : basket) {
            OrderService.processOrder(currentUser, selectedCard, order);
        }

        basket.clear();
        System.out.println("✅ Buyurtma qabul qilindi va to‘lov amalga oshirildi!");
    }

}
