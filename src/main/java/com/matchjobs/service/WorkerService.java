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

import com.matchjobs.beans.Worker;

/**
 * Integration class for Worker service
 */
@Component
public class WorkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);

    private static String WORKERS_URL = "http://test.swipejobs.com/api/workers";

    private RestTemplate restTemplate = new RestTemplate();

    /**
     *  Get All Workers
     * @return Workers
     */
    public Set<Worker> getWorkers() {

        try {
            ResponseEntity<Worker[]> workersResponse = restTemplate.getForEntity(WORKERS_URL, Worker[].class);

            if (HttpStatus.OK != workersResponse.getStatusCode()) {
                LOGGER.error("Invalid Response received from workers api with status code as : ", workersResponse.getStatusCode());
                return null;
            }
            
            return Arrays.stream(workersResponse.getBody()).collect(Collectors.toSet());
            
        }catch (Exception e){
        		LOGGER.error("Error while trying to get Workers list",e);
        		return null;
        }
    }
}
