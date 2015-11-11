import java.util.Random;

public class TaskFactory {
    private static Random rand = new Random();
    private static final int RANGE = 50;
    public static Task getTask(String title, String criteria) {
        if(criteria.equals("active")) {
            Task t;
            if (rand.nextBoolean()) {
                t = new Task(title, rand.nextInt(RANGE) + 1);
            } else {
                t = new Task(title, rand.nextInt(RANGE) + 5, rand.nextInt(RANGE) + RANGE * 2, rand.nextInt(RANGE / 4));
            }
            t.setActive(true);
            return t;
        }
        else if (criteria.equals("repeated")) {
            Task t = new Task(title, rand.nextInt(RANGE) + 5, rand.nextInt(RANGE) + RANGE * 2, rand.nextInt(RANGE / 4));
            t.setActive(true);
            return t;
        }
        else if (criteria.equals("not repeated")) {
            Task t = new Task(title, rand.nextInt(RANGE) + 1);
            t.setActive(true);
        }
        return null;
    }
}
