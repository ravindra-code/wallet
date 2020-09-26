package util;

import java.util.UUID;

public class WalletUtil {

    public static String generateID(){

        return UUID.randomUUID().toString();
    }
}
