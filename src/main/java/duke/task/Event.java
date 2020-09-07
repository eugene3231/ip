package duke.task;


/**
 * Represents an event task with description and at-time.
 * Inherits from Task.
 */
public class Event extends Task {
    protected final String at;

    /**
     * Initializes with a description and the time of event.
     *
     * @param desc Description.
     * @param at Time of the event.
     */
    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String printSaveFormat() {
        return "event " + super.printSaveFormat() + " /at " + at;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
