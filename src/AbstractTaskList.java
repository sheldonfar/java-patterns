//package ua.edu.sumdu.ta.minin.pr5;


import java.util.Iterator;

public abstract class AbstractTaskList implements Cloneable, Iterable<Task> {
    protected int size = 0;

    public abstract void add(Task task);

    public abstract void remove(Task task);

    public int size() {
        return size;
    }

    public abstract Task[] incoming(int from, int to);

    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Task t : this) {
            result = 5 * result + t.hashCode();
        }
        return result;
    }

    public String toString() {
        String result = this.getClass().getName() + " [";

        int i = 0;
        for (Task t : this) {
            i++;
            if (i != size)
                result += t.getTitle() + ", ";
            else
                result += t.getTitle();
        }
        result += "]";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractTaskList))
            return false;
        if (!this.getClass().equals(obj.getClass()))
            return false;
        AbstractTaskList newObj = (AbstractTaskList) obj;
        if (size != newObj.size())
            return false;
        Iterator<Task> it1 = this.iterator();
        Iterator<Task> it2 = newObj.iterator();
        while (it1.hasNext()) {
            Task t1 = it1.next();
            Task t2 = it2.next();
            if (!t1.equals(t2))
                return false;
        }
        return true;
    }
}
