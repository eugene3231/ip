package duke;

public class Duke {
    public static void main(String[] args)  {
        initialize();
    }

    public static void initialize() {
        Storage storage = new Storage();
        if (Storage.isLoadingError) {
            return;
        }
        TaskListHandler handler = new TaskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }
}