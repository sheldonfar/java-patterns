//package ua.edu.sumdu.ta.minin.pr5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList implements Cloneable, Iterable<Task> {
    private static final int DEFAULT_CAPACITY = 1000;
    private static final int EXTEND_CAPACITY = 100;
    public static int taskListCount = 0;
    private Task[] tasks;


    public ArrayTaskList() {
        tasks = new Task[DEFAULT_CAPACITY];
        taskListCount++;
    }

    /**
     * Adds new task to array list
     *
     * @param task to set
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (Objects.equals(task.getTitle(), "")) {
            throw new IllegalArgumentException("Adding empty tasks is forbidden!");
        }
        if (size == tasks.length) extendTaskArray();
        tasks[size++] = task;
    }

    /**
     * Removes all task occurencies from array list
     *
     * @param task to remove
     */
    @Override
    public void remove(Task task) throws IllegalArgumentException {
        if (Objects.equals(task.getTitle(), "")) {
            throw new IllegalArgumentException("Removing empty tasks is forbidden!");
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(task.getTitle(), tasks[i].getTitle())) {
                int value = size - i - 1;
                if (value > 0) {
                    System.arraycopy(tasks, i + 1, tasks, i, value);
                }
                tasks[--size] = null;
                remove(task);
            }
        }
    }

    /**
     * Returns array of incoming tasks
     *
     * @param from interval lower bound
     * @param to   interval upper bound
     * @return Task[] array of found tasks
     */
    public Task[] incoming(int from, int to) throws IllegalArgumentException {
        if (from > to || from < 0 || to < 0) {
            throw new IllegalArgumentException("Please check params for incoming method");
        }

        int index = 0;
        int foundTasksCount = 0;
        for (int i = 0; i < size; i++) {
            if (tasks[i].nextTimeAfter(from) <= to && tasks[i].nextTimeAfter(from) != -1) {
                foundTasksCount++;
            }
        }
        Task[] inc = new Task[foundTasksCount];
        for (int i = 0; i < size; i++) {
            if (tasks[i].nextTimeAfter(from) <= to && tasks[i].nextTimeAfter(from) != -1) {
                inc[index++] = tasks[i];
            }
        }
        return inc;
    }

    /**
     * Extends tasks array using EXTEND_CAPACITY param
     */
    public void extendTaskArray() {
        Task[] newArray = new Task[tasks.length + EXTEND_CAPACITY];
        System.arraycopy(tasks, 0, newArray, 0, size);
        tasks = newArray;
    }

    /**
     * Clones ArrayTaskList
     *
     * @return new cloned list
     * @throws CloneNotSupportedException
     */
    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        return (ArrayTaskList) super.clone();
    }

    /**
     * ArrayTaskList iterator
     *
     * @return new instance of TaskListIterator
     */
    public Iterator<Task> iterator() {
        return new TaskListIterator();
    }

    private class TaskListIterator implements Iterator<Task> {
        private int currentPos;

        public TaskListIterator() {
            currentPos = 0;
        }

        /**
         * Checks if there is a next node
         *
         * @return true if there is next node, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentPos < size;
        }

        /**
         * Gets next node
         *
         * @return Task from the next node
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public Task next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element found!");
            }
            Task task = tasks[currentPos];
            currentPos++;
            return task;
        }

        @Override
        public void remove() {

        }
    }
}
