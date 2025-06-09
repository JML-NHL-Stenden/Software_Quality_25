package controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker
{
    private final Map<Integer, Command> commandMap = new HashMap<>();

    public void bind(int keyCode, Command command)
    {
        commandMap.put(keyCode, command);
    }

    public void execute(int keyCode)
    {
        Command command = commandMap.get(keyCode);
        if (command!=null) {
            command.execute();
        }
    }
}
