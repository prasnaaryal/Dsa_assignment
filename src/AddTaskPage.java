import controller.TaskController;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddTaskPage extends JFrame {


    JLabel addtaskLabel;
    JLabel taskid;
    JPanel frame;

    JTextField addtxtTask;
    JTextField tasktxtId;
    JButton addTask;
    JButton createsJob;





    public AddTaskPage() {
        initialize();
        uIInitialize();


    }



    public void initialize () {
        setTitle("Add  Task");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF6E1F1));
        getContentPane();

        frame =new JPanel();
        frame.setBounds(300,50,550,550);
        frame.setBackground(Color.white);
        add(frame);

    }
    public void uIInitialize(){

//        id = new JLabel("Task Id");
//        id.setFont(new Font("Roboto", Font.PLAIN, 30));
//        id.setBounds(420, 120, 100, 25);
//        id.setForeground(Color.black);
//        add(id);
//        add(frame);

        taskid = new JLabel("TaskId");
        taskid.setFont(new Font("Roboto", Font.PLAIN, 30));
        taskid.setBounds(420, 120, 100, 25);
        taskid.setForeground(Color.black);
        add(taskid);
        add(frame);

        tasktxtId = new JTextField();
        tasktxtId.setBounds(420, 150, 120, 30);
        add(tasktxtId);
        add(frame);

//        taskLabel = new JLabel("Add your Task");
//        taskLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
//        taskLabel.setBounds(450, 200, 400, 40);
//        taskLabel.setForeground(Color.black);
//        add(taskLabel);
//        add(frame);

        addtaskLabel = new JLabel("Task Addition");
        addtaskLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
        addtaskLabel.setBounds(420, 200, 400, 40);
        addtaskLabel.setForeground(Color.black);
        add(addtaskLabel);
        add(frame);


//        txtTask = new JTextField();
//        txtTask.setBounds(350, 280, 450, 45);
//        add(txtTask);
//        add(frame);

        addtxtTask = new JTextField();
        addtxtTask.setBounds(350, 280, 450, 45);
        add(addtxtTask);
        add(frame);


        addTask = new JButton("Submit Task");
        addTask.setBounds(450,400,250,45);
        addTask.setForeground(Color.white);
        addTask.setFocusPainted(false);
        addTask.setBackground(new Color(0x0B0B17));
        addTask.setFont(new Font("Roboto", Font.PLAIN, 25));
        add(addTask);
        add(frame);

        createsJob = new JButton("Job Create");
        createsJob.setBounds(450,500,250,45);
        createsJob.setForeground(Color.white);
        createsJob.setFocusPainted(false);
        createsJob.setBackground(new Color(0x0E0E0E));
        createsJob.setFont(new Font("Roboto", Font.PLAIN, 25));
        add(createsJob);
        add(frame);

//        schduleJob = new JButton("Create Job");
//        createJob.setBounds(450,500,300,45);
//        createJob.setForeground(Color.white);
//        createJob.setFocusPainted(false);
//        createJob.setBackground(new Color(0x0E0E0E));
//        createJob.setFont(new Font("Roboto", Font.PLAIN, 25));
//        add(createJob);
//        add(frame);


        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        createsJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJob createJob = new CreateJob();
                createJob.show();
                dispose();
            }
        });
    }


    private void addTask(){
        String task=addtxtTask.getText();
        int taskId = Integer.parseInt(tasktxtId.getText());

        if(task.isEmpty()||tasktxtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all the fields");

        }

        else{
            TaskModel taskModel = new TaskModel(task,taskId);

            TaskController taskController = new TaskController();
            int insert =taskController.addTask(taskModel);

            if (insert > 0) {
                JOptionPane.showMessageDialog(null, "Task Added Successfully");


            } else {
                JOptionPane.showMessageDialog(null, "Failed to Add Task");
            }
        }
    }





    public static void main(String[] args) {
        new AddTaskPage().setVisible(true);
    }
}