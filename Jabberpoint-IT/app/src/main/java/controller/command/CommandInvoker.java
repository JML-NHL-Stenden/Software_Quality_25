package controller.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Invokes commands mapped to key codes.
 */
public class CommandInvoker
{
    private final Map<Integer, Command> commandMap = new HashMap<>();

    /**
     * Binds a command to a specific key code.
     *
     * @param keyCode the key code
     * @param command the command to bind
     */
    public void bind(int keyCode, Command command)
    {
        commandMap.put(keyCode, command);
    }

    /**
     * Executes the command associated with the given key code, if any.
     *
     * @param keyCode the key code to trigger
     */
    public void execute(int keyCode)
    {
        Command command = commandMap.get(keyCode);
        if (command!=null) {
            command.execute();
        }
    }
}
