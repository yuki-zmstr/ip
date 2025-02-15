package yuki;

/**
 * Constants used in app.
 */
public class Constants {
    public static final int LENGTH_TODO_COMMAND = 4;
    public static final int LENGTH_DEADLINE_COMMAND = 8;
    public static final int LENGTH_EVENT_COMMAND = 5;

    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String HELP_COMMAND = "help";
    public static final String EXIT_COMMAND = "exit";

    // for extracting information from data file.
    public static final char TODO_INDICATOR = 'T';
    public static final char DEADLINE_INDICATOR = 'D';
    public static final char EVENT_INDICATOR = 'E';
    public static final char COMPLETION_INDICATOR = 'X';
    public static final int TASK_INDICATOR_INDEX = 7;
    public static final int TASK_DESCRIPTION_INDEX = 10;
    public static final int TASK_COMPLETION_INDEX = 3;

    // Prefix strings to identify dates inputted by user.
    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT_START = "/from ";
    private static final String TASK_DATA_PREFIX_EVENT_END = "/to ";
    static final String regexMatcher = TASK_DATA_PREFIX_DEADLINE + "|"
            + TASK_DATA_PREFIX_EVENT_START + "|" + TASK_DATA_PREFIX_EVENT_END;

    // for extracting command and description from user input.
    public static final int INDEX_COMMAND = 0;
    public static final int INDEX_DESCRIPTION = 1;

    // for mark, unmark, and delete commands.
    // extracts index of task from the user input.
    public static final int TASK_INDEX = 1;
}
