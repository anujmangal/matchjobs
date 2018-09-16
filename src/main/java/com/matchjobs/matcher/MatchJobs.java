package com.matchjobs.matcher;

import java.util.List;
import java.util.Set;

import com.matchjobs.beans.Job;
import com.matchjobs.beans.Worker;

public interface MatchJobs {

    /**
     * Matches Jobs for a worker
     * @param jobs
     * @param worker
     * @return
     */
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker);

}
