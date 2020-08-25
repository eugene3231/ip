package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;

public class InvalidCommand extends Command {
    protected final String invalidInput;

    public InvalidCommand(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    @Override
    public void execute(taskListHandler handler, Storage storage) {
        // Other commands
        try {
            throw new DukeException("\u2639 Oops, I'm sorry but I don't know what "
                + '"' + invalidInput + '"' + " means :-(");
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
