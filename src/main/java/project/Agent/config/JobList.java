package project.Agent.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.Agent.Utility.IntervalUnit;
import project.Agent.Utility.Job;
import project.Agent.dto.JobEntry;
import project.Agent.task.EngHourlyLoadTask;
import project.Agent.task.SubHourlyLoadTask;

import java.util.List;

@Component
public class JobList {

    @Value("${agent.name}")
    private String agentName;
    private List<JobEntry> list;


    @PostConstruct
    public void updateJobList() {

        list = List.of(

                new JobEntry(Job.ENG_HOURLY_LOAD)
                        .agent(agentName)
                        .intervalUnit(IntervalUnit.Hourly)
                        .jobParts(List.of("1","2","3","4","5"))
                        .task(EngHourlyLoadTask.class),

                new JobEntry(Job.SUB_HOURLY_LOAD)
                        .agent(agentName)
                        .intervalUnit(IntervalUnit.Hourly)
                        .jobParts(List.of("1","2","3","4","5"))
                        .task(SubHourlyLoadTask.class)
        );
    }

    public List<JobEntry> getList(){
        return list;
    }

}
