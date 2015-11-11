import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Objects;


public class TaskView extends JFrame{


    private JPanel rootPanel;
    private JTextField titleField;
    private JRadioButton isNotRepeatedRadioButton;
    private JRadioButton isRepeatedRadioButton;
    private JTextField timeField;
    private JTextField endField;
    private JTextField repeatField;
    private JLabel titleLabel;
    private JLabel timeLabel;
    private JLabel endLabel;
    private JLabel repeatLabel;
    private JButton addNewTaskButton;
    private JTextArea textArea;
    private JTextField titleField1;
    private JRadioButton isNotRepeatedRadioButton1;
    private JRadioButton isRepeatedRadioButton1;
    private JTextField timeField1;
    private JTextField endField1;
    private JTextField repeatField1;
    private JButton editTaskButton;
    private JTextArea textArea1;
    private JLabel titleLabel1;
    private JLabel RepeatStateLabel1;
    private JLabel timeLabel1;
    private JLabel endLabel1;
    private JLabel repeatLabel1;
    private JLabel repeatStateLabel;
    private JTextField titleField2;
    private JLabel titleLabel2;
    private JButton deleteTaskButton;
    private JTextArea textArea2;
    private JTextField fromField;
    private JTextField toField;
    private JButton getIncomingTasksButton;
    private JTextArea textArea3;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JButton addRandomTaskButton;


    String title;
    int time, end, repeat, from, to;
    ArrayTaskList atl = new ArrayTaskList();

    public TaskView() {
        super("Task Manager v.0.1");

        setContentPane(rootPanel);

        timeField.setVisible(false);
        endField.setVisible(false);
        repeatField.setVisible(false);
        timeLabel.setVisible(false);
        endLabel.setVisible(false);
        repeatLabel.setVisible(false);

        timeField1.setVisible(false);
        endField1.setVisible(false);
        repeatField1.setVisible(false);
        timeLabel1.setVisible(false);
        endLabel1.setVisible(false);
        repeatLabel1.setVisible(false);
        pack();


        isNotRepeated(isNotRepeatedRadioButton, timeLabel, timeField, endLabel, endField, repeatLabel, repeatField);
        isNotRepeated(isNotRepeatedRadioButton1, timeLabel1, timeField1, endLabel1, endField1, repeatLabel1, repeatField1);
        isRepeated(isRepeatedRadioButton, timeLabel, timeField, endLabel, endField, repeatLabel, repeatField);
        isRepeated(isRepeatedRadioButton1, timeLabel1, timeField1, endLabel1, endField1, repeatLabel1, repeatField1);

        addNewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title = titleField.getText();


                try {
                    time = Integer.parseInt(timeField.getText());
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Time should be a number!");
                }
                Task t;
                try {
                    if(isNotRepeatedRadioButton.isSelected()) {
                        t = new Task(title, time);
                    }
                    else if (isRepeatedRadioButton.isSelected()) {
                        try {
                            end = Integer.parseInt(endField.getText());
                            repeat = Integer.parseInt(repeatField.getText());
                        }
                        catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "End & repeat should be numbers!");
                        }

                        t = new Task(title, time, end, repeat);
                    }
                    else {
                        return;
                    }
                    t.setActive(true);
                    atl.add(t);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                textArea.setText("");

                for(Task task : atl) {
                    textArea.append(task.toString() + "\n");
                }
            }
        });

        addRandomTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atl.add(TaskFactory.getTask("Random Task", "active"));
                textArea.setText("");

                for(Task task : atl) {
                    textArea.append(task.toString() + "\n");
                }
            }
        });


        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title = titleField1.getText();


                try {
                    time = Integer.parseInt(timeField1.getText());
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Time should be a number!");
                }
                Task t;
                try {
                    if(isNotRepeatedRadioButton1.isSelected()) {
                        t = new Task(title, time);
                    }
                    else if (isRepeatedRadioButton1.isSelected()) {
                        try {
                            end = Integer.parseInt(endField1.getText());
                            repeat = Integer.parseInt(repeatField1.getText());
                        }
                        catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "End & repeat should be numbers!");
                        }
                        t = new Task(title, time, end, repeat);
                    }
                    else {
                        return;
                    }
                    t.setActive(true);
                    for(Task task : atl) {
                        if(Objects.equals(task.getTitle(), t.getTitle())) {
                            if(t.isRepeated())
                                task.setTime(t.getStartTime(), t.getEndTime(), t.getRepeatInterval());
                            else
                                task.setTime(t.getTime());

                        }
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                textArea1.setText("");

                for(Task task : atl) {
                    textArea1.append(task.toString() + "\n");
                }
            }
        });


        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title = titleField2.getText();

                for(Task task: atl) {
                    if(Objects.equals(task.getTitle(), title)) {
                        atl.remove(task);
                    }
                }

                textArea2.setText("");

                for(Task task : atl) {
                    textArea2.append(task.toString() + "\n");
                }
            }
        });


        getIncomingTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    from = Integer.parseInt(fromField.getText());
                    to = Integer.parseInt(toField.getText());

                    textArea3.setText("");
                    for(Task task : atl.incoming(from, to)) {
                        textArea3.append(task.toString() + "\n");
                    }
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "From & To should be numbers!");
                }
            }

        });
    }

    public void initialize(ArrayTaskList atl) {
        this.atl = atl;
    }
    public void isNotRepeated (final JRadioButton rb, final JLabel timeLabel, final JTextField timeField,
                               final JLabel endLabel, final JTextField endField, final JLabel repeatLabel, final JTextField repeatField) {
        rb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb.isSelected()) {
                    timeLabel.setVisible(true);
                    timeLabel.setText("Time");

                    timeField.setVisible(true);

                    endLabel.setVisible(false);
                    endField.setVisible(false);

                    repeatLabel.setVisible(false);
                    repeatField.setVisible(false);
                }
            }
        });
    }

    public void isRepeated(final JRadioButton rb, final JLabel timeLabel, final JTextField timeField, final JLabel endLabel,
                           final JTextField endField, final JLabel repeatLabel, final JTextField repeatField) {
        rb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb.isSelected()) {
                    timeLabel.setVisible(true);
                    timeLabel.setText("Start");
                    timeField.setVisible(true);

                    endLabel.setVisible(true);
                    endField.setVisible(true);

                    repeatLabel.setVisible(true);
                    repeatField.setVisible(true);
                }
            }
        });
    }
}
