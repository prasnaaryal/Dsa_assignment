
import controller.TaskController;
import model.JobModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateJob extends JFrame {
    JLabel jobLabel;
    JLabel id;
    JLabel numTask;
    JLabel profit;
    JLabel time;
    JTextField txtjob;
    JTextField txtId;
    JTextField txtNumTask;
    JTextField txtprofit;
    JTextField txtTime;
    JButton CreateJob;
    JButton addTak;
    JButton back;
    public CreateJob() {
        initialize();
        uIInitialize();
    }

    public void initialize () {
//        screen size
        setTitle("Create Job");
        setSize(1400, 900 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0xEADCBB));
        getContentPane();
    }
    public void uIInitialize(){
        back = new JButton("Back");
        back.setBounds(0, 10, 70, 20);
        back.setFont(new Font("Times New Roman", Font.BOLD, 10));
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

        id = new JLabel("Job Id");
        id.setFont(new Font("San Serif", Font.PLAIN, 40));
        id.setBounds(400, 120, 200, 40);
        id.setForeground(Color.black);
        add(id);

        txtId = new JTextField();
        txtId.setBounds(700, 120, 200, 40);
        add(txtId);

        time = new JLabel("End Time");
        time.setFont(new Font("San Serif", Font.PLAIN, 40));
        time.setBounds(400, 180, 200, 40);
        time.setForeground(Color.black);
        add(time);

        txtTime = new JTextField();
        txtTime.setBounds(700, 180, 200, 40);
        add(txtTime);

        profit=new JLabel("Profit");
        profit.setFont(new Font("San Serif", Font.PLAIN, 40));
        profit.setBounds(400, 240, 200, 40);
        profit.setForeground(Color.black);
        add(profit);

        txtprofit = new JTextField();
        txtprofit.setBounds(700, 240, 200, 40);
        add(txtprofit);

        numTask = new JLabel("Task no.");
        numTask.setFont(new Font("San Serif", Font.PLAIN, 40));
        numTask.setBounds(400, 300, 200, 40);
        numTask.setForeground(Color.black);
        add(numTask);

        txtNumTask = new JTextField();
        txtNumTask.setBounds(700, 300, 200, 40);
        add(txtNumTask);

        jobLabel = new JLabel("Work name");
        jobLabel.setFont(new Font("San Serif", Font.PLAIN, 40));
        jobLabel.setBounds(400, 360, 300, 40);
        jobLabel.setForeground(Color.black);
        add(jobLabel);

        txtjob = new JTextField();
        txtjob.setBounds(700, 360, 450, 45);
        add(txtjob);

        CreateJob = new JButton("Submit");
        CreateJob.setBounds(450,500,300,45);
        CreateJob.setForeground(Color.white);
        CreateJob.setBackground(new Color(0x0000));
        CreateJob.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(CreateJob);

        addTak = new JButton("Add Task");
        addTak.setBounds(450,600,300,45);
        addTak.setForeground(Color.white);
        addTak.setFocusPainted(false);
        addTak.setBackground(new Color(0x000));
        addTak.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(addTak);

        CreateJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJob();
            }
        });

        addTak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJobTask addJobTask = new AddJobTask();
                addJobTask.show();
                dispose();
            }
        });
    }

    private void addJob(){
        int id=Integer.parseInt(txtId.getText());
        String jobName = txtjob.getText();
        int numOfTask = Integer.parseInt(txtNumTask.getText());
        int dead=Integer.parseInt(txtTime.getText());
        int profit = Integer.parseInt(txtprofit.getText());

        if(id==0||jobName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please Enter All Field","Try again",JOptionPane.ERROR_MESSAGE);
        }else{
            JobModel jobModel = new JobModel(id, jobName,numOfTask,dead,profit);
            TaskController taskController = new TaskController();
            int insert = taskController.addJob(jobModel);
            if(insert>0){
                JOptionPane.showMessageDialog(null, "Job Created Successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Failed to create job");
            }
        }
    }
    public static void main(String[] args) {
        new CreateJob().setVisible(true);
    }
}