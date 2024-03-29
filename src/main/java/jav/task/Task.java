package jav.task;

import jav.exception.InvalidParamException;

/**
* A task consists of a description and whether it is marked as done or not.
*/
public class Task {
    /** The type of task. */
    protected String type;

    /** Description of task. */
    protected String description;

    /** Whether the task is done. */
    protected boolean isMarked;

    /**
     * Constructs a new Task.
     *
     * @return a new Task.
     */
    public Task() { 
        type = "Task";
        description = "task";
        isMarked = false;
    }
    
    /**
     * Constructs a new Task.
     * 
     * @param params a string containing the information about the task.
     * @param isMarked whether the task is marked.
     *
     * @return a new Task.
     * 
     * @throws InvalidParamException if the parameters are invalid.
     */
    public Task(String params, boolean isMarked) throws InvalidParamException { 
        type = "Task"; 
        description = params; 
        this.isMarked = isMarked;
    } 

    public String getDescription() {
        return description;
    }

    public String getType() { 
        return type;
    }

    public boolean isMarked() { 
        return isMarked;
    }

    /**
     * Updates whether the task is marked or not.
     */
    public void updateMark(boolean _flag) { 
        isMarked = _flag;
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return a string representation of the task.
     */
    public String toString() { 
        String s = "[";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + description;

        return s;
    }

    /**
     * Returns a string of the parameters in a file ready format.
     *
     * @return a string of the parameters in a file ready format.
     */
    public String getFileFormatParam() {
        return description;
    }
}