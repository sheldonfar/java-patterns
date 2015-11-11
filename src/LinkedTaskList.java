//package ua.edu.sumdu.ta.minin.pr5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList implements Cloneable, Iterable<Task> {
    public static int taskListCount = 0;
    static final String taskPrefix = "[EDUCTR][TA] ";

    private Node head;

    private class Node {
        Node next;
        Task task;

        /**
         * Node class constructor
         *
         * @param task data to set
         */
        public Node(Task task) {
            next = null;
            this.task = task;
        }

        /**
         * Node class constructor
         *
         * @param task data to set
         * @param next pointer to next node
         */
        public Node(Task task, Node next) {
            this.next = next;
            this.task = task;
        }

        /**
         * Get task from Node
         *
         * @return task from Node
         */
        public Task getTask() {
            return task;
        }

        /**
         * Set task to Node
         *
         * @param task data to set
         */
        public void setTask(Task task) {
            this.task = task;
        }

        /**
         * Get pointer to next Node
         *
         * @return Node next element
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set pointer to next Node
         *
         * @param next pointer to set
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * LinkedTaskList zero-param constructor
     */
    public LinkedTaskList() {
        head = null;
        taskListCount++;
    }

    /**
     * Adds new task to linked list
     *
     * @param task to set
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (Objects.equals(task.getTitle(), "")) {
            throw new IllegalArgumentException("Adding empty tasks is forbidden!");
        }
        task.setTitle(taskPrefix + task.getTitle());
        Node newNode = new Node(task);
        Node currentNode = head;

        if (head == null) {
            head = newNode;
        } else {
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    /**
     * Removes all task occurencies from linked list
     *
     * @param task to remove
     */
    @Override
    public void remove(Task task) throws IllegalArgumentException {
        if (Objects.equals(task.getTitle(), "")) {
            throw new IllegalArgumentException("Removing empty tasks is forbidden!");
        }
        Node tmpNode = head;
        Node prevNode = null;

        while (tmpNode != null) {
            if (Objects.equals(tmpNode.getTask().getTitle(), task.getTitle())) {
                if (tmpNode == head) {
                    head = head.next;
                } else {
                    prevNode.next = tmpNode.next;
                }
                size--;
            } else {
                prevNode = tmpNode;
            }
            tmpNode = tmpNode.next;
        }
    }

    /**
     * Returns array of incoming tasks
     *
     * @param from interval lower bound
     * @param to   interval upper bound
     * @return Task[] array of found tasks
     */
    @Override
    public Task[] incoming(int from, int to) throws IllegalArgumentException {
        if (from > to || from < 0 || to < 0) {
            throw new IllegalArgumentException("Please check params for incoming method");
        }
        Node current = head;
        int index = 0;
        int foundTasksCount = 0;
        for (int i = 0; i < size; i++) {
            if (current.getTask().nextTimeAfter(from) <= to && current.getTask().nextTimeAfter(from) != -1) {
                foundTasksCount++;
            }
            if (current.getNext() != null)
                current = current.getNext();
        }
        current = head;
        Task[] inc = new Task[foundTasksCount];
        for (int i = 0; i < size; i++) {
            if (current.getTask().nextTimeAfter(from) <= to && current.getTask().nextTimeAfter(from) != -1) {
                inc[index++] = current.getTask();
            }
            if (current.getNext() != null)
                current = current.getNext();
        }
        return inc;
    }

    /**
     * Clones LinkedTaskList
     *
     * @return new cloned list
     * @throws CloneNotSupportedException
     */
    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        return (LinkedTaskList) super.clone();
    }

    /**
     * LinkedTaskList iterator
     *
     * @return new instance of LinkedListIterator
     */
    public Iterator<Task> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Task> {
        private Node currentPos;

        public LinkedListIterator() {
            super();
            currentPos = head;
        }

        /**
         * Checks if there is a pointer to next node
         *
         * @return true if there is next node, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentPos != null;
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
            Task task = (Task) currentPos.getTask();
            currentPos = currentPos.next;
            return task;
        }

        /**
         * This is not implemented, just overriden
         */
        @Override
        public void remove() {

        }
    }
}
