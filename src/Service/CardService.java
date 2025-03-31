package Service;

import Model.Card;
import Model.User;
import Utils.FileUtil;
import Utils.Input;

import java.util.List;

public class CardService {
    private static final String CARD_FILE = "data/cards.txt";

    public static Card createCard(List<Card> cards, User user) {
        String cardNumber = Input.str("💳 Yangi karta raqamini kiriting: ");
        int balance = Input.num("💰 Kartaga balansni kiriting: ");

        Card newCard = new Card(cardNumber, balance, user);
        cards.add(newCard);

        FileUtil.saveToFile(CARD_FILE, cards);

        System.out.println("------------------------------------------------");
        System.out.println("✅ Karta muvaffaqiyatli yaratildi!");
        System.out.println("💳 Karta raqami: " + newCard.getCard_number());
        System.out.println("💰 Balans: " + newCard.getBalance());
        System.out.println("👤 Foydalanuvchi: " + newCard.getUser().getName());

        return newCard;
    }
}
