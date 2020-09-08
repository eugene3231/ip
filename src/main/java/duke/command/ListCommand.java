package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Inherits from generic command class.
 */
public class ListCommand extends Command {

    /**
     * Prints the task list.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        // Prints the given list
        handler.printList();
    }
}
