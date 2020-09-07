package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with description and by-time.
 * Inherits from Task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Initializes with a description and the time that deadline should be completed by.
     *
     * @param desc Description.
     * @param by Time to be completed by.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        parseDate(by);
    }

    /**
     * Processes user input into into another datetime format.
     * e.g. 2020-10-10 1800 --> 10 Oct 2020 6:00 PM
     * if user input doesnt match this format then accept normal string as time.
     *
     * @param input User input for the time for deadline.
     */
    public void parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, formatter);
            by = parsedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        } catch (DateTimeParseException e) {
            System.out.print("");
        }
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String printSaveFormat() {
        return "deadline " + super.printSaveFormat() + " /by " + by;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
