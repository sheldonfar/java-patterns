import java.util.Objects;

public class Task implements Cloneable {

    private String title = "";
    private boolean active = false;
    private int time = 0;
    private int start = 0, end = 0, repeat = 0;

    /**
     * <p>Gets task title.</p>
     *
     * @return task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>Sets task title.</p>
     *
     * @param title specifies task's title
     */
    public final void setTitle(String title) throws IllegalArgumentException {
        if (title == null || Objects.equals(title, ""))
            throw new IllegalArgumentException("Task title should not be a null or an empty string!");
        this.title = title;
    }

    /**
     * <p>Checks if task is in active state.</p>
     *
     * @return true if the task is active, false - if not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * <p>Sets task state.</p>
     *
     * @param active task's state
     */
    public final void setActive(boolean active) {
        this.active = active;
    }

    /**
     * <p>Sets time for tasks alert if the task is not repeated.</p>
     * Also sets start, end and repeat to zero to make task be not repeated
     *
     * @param time task's time to happen
     */
    public final void setTime(int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Time value should be > 0. Passed value: " + time);
        }
        this.time = time;
        repeat = 0;
        start = 0;
        end = 0;
    }

    /**
     * <p>Sets time for  tasks alert if the task is repeated.</p>
     * Also sets time to zero to remove not-repeated state
     *
     * @param start  time to happen
     * @param end    time to end
     * @param repeat repeat interval
     */
    public final void setTime(int start, int end, int repeat) throws IllegalArgumentException {
        if (start < 0 || end < 0 || repeat < 0) {
            throw new IllegalArgumentException("Start, end and repeat should be > 0. Passed value for start, end, repeat: "
                    + start + ", " + end + ", " + repeat);
        } else if (start > end) {
            throw new IllegalArgumentException("Start should be < then end. Passed values for start and end: "
                    + start + ", " + end);
        } else if (start + repeat > end) {
            throw new IllegalArgumentException("Start + repeat should be <= end. Passed values for start, end, repeat: "
                    + start + ", " + end + ", " + repeat);
        }
        this.time = 0;
        this.start = start;
        this.end = end;
        this.repeat = repeat;
    }

    /**
     * <p>Gets Time when the task is going to happen if it's not-repeated.</p>
     * Else gets start time if task is repeated
     *
     * @return task's start time
     */
    public int getTime() {
        return (repeat == 0) ? time : start;
    }

    /**
     * <p>Gets @param start time if the task is repeated.</p>
     * Else gets time if it's not-repeated
     *
     * @return task's start time
     */
    public int getStartTime() {
        return (repeat == 0) ? time : start;
    }

    /**
     * <p>Gets tasks time if it's not-repeated.</p>
     * Else gets end time
     *
     * @return task's end time
     */
    public int getEndTime() {
        return (repeat == 0) ? time : end;
    }

    /**
     * <p>Gets tasks repeat interval if it's repeated.</p>
     * Else returns zero
     *
     * @return repeat interval
     */
    public int getRepeatInterval() {
        return (repeat == 0) ? 0 : repeat;
    }

    /**
     * <p>Checks if task is in repeated state.</p>
     *
     * @return true - task is repeated, false - task is not repeated
     */
    public boolean isRepeated() {
        return (repeat != 0);
    }

    /**
     * <p>Makes a string with info about task.</p>
     *
     * @return Task time for active & non-repeated task, start,end,repeat for repeated, inactive state for not active
     */
    public String toString() {
        if (active && repeat == 0) return "Task \"" + title + "\" at " + time;
        else if (active) return "Task \"" + title + "\" from " + start + " to " + end + " every " + repeat + " seconds";
        else return "Task \"" + title + "\" is inactive";
    }

    /**
     * <p>Makes a string with info about task.</p>
     *
     * @param time from where we count next repeat time
     * @return calculated time of next repeat from time given
     */
    public int nextTimeAfter(int time) throws IllegalArgumentException {
        if (time < 0)
            throw new IllegalArgumentException("Time should not be a negative number!");
        if (isRepeated() && time < start) return start;
        if (!isActive() || (isRepeated() && time >= end) || (!isRepeated() && time >= this.time)) return -1;
        if (!isRepeated()) return this.time;

        int nextTimeAfter = start;
        while (nextTimeAfter <= time) nextTimeAfter += repeat;

        return nextTimeAfter <= end ? nextTimeAfter : -1;
    }

    /**
     * Clones task
     *
     * @return new cloned task
     * @throws CloneNotSupportedException
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    /**
     * Checks if tasks are equal
     *
     * @param obj task which equality to check
     * @return true if tasks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task taskObj = (Task) obj;
        return Objects.equals(this.title, taskObj.getTitle()) && (this.time == taskObj.time) &&
                (this.start == taskObj.start) && (this.end == taskObj.end) && (this.repeat == taskObj.repeat);
    }

    /**
     * Gets hashcode of task
     *
     * @return hashcode number
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = 5 * result + title.hashCode();
        result = 5 * result + time;
        result = 5 * result + start;
        result = 5 * result + end;
        result = 5 * result + repeat;
        return result;
    }

    public Task() {

    }

    public Task(String title, int time) {
        setTitle(title);
        setTime(time);

    }

    public Task(String title, int start, int end, int repeat) {
        setTitle(title);
        setTime(start, end, repeat);
    }
}