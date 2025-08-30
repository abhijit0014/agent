package project.Agent.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.Agent.dto.CornJob;
import project.Agent.dto.JobEntry;
import project.Agent.service.CornJobService;
import project.Agent.task.TaskInterface;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TaskManager {

    private int poolSize = 3;
    private LinkedBlockingQueue<TaskInterface> taskQueue;
    private ExecutorService executor;
    private CornJobService cornJobService;
    private JobList jobList;

    public TaskManager(CornJobService cornJobService, JobList jobList) {
        this.cornJobService = cornJobService;
        this.jobList = jobList;
        //this.poolSize = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(poolSize);
        taskQueue = new LinkedBlockingQueue<>();
    }

    public synchronized int availableTaskSlot(){
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
        return (poolSize - threadPoolExecutor.getActiveCount()) - taskQueue.size();
    }




    // -------------- job discovery schedular -----------------

    @Scheduled(fixedDelay = 10000)
    public void engHourlyLoadJDS() throws InterruptedException {

        for (JobEntry jobEntry : jobList.getList()) {

            int freeSlot = this.availableTaskSlot();
            System.out.println("Available task slot "+ freeSlot);

            if (freeSlot >0){
                List<CornJob> newjobs = cornJobService.getNewJobApi(jobEntry, freeSlot);
                for (CornJob cornJob : newjobs){
                    System.out.println("Submitted "+jobEntry.getName()+" "+cornJob.getName());
                    taskQueue.add(jobEntry.createNewTask(cornJob));
                }
            }
        }
    }


    // ---------------------Task schedular ---------------------------
    @Scheduled(fixedDelay = 1000)
    public void taskSchedular() throws InterruptedException {

        while (!taskQueue.isEmpty()){
            executor.submit((Runnable) taskQueue.take());
        }
    }

}
