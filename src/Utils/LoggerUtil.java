package Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerUtil implements Serializable {

    private static final Logger logger = Logger.getLogger("Uzum_Martket_Logger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("Uzum_Martket.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
    public static void logWarning(String message) {
        logger.warning(message);
    }
    public static void logError(String message) {
        logger.severe(message);
    }
}
