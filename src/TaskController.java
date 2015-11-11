import javax.swing.*;

public class TaskController {
    private Task model;
    private TaskView view;

    public TaskController(Task model, TaskView view) {
        ArrayTaskList atl = new ArrayTaskList();

        this.model = model;
        this.view = view;

        view.initialize(atl);


        view.setResizable(false);
        view.setVisible(true);

        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        view.setLocationRelativeTo(null);
        view.setSize(600, 350);
    }
}
