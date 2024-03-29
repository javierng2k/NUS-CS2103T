package jav.task;

import jav.exception.InvalidParamException;

/**
* A "ToDo" is a task. (For the time being, it is basically the same)
*/
public class ToDo extends Task {
    /**
     * Constructs a new ToDo.
     *
     * @return a new ToDo.
     */
    public ToDo() { 
        description = "todo";
        isMarked = false;
    }
    
    /**
     * Constructs a new todo.
     *
     * @param params a string containing the information about the todo.
     * @param isMarked whether the todo is marked.
     *
     * @return a new todo.
     * @throws InvalidParamException if the parameters are invalid.
     */
    public ToDo(String params, boolean isMarked) throws InvalidParamException { 
        description = params;
        this.isMarked = isMarked;
    }

    @Override
    public String toString() { 
        String s = "[T][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + description;

        return s;
    }

    @Override
    public String getFileFormatParam() {
        return description;
    }
}