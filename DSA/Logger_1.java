
import java.util.*;

public class Logger_1 {

    HashMap<String, Integer> map;

    public Logger_1() {
        map = new HashMap<>();
    }

    public void shouldPrintMessage(int time, String st) {
        if (!map.containsKey(st)) {
            map.put(st, time);
            System.out.println(st);
        } else {
            int t = map.get(st);
            if (time - t <= 10) {
                System.out.println("Should not be printed");
            } else {
                map.put(st, time);
                System.out.println(st);
            }
        }
    }

}
