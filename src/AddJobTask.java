import controller.TaskController;
import model.JobTaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AddJobTask extends JFrame{
    JButton back;
    JLabel jobid;
    JTextField jobtxtId;
    JComboBox runtask;
    JComboBox dependent;
    JButton addTak;
    JLabel runningTask;
    JLabel dependentTask;

    AddJobTask(){
        setTitle("Select Task For Job");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x0E0E0E));
        getContentPane();
        initilize();
    }

    void initilize(){

        back = new JButton("Back");
        back.setBounds(0, 10, 70, 20);
        back.setFont(new Font("Roboto", Font.BOLD, 10));
        back.setFocusPainted(false);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskPage addTaskPage = new AddTaskPage();
                addTaskPage.show();
                dispose();
            }
        });

        TaskController taskController = new TaskController();
        ArrayList<Integer> idtaskList = taskController.fetchTask();
        String[] idOfTaskList=new String[idtaskList.size()+1];
        System.out.println(idtaskList);


        for(int i=0; i<idtaskList.size(); i++){

            System.out.println(idtaskList.get(i));
            idOfTaskList[i]=idtaskList.get(i).toString();
        }

        System.out.println(Arrays.toString(idOfTaskList));

        jobid = new JLabel("Job Id");
        jobid.setFont(new Font("Roboto", Font.PLAIN, 30));
        jobid.setBounds(400, 120, 200, 40);
        jobid.setForeground(Color.black);
        add(jobid);
//        add(frame)

        jobtxtId = new JTextField();
        jobtxtId.setBounds(700, 120, 200, 40);
        add(jobtxtId);

        runningTask = new JLabel("Current Task");
        runningTask.setFont(new Font("Roboto", Font.PLAIN, 30));
        runningTask.setBounds(400, 200, 300, 40);
        runningTask.setForeground(Color.black);
        add(runningTask);

        runtask = new JComboBox(idOfTaskList);
        runtask.setFont(new Font("Roboto", Font.PLAIN, 20));
        runtask.setBounds(700, 200, 300, 40);
        add(runtask);

        dependentTask = new JLabel("Dependent Task");
        dependentTask.setFont(new Font("Roboto", Font.PLAIN, 30));
        dependentTask.setBounds(350, 300, 300, 40);
        dependentTask.setForeground(Color.black);
        add(dependentTask);

        dependent = new JComboBox(idOfTaskList);
        dependent.setFont(new Font("Roboto", Font.PLAIN, 20));
        dependent.setBounds(700, 300, 300, 40);
        add(dependent);

        addTak = new JButton("Add Task");
        addTak.setBounds(450,400,300,45);
        addTak.setForeground(Color.white);
        addTak.setFocusPainted(false);
        addTak.setBackground(new Color(0x07070A));
        addTak.setFont(new Font("Roboto", Font.PLAIN, 25));
        add(addTak);

        addTak = new JButton("start job");
        addTak.setBounds(450,500,300,45);
        addTak.setForeground(Color.white);
        addTak.setFocusPainted(false);
        addTak.setBackground(new Color(0x07070A));
        addTak.setFont(new Font("Roboto", Font.PLAIN, 25));
        add(addTak);

        addTak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jobId;
                int source;
                int destination;

                if(jobtxtId.getText().isEmpty()||String.valueOf(runtask.getSelectedItem()).isEmpty()){
                    JOptionPane.showMessageDialog(null, "blank fields", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    jobId=Integer.parseInt(jobtxtId.getText());
                    source=Integer.parseInt(String.valueOf(dependent.getSelectedItem()));
                    destination=Integer.parseInt(String.valueOf(runtask.getSelectedItem()));

                    JobTaskModel jobTaskModel = new JobTaskModel(jobId,source,destination);
                    TaskController taskController1 = new TaskController();
                    int insert = taskController.addJobTask(jobTaskModel);

                    if(insert>0){
                        JOptionPane.showMessageDialog(null, "Task added Successfully");
                    }else{
                        JOptionPane.showMessageDialog(null, "Filed to add Task");
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        new AddJobTask().setVisible(true);
    }
}
