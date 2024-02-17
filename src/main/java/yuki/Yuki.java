package yuki;

import yuki.exceptions.YukiExceptions;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
public class Yuki {

    // length of each command: 'todo', 'deadline', 'event'
    private static final int LENGTH_TODO_COMMAND = 4;
    private static final int LENGTH_DEADLINE_COMMAND = 8;
    private static final int LENGTH_EVENT_COMMAND = 5;

    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String[] data;
    private static String description;

    public static void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        int index = 1;
        for (Task item : tasks) {
            System.out.println((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description);
            index++;
        }
        reportNumberOfTasks();
    }

    public static void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0];
        Task t = new Todo(description);
        tasks.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void addDeadline(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_DEADLINE_COMMAND));
        if (data.length < 2) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0] + " (by:" + data[1] + ")";
        Task t = new Deadline(description);
        tasks.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void addEvent(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_EVENT_COMMAND));
        if (data.length < 3) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
        Task t = new Event(description);
        tasks.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void reportNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(String line, String cmd) throws YukiExceptions.InvalidIndexException {
        int indexTask;
        indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > tasks.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        if (cmd.equals("mark")){
            tasks.get(indexTask - 1).markAsDone();
        } else if (cmd.equals("unmark")) {
            tasks.get(indexTask - 1).markAsUndone();
        } else {
            System.out.println("invalid command in mark method");
        }
    }

    public static void main(String[] args) {
        Utils.printWelcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String command;

        while (!line.equals("exit")) {
            Utils.printLine();
            command = line.split(" ")[0];

            switch(command) {
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    markTask(line, "mark");
                } catch (YukiExceptions.InvalidIndexException e) {
                    System.out.println(e);
                    listTasks();
                }
                break;
            case "unmark":
                try {
                    markTask(line, "unmark");
                } catch (YukiExceptions.InvalidIndexException e) {
                    System.out.println(e);
                    listTasks();
                }
                break;
            case "todo":
                try {
                    addTodo(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            case "deadline":
                try {
                    addDeadline(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            case "event":
                try {
                    addEvent(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            default:
                Utils.printInvalidCommandWarning();
                Utils.printInstructions();
                break;
            }

            Utils.printLine();
            line = in.nextLine();
        }
        Utils.printExitMessage();
    }
}
