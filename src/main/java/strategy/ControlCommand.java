package strategy;

import java.util.Map;

public class ControlCommand implements Command {
    private final Map<String, String> commands = initCommands();

    @Override
    public Map<String, String> initCommands() {
        return Map.ofEntries(
                Map.entry("CLEAR", "L"),
                Map.entry("CLIP", "X"),
                Map.entry("COPY", "C"),
                Map.entry("CUT", "X"),
                Map.entry("PASTE", "V"),
                Map.entry("SAVE", "S"),
                Map.entry("SEARCH", "F"),
                Map.entry("SELECT", "A"),
                Map.entry("UNDO", "Z")
        );
    }

    @Override
    public String getCommand(String key) {
        return commands.getOrDefault(key, null);
    }

}
