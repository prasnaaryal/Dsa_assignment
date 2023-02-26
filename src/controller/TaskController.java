package controller;


import Database.DatabaseConnection;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    DatabaseConnection database = new DatabaseConnection();

    public int addTask(TaskModel taskModel){
        try{
            String query ="insert into tabletask(tasks,task_Id) values(?,?)";

            PreparedStatement st = database.connection.prepareStatement(query);
            st.setString(1,taskModel.getTask());
            st.setInt(2,taskModel.getTaskId());

            return database.manipulate(st);

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int addJob(JobModel jobModel){
        try{
            String query="insert into tableJob(job_Id, job_Name,job_Profit, deadline, num_OfTask) values(?,?,?,?,?)";

            PreparedStatement st= database.connection.prepareStatement(query);
            st.setInt(1, jobModel.getJobId());
            st.setString(2,jobModel.getJobName());
            st.setInt(3,jobModel.getProfit());
            st.setInt(4,jobModel.getTime());
            st.setInt(5,jobModel.getNumOfTask());

            return database.manipulate(st);
        }catch(SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }

    public ArrayList<Integer> fetchTask() {
        TaskModel taskModel = null;
        ArrayList<Integer> idList = new ArrayList<>();

        try {
            String query = "select * from tabletask";
            PreparedStatement ps = database.connection.prepareStatement(query);
            ResultSet resultSet = database.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("task_Id"));
                taskModel = new TaskModel();
                taskModel.setTaskId(resultSet.getInt("task_Id"));
                taskModel.setTask(resultSet.getString("tasks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }

    public int addJobTask(JobTaskModel jobTaskModel){
        try{
            String query="insert into tablJobtask(jobId, sourcetask, destinationtask) values(?,?,?)";
            PreparedStatement st= database.connection.prepareStatement(query);
            st.setInt(1,jobTaskModel.getJobId());
            st.setInt(2,jobTaskModel.getSource());
            st.setInt(3,jobTaskModel.getDestination());
            return database.manipulate(st);
        }catch(SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }

    public JobModel fetchJobBYId(Integer id){
        JobModel jobModel = null;

        try {
            String query = "select * from tableJob where job_Id=?";
            PreparedStatement ps = database.connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet resultSet = database.retrieve(ps);

            while (resultSet.next()) {

                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("job_Id"));
                jobModel.setJobName(resultSet.getString("job_Name"));
                jobModel.setNumOfTask(resultSet.getInt("num_OfTask"));
                jobModel.setTime(resultSet.getInt("deadline"));
                jobModel.setProfit(resultSet.getInt("job_Profit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobModel;
    }
    public ArrayList<JobModel> fetchJob() {
        JobModel jobModel = null;
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<JobModel> job = new ArrayList<>();

        try {
            String query = "select * from tableJob";
            PreparedStatement ps = database.connection.prepareStatement(query);
            ResultSet resultSet = database.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("job_Id"));
                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("job_Id"));
                jobModel.setJobName(resultSet.getString("job_Name"));
                jobModel.setNumOfTask(resultSet.getInt("num_OfTask"));
                jobModel.setProfit(resultSet.getInt("job_Profit"));
                jobModel.setTime(resultSet.getInt("deadline"));

                job.add(jobModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public ArrayList<JobTaskModel> fetchTaskModels(int jobId){
        ArrayList<JobTaskModel> list = new ArrayList<>();
        try{
            String query = "select * from tabljobtask where jobId=?";
            PreparedStatement ps = database.connection.prepareStatement(query);
            ps.setInt(1,jobId);
            ResultSet resultSet = database.retrieve(ps);

            while (resultSet.next()){
                JobTaskModel jtm = new JobTaskModel();
                jtm.setJobId(resultSet.getInt("jobId"));
                jtm.setSource(resultSet.getInt("sourcetask"));
                jtm.setDestination(resultSet.getInt("destinationtask"));
                list.add(jtm);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public TaskModel getTaskById(Integer id){
        TaskModel taskModel = new TaskModel();
        try{
            String query = "select * from tabletask where task_Id=?";
            PreparedStatement ps= database.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = database.retrieve(ps);
            while(resultSet.next()){
                taskModel.setTaskId(resultSet.getInt("task_Id"));
                taskModel.setTask(resultSet.getString("tasks"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return taskModel;
    }
    public List<JobModel> getAllJob(){
        ArrayList<JobModel> jobs= new ArrayList<>();

        try{
            String query="select * from tableJob";
            PreparedStatement ps = database.connection.prepareStatement(query);
            ResultSet resultSet = database.retrieve(ps);
            while (resultSet.next()){
                JobModel j = new JobModel();
                j.setJobId(resultSet.getInt("job_Id"));
                j.setJobName(resultSet.getString("job_Name"));
                j.setNumOfTask(resultSet.getInt("num_OfTask"));
                j.setProfit(resultSet.getInt("job_Profit"));
                j.setTime(resultSet.getInt("deadline"));
                jobs.add(j);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jobs;
    }



}
