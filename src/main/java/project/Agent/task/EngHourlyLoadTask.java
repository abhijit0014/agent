package project.Agent.task;

import project.Agent.dto.CornJob;

public class EngHourlyLoadTask implements Runnable, TaskInterface{

    private CornJob cornJob;
    public void setTask(CornJob cornJob) {
        this.cornJob = cornJob;
    }

    @Override
    public void run() {
        Long sleepCount = Long.parseLong(cornJob.getName().split("-")[1]);
        try {
            System.out.println("START : EngHourlyLoadTask running "+cornJob.getName());
            Thread.sleep(sleepCount);
            System.out.println("END : EngHourlyLoadTask running "+cornJob.getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
