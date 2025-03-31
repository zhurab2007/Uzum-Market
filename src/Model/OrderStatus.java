package Model;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    BUYURTMA_BERILDI,
    KURYERDA,
    YIGILYAPDI,
    OLIBKETISHGA_TAYYOR,
    BEKOR_QILINDI,
    BUYURTMA_BEKOR_QILINDI
}
