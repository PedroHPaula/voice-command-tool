package singleton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;
/*
 * This class returns an object for the keyboard actions
 * needed for the script. As the actions do not change
 * during its running and we will only need one instance
 * of this class, it can be implemented using a Singleton.
 * */
public class MyRobot extends Robot {

    private static final MyRobot instance;

    static {
        try {
            instance = new MyRobot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public MyRobot() throws AWTException {
    }

    public static MyRobot getInstance() { return instance; }

    private final Map<String, Integer> mapOfKeys = mapKeys();

    public Map<String, Integer> mapKeys() {
        return Map.ofEntries(
                // Alphanumeric keys
                Map.entry("VK_A", KeyEvent.VK_A),
                Map.entry("VK_B", KeyEvent.VK_B),
                Map.entry("VK_C", KeyEvent.VK_C),
                Map.entry("VK_D", KeyEvent.VK_D),
                Map.entry("VK_E", KeyEvent.VK_E),
                Map.entry("VK_F", KeyEvent.VK_F),
                Map.entry("VK_G", KeyEvent.VK_G),
                Map.entry("VK_H", KeyEvent.VK_H),
                Map.entry("VK_I", KeyEvent.VK_I),
                Map.entry("VK_J", KeyEvent.VK_J),
                Map.entry("VK_K", KeyEvent.VK_K),
                Map.entry("VK_L", KeyEvent.VK_L),
                Map.entry("VK_M", KeyEvent.VK_M),
                Map.entry("VK_N", KeyEvent.VK_N),
                Map.entry("VK_O", KeyEvent.VK_O),
                Map.entry("VK_P", KeyEvent.VK_P),
                Map.entry("VK_Q", KeyEvent.VK_Q),
                Map.entry("VK_R", KeyEvent.VK_R),
                Map.entry("VK_S", KeyEvent.VK_S),
                Map.entry("VK_T", KeyEvent.VK_T),
                Map.entry("VK_U", KeyEvent.VK_U),
                Map.entry("VK_V", KeyEvent.VK_V),
                Map.entry("VK_W", KeyEvent.VK_W),
                Map.entry("VK_X", KeyEvent.VK_X),
                Map.entry("VK_Y", KeyEvent.VK_Y),
                Map.entry("VK_Z", KeyEvent.VK_Z),
                Map.entry("VK_-", KeyEvent.VK_MINUS),
                Map.entry("TAB", KeyEvent.VK_TAB),
                Map.entry("CAPS LOCK", KeyEvent.VK_CAPS_LOCK),
                Map.entry("ENTER", KeyEvent.VK_ENTER),
                Map.entry("SPACE", KeyEvent.VK_SPACE),

                // Navigation keys
                Map.entry("UP", KeyEvent.VK_UP),
                Map.entry("RIGHT", KeyEvent.VK_RIGHT),
                Map.entry("DOWN", KeyEvent.VK_DOWN),
                Map.entry("LEFT", KeyEvent.VK_LEFT),
                Map.entry("PAGE UP", KeyEvent.VK_PAGE_UP),
                Map.entry("PAGE DOWN", KeyEvent.VK_PAGE_DOWN),
                Map.entry("HOME", KeyEvent.VK_HOME),
                Map.entry("END", KeyEvent.VK_END)

        );
    }

    public void typeString(String string) {
        char[] listOfChars = string.toCharArray();
        String keyCode;
        for (char character : listOfChars) {
            keyCode = ("VK_" + character).toUpperCase();
            instance.keyPress(mapOfKeys.get(keyCode));
            instance.keyRelease(mapOfKeys.get(keyCode));
        }
    }

    public void tapKey(String keyCode) {
        instance.keyPress(mapOfKeys.get(keyCode));
        instance.keyRelease(mapOfKeys.get(keyCode));
    }
}
