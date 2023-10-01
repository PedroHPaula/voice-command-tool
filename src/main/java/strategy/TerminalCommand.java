package strategy;

import java.util.Map;

public class TerminalCommand implements Command {
    private final Map<String, String> commands = initCommands();

    @Override
    public Map<String, String> initCommands() {
        return Map.ofEntries(
                Map.entry("ARGUMENT", "xargs"),
                Map.entry("COPY", "cp"),
                Map.entry("CD", "cd"),
                Map.entry("CLEAR", "clear"),
                Map.entry("DIRECTORY", "mkdir"),
                Map.entry("DIRECTORY(4)", "mkdir"),
                Map.entry("ECHO", "echo"),
                Map.entry("EXIT", "exit"),
                Map.entry("FIND", "find"),
                Map.entry("GLOBAL", "grep"),
                Map.entry("LESS", "less"),
                Map.entry("LIST", "list"),
                Map.entry("LOCATE", "plocate"),
                Map.entry("MAN", "man"),
                Map.entry("MOVE", "mv"),
                Map.entry("NANO", "nano"),
                Map.entry("NEW", "gnome-terminal"),
                Map.entry("REMOVE", "rm"),
                Map.entry("SILENCE", "silence"),
                Map.entry("STREAM", "sed"),
                Map.entry("SWITCH", "sudo"),
                Map.entry("TAR", "tar"),
                Map.entry("ZIP", "gzip"));
    }

    @Override
    public String getCommand(String key) {
        return commands.getOrDefault(key, null);
    }

}
