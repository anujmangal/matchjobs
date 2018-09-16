package com.matchjobs.service;

import com.matchjobs.beans.Job;
import com.matchjobs.beans.Worker;
import com.matchjobs.matcher.MatchJobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MatchJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchJobsService.class);
    
    @Autowired
    private MatchJobs matchJobService;
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private WorkerService workerService;
    
    private static final Integer MAX_JOB_RESULTS = 3;
    
    /**
     * Currently using {@link com.matchjobs.matcher.MatchJobsImpl} to find the best matching jobs.
     * @param workerId
     * @return List of matched jobs
     */
    public List<Job> getMatchedJobs(String workerId) {

        List<Job> matchedJobs = new ArrayList<>();
        Set<Worker> workers = workerService.getWorkers();
        Worker worker = new Worker(workerId);
        if (workers == null || workers.isEmpty() || !workers.contains(worker)) {
            LOGGER.error("Worker wih id {} not available", workerId);
        } else {
            Set<Job> jobs = jobService.getJobs();
            if (jobs == null || jobs.isEmpty()) {
                LOGGER.error("No Jobs found");
                
            } else {
                matchedJobs = matchJobService.getMatchingJobs(
                        jobs,
                        workers.stream().filter(w -> w.getUserId().equals(workerId)).findFirst().get());

                // only show 3 results
                if(matchedJobs.size() > MAX_JOB_RESULTS){
                    matchedJobs.subList(MAX_JOB_RESULTS, matchedJobs.size()).clear();
                }
            }
        }
        return matchedJobs;
    }
}
