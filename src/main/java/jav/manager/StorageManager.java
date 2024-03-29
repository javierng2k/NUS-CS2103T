package jav.manager;

import java.util.ArrayList;

import jav.exception.InvalidParamException;
import jav.task.Deadline;
import jav.task.Event;
import jav.task.Task;
import jav.task.ToDo;

/**
* StorageManager manages and stores tasks into a storage.
*/
public class StorageManager {
    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private StorageManager() {}
    private static class LazyHolder {
        static final StorageManager INSTANCE = new StorageManager();
    }
    public static StorageManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /** All types of tasks that is handled by Storage. */
    public enum StorageType {
        TODO,
        DEADLINE,
        EVENT
    }

    /** The currently stored tasks. */
    private ArrayList<Task> tasks;

    /**
     * Returns the currently stored tasks as a string.
     *
     * @return a string representation of the currently stored tasks.
     */
    public String printStoredTasks() {
        int i = 1;
        String s = "";
        if (tasks == null) {
            tasks = new ArrayList<>();
        } else {
            for(Task t : tasks) {
                s += "| " + i + ".";
                s += t.toString();
                s += "\n";
                i++;
            }
        }

        return s;
    }

    /**
     * Stores an unmarked task in the storage.
     *
     * @param param the information about the task.
     * @param type the type of task.
     */
    public void store(String param, StorageType type) {
        store(param, type, false);
    }


    /**
     * Stores a task in the storage.
     *
     * @param param the information about the task.
     * @param type the type of task.
     * @param isMarked whether the task should be marked.
     */
    public void store(String param, StorageType type, boolean isMarked) throws InvalidParamException {
        if (tasks == null) tasks = new ArrayList<>();

        Task task;
        try {
            switch (type) {
            case TODO:
                task = new ToDo(param, isMarked);   
                break;
            case DEADLINE:
                task = new Deadline(param, isMarked);
                break;
            case EVENT:
                task = new Event(param, isMarked);
                break;
            default:
                task = new Task(param, isMarked);   
                break;
            }
            tasks.add(task);
        } catch (InvalidParamException e) {
            throw e;
        }
    }

    /**
     * Update whether a specific task is marked or not.
     *
     * @param index the index of the task.
     * @param isMarked whether the task should be marked.
     * 
     * @return whether the specified task exists or not.
     */
    public boolean updateTask(int index, boolean isMarked) {
        if (tasks == null) return false;

        if (tasks.size() >= index + 1) {
            tasks.get(index).updateMark(isMarked);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a specific task.
     *
     * @param index the index of the task.
     * 
     * @return whether the specified task exists or not.
     */
    public boolean deleteTask(int index) {
        if (tasks == null) return false;

        if (tasks.size() >= index + 1) {
            tasks.remove(index);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns the data of the entire storage in a file ready format.
     *
     * @return a string containing the data of the entire storage in a file ready format.
     */
    public String getFileFormat() {
        String result = "";

        for (Task task : tasks) {
            result += "type=" + task.getType() + 
                    ",marked=" + (task.isMarked() ? "T" : "F") +
                    ",param=" + task.getFileFormatParam() +"\n";
        }

        return result;
    }

    /**
     * Load the saved data into the storage.
     */
    public void load(String savedData) {
        if (!savedData.equals("")) {
            String strings[] = savedData.split("\n");

            for (String str : strings) {
                String tokens[] = str.split(",marked=");
                String type = tokens[0].substring(5);
                tokens = tokens[1].split(",param=");
                String isMarked = tokens[0];
                String param = tokens[1];

                store(param, stringToStorageType(type), (isMarked.equals("T") ? true : false));
            }
        }
    }

    /**
     * A helper function to convert a string to a storage type.
     * 
     * @param a string to convert.
     * 
     * @return the converted storage type.
     */
    public StorageType stringToStorageType(String _type) {
        if (_type.equals("Event")) {
            return StorageType.EVENT;
        } else if (_type.equals("Deadline")) {
            return StorageType.DEADLINE;
        } else {
            return StorageType.TODO;
        }
    }
}
