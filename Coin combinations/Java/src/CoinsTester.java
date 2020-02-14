import java.util.HashMap;
import java.util.Map;

public class CoinsTester {

    public static void main(String[] args) {
        Map<Integer, String> changeDict = new HashMap<Integer, String>();
        changeDict.put(10, "Dimes");
        changeDict.put(5, "Nickels");
        changeDict.put(2, "Pennies");
        Coins coinClass = new Coins(changeDict);
        coinClass.ways(17);
    }
}
