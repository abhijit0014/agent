package project.Agent.service;

import org.springframework.stereotype.Service;
import project.Agent.dto.CornJob;
import project.Agent.dto.JobEntry;
import java.util.List;

@Service
public class CronJobService {

    public synchronized List<CornJob> getNewJobApi(JobEntry jobEntry, int limit) {
        return List.of(new CornJob(),new CornJob(),new CornJob(),new CornJob())
                .stream().limit(limit).toList();
    }
}
