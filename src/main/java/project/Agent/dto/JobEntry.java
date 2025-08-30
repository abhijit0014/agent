package project.Agent.dto;

import project.Agent.Utility.Job;
import project.Agent.Utility.IntervalUnit;
import project.Agent.task.TaskInterface;

import java.util.List;

public class JobEntry {
    private String name;
    private int interval;
    private IntervalUnit intervalUnit;
    private List<String> jobParts;
    private String agent;
    private String task;

    public JobEntry(Job name) {
        this.name = name.toString();
        this.intervalUnit = IntervalUnit.Daily;
    }

    public JobEntry intervalUnit(IntervalUnit interval) {
        this.intervalUnit = interval;
        return this;
    }

    public JobEntry jobParts(List<String> jobParts) {
        this.jobParts = jobParts;
        return this;
    }

    public JobEntry agent(String agent) {
        this.agent = agent;
        return this;
    }

    public String getAgent() {
        return agent;
    }

    public List<String> getJobParts() {
        return jobParts;
    }

    public String getName() {
        return name;
    }

    public IntervalUnit getJobInterval() {
        return intervalUnit;
    }

    public JobEntry task(Class<? extends TaskInterface> task) {
        this.task = task.getName();
        return this;
    }

    public TaskInterface createNewTask(CornJob cornJob) {

        try {
            Class<?> loadedClass = Class.forName(this.task);
            TaskInterface task = (TaskInterface) loadedClass.getDeclaredConstructor().newInstance();
            task.setTask(cornJob);
            return task;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
