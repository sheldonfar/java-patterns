public class Main {
    public static void main(String[] args) {
        Task model = new Task("task1", 10);

        TaskView view = new TaskView();
        TaskController controller = new TaskController(model, view);

    }
}
