package com.matchjobs.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.matchjobs.beans.Job;

/**
 * Integration class for the Job Service API.
 */
@Component
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);
    
    private static String JOBS_URL = "http://test.swipejobs.com/api/jobs";

    private RestTemplate restTemplate = new RestTemplate();

    /**
     *  Get All Joba
     * @return Jobs
     */
    public Set<Job> getJobs() {
        try {
            ResponseEntity<Job[]> jobResponse = restTemplate.getForEntity(JOBS_URL, Job[].class);

            if (HttpStatus.OK != jobResponse.getStatusCode()) {
                LOGGER.error("Invalid Response received from jobs api with status code as : ", jobResponse.getStatusCode());
                return null;
            }
            return Arrays.stream(jobResponse.getBody()).collect(Collectors.toSet());
       
        } catch (Exception e) {
        		LOGGER.error("Exception while trying to get Workers list",e);
        		return null;
        }
        
    }
}
