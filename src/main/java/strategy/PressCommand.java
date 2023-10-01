package strategy;

import java.util.Map;

public class PressCommand implements Command {
    private final Map<String, String> commands = initCommands();

    @Override
    public Map<String, String> initCommands() {
        return Map.ofEntries(
                Map.entry("UP", "UP"),
                Map.entry("UPPER", "UP"),
                Map.entry("RIGHT", "RIGHT"),
                Map.entry("DOWN", "DOWN"),
                Map.entry("LEFT", "LEFT"),
                Map.entry("HOME", "HOME"),
                Map.entry("END", "END"),
                Map.entry("TAB", "TAB"),
                Map.entry("TABULAR", "TAB"),
                Map.entry("CAPS", "CAPS LOCK"),
                Map.entry("CAPS LOCK", "CAPS LOCK"),
                Map.entry("ENTER", "ENTER"),
                Map.entry("SPACE", "SPACE")
                );
    }

    @Override
    public String getCommand(String key) {
        return commands.getOrDefault(key, null);
    }

}
