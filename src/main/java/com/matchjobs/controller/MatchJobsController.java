package com.matchjobs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matchjobs.beans.Job;
import com.matchjobs.service.MatchJobsService;

@RestController
public class MatchJobsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchJobsController.class);

    @Autowired
    private MatchJobsService matchJobsService;

    /**
     * Find out the best matching jobs for a given worker.
     * @param workerId
     * @return List of matched jobs
     */
    @RequestMapping(value = "/jobs/search/{workerId}", method = RequestMethod.GET)
    List<Job> getMatchingJobs(@PathVariable String workerId) {

        try {
            List<Job> jobs = matchJobsService.getMatchedJobs(workerId);
            LOGGER.info("Found {} matching jobs for workerId={}",jobs.size(),workerId);
            return jobs;
        }catch (Exception ex){
            LOGGER.error("Error occurred while matching jobs.",ex);
            return new ArrayList<>();
        }
    }
}
