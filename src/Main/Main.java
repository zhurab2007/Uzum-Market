package Main;

import Model.*;
import Service.*;
import Utils.*;

import java.util.List;
public class Main {
    public static final String DATA_FOLDER = "data";

    public static void main(String[] args) {
        FileUtil.makingDatabase("data");
        LoggerUtil.logInfo("Uzum market ishlamoqda ✔︎");
        List<User> users = FileUtil.loadFromFile(DATA_FOLDER + "data/users.txt", User.class);
        List<Card> cards = FileUtil.loadFromFile(DATA_FOLDER + "data/cards.txt", Card.class);
        while (true) {
            System.out.println(
                    """
                            1️⃣ - Sign Up (Register) 🔐
                            2️⃣ - Sign In (Login) 🔑
                            0️⃣ - Exit ❌
                            """
            );
            switch (Input.num("Choose: ")) {
                case 1 -> Service.signUp(users);
                case 2 -> Service.signIn();
                case 0 -> {
                    System.out.println("Dasturdan chiqildi. ❌");
                    return;
                }
                default -> System.out.println("❌ Noto‘g‘ri tanlov!");
            }
        }
    }
}
