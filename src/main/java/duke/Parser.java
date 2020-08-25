package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {

    public static Command parse(String input, TaskListHandler handler) throws DukeException {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            Task currentTask = Parser.parseModifyTaskCommand(input, handler);
            return new DoneCommand(currentTask);
        case "delete":
            Task delTask = Parser.parseModifyTaskCommand(input, handler);
            return new DeleteCommand(delTask);
        case "todo":
            Task newToDo = Parser.parseNewTaskCommand(input, Task.taskType.TODO);
            return new TodoCommand(newToDo);
        case "deadline":
            Task newDeadline = Parser.parseNewTaskCommand(input, Task.taskType.DEADLINE);
            return new DeadlineCommand(newDeadline);
        case "event":
            Task newEvent = Parser.parseNewTaskCommand(input, Task.taskType.EVENT);
            return new EventCommand(newEvent);
        case "clear":
            return new ClearCommand();
        default:
            return new InvalidCommand();
        }
    }

    public static Task parseModifyTaskCommand(String input,TaskListHandler handler) throws DukeException {
        String[] stringArr = input.split(" ");
        // DONE OR DELETE
        String lowerCaseOperation = stringArr[0].toLowerCase();
        if (stringArr.length != 2 ) {
            // if multiple tasks are given as arguments
            throw new DukeException("\u2639 Oops, pls enter only one task number after " + lowerCaseOperation);
        }
        try {
            // Finding the actual task
            int indexOfTask = Integer.parseInt(stringArr[1]) - 1;
            return handler.getTaskList().get(indexOfTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            throw new DukeException("\u2639 Oops, pls enter a valid task number after " + lowerCaseOperation);
        }
    }

    public static Task parseNewTaskCommand(String input, Task.taskType tasktype) throws DukeException {
        // Sorts the input into a task with or without time
        if (tasktype == Task.taskType.TODO) {
            // Without time
            if (input.substring(4).trim().isEmpty()) {
                // if given empty arguments or space as task
                throw new DukeException("\u2639 Oops, the description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            return new Todo(taskDesc);
        }
        if (tasktype == Task.taskType.DEADLINE) {
            try {
                return parseTaskWithTime(input, tasktype, "/by");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops, use add deadline format: deadline [task] /by [time (can be 'YYYY-MM-DD HHMM')]");
            }
        } else if (tasktype == Task.taskType.EVENT) {
            try {
                return parseTaskWithTime(input, tasktype, "/at");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 Oops, use add event format: event [task] /at [time]");
            }
        } else {
            return new Task("this task should not be created", "todo");
        }
    }

    public static Task parseTaskWithTime(String input, Task.taskType tasktype, String separator) throws DukeException {
        // Process string to find task description and time
        String taskDesc = input.substring(tasktype.name().length() + 1, input.indexOf(separator) - 1);
        checkIsFieldEmpty("taskDesc", taskDesc);
        // +4 due to size of /by or /at with a space
        String time = input.substring(input.indexOf(separator) + 4);
        checkIsFieldEmpty("time", time);
        if (tasktype ==  Task.taskType.DEADLINE) {
            return new Deadline(taskDesc, time);
        } else {
            return new Event(taskDesc, time);
        }
    }

    public static void checkIsFieldEmpty(String nameOfField, String field) throws DukeException {
        // check whether the argument given is empty
        if (field.trim().isEmpty()) {
            throw new DukeException("\u2639 Oops, " + nameOfField + " cannot be empty");
        }
    }
}
