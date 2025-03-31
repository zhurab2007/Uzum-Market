package Model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {

    public static List<Product> getProducts(int category) {
        List<Product> products = new ArrayList<>();

        switch (category) {
            case 1 -> { // Kiyimlar
                products.add(new Product("Kurtka", 250000));
                products.add(new Product("Shim", 150000));
                products.add(new Product("Ko'ylak", 200000));
                products.add(new Product("Krossovka", 350000));
            }
            case 2 -> { // Maishiy Texnika
                products.add(new Product("Smart TV 42'", 3200000));
                products.add(new Product("Changyutgich", 850000));
                products.add(new Product("Kir yuvish mashinasi", 2800000));
                products.add(new Product("Muzlatkich", 3800000));
            }
            case 3 -> { // Avto Buyumlar
                products.add(new Product("Motor moyi 5L", 400000));
                products.add(new Product("Akumulyator", 900000));
                products.add(new Product("Shina 16R", 1200000));
                products.add(new Product("Avtomobil tozalagich", 300000));
            }
            case 4 -> { // Aksessuarlar
                products.add(new Product("Quloqchin", 150000));
                products.add(new Product("Telefon g‘ilofi", 50000));
                products.add(new Product("Smart soat", 800000));
                products.add(new Product("Quvvatlagich", 120000));
            }
            case 5 -> { // Elektronika
                products.add(new Product("Noutbuk", 7500000));
                products.add(new Product("Planshet", 3000000));
                products.add(new Product("Komp'yuter sichqonchasi", 120000));
                products.add(new Product("Klaviatura", 250000));
            }
            case 6 -> { // Oziq-ovqat
                products.add(new Product("Olma (1kg)", 25000));
                products.add(new Product("Go‘sht (1kg)", 95000));
                products.add(new Product("Non", 4000));
                products.add(new Product("Shakar (1kg)", 18000));
            }
            case 7 -> { // Uy jihozlari
                products.add(new Product("Divan", 5000000));
                products.add(new Product("Stol", 1500000));
                products.add(new Product("Kreslo", 1200000));
                products.add(new Product("Ko‘rpa", 500000));
            }
            case 8 -> { // Sport tovarlar
                products.add(new Product("Futbol to‘pi", 200000));
                products.add(new Product("Bokschi qo‘lqoplari", 400000));
                products.add(new Product("Velosiped", 2500000));
                products.add(new Product("Trenajyor", 3200000));
            }
            case 9 -> { // O'yinchoqlar
                products.add(new Product("Lego konstruktori", 350000));
                products.add(new Product("Mashina o'yinchog'i", 150000));
                products.add(new Product("Qo‘g‘irchoq", 180000));
                products.add(new Product("Dastgohli o‘yinchoq", 450000));
            }
            case 10 -> { // Parfyumeriya
                products.add(new Product("Atir Chanel", 1500000));
                products.add(new Product("Dior Sauvage", 1800000));
                products.add(new Product("Axe bodom spreyi", 120000));
                products.add(new Product("Nivea dezodorant", 80000));
            }
            default -> {
                System.out.println("❌ Noto‘g‘ri kategoriya tanlandi!");
                return null;
            }
        }
        return products;
    }
}
