package strategy;

import java.util.Map;
/*
* This interface represents a generic type of command, i.e.,
* Control, Terminal, etc. The idea is to use it as a
* Strategy Design Pattern by implementing different types of
* commands as different classes
* */
public interface Command {

    Map<String, String> initCommands();

    String getCommand(String key);
}
