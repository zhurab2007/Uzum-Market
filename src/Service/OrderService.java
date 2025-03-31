package Service;

import Model.*;
import Utils.LoggerUtil;

import java.util.concurrent.*;

public class OrderService {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void processOrder(User user, Card card, Order order) {
        if (card.getBalance() < order.getTotalPrice()) {
            System.out.println("❌ Buyurtma uchun mablag' yetarli emas!");
            return;
        }

        card.setBalance(card.getBalance() - order.getTotalPrice());
        System.out.println("✅ Buyurtma qabul qilindi! Jarayon boshlandi...");

        executor.execute(() -> {
            try {
                order.setOrderStatus(OrderStatus.BUYURTMA_BERILDI);
                System.out.println("📦 Status: " + order.getOrderStatus());
                Thread.sleep(3000);

                order.setOrderStatus(OrderStatus.YIGILYAPDI);
                System.out.println("🔄 Status: " + order.getOrderStatus());
                Thread.sleep(15000);

                order.setOrderStatus(OrderStatus.KURYERDA);
                System.out.println("🚚 Status: " + order.getOrderStatus());
                Thread.sleep(120000);

                order.setOrderStatus(OrderStatus.OLIBKETISHGA_TAYYOR);
                System.out.println("✅ Buyurtmani olib ketishingiz mumkin!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public static void cancelOrder(User user, Card card, Order order) {
        if (order.getOrderStatus() == OrderStatus.OLIBKETISHGA_TAYYOR) {
            System.out.println("❌ Buyurtma allaqachon tayyor, bekor qilib bo'lmaydi!");
            return;
        }

        order.setOrderStatus(OrderStatus.BEKOR_QILINDI);
        System.out.println("🚫 Buyurtma bekor qilindi. Pul 3 daqiqa ichida qaytariladi.");

        scheduler.schedule(() -> {
            card.setBalance(card.getBalance() + order.getTotalPrice());
            System.out.println("💰 Pul qaytarildi: " + order.getTotalPrice() + " so'm");

            String logMessage = "🛑 User: " + user.getName() + ", Bekor qilingan buyurtma summasi: " + order.getTotalPrice();
            LoggerUtil.logInfo(logMessage);
        }, 3, TimeUnit.MINUTES);
    }

    public static void monitorOrder(Order order) {
        System.out.println("📊 Buyurtma holati: " + order.getOrderStatus());
    }
}