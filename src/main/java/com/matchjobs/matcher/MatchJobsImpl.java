package com.matchjobs.matcher;

import com.matchjobs.beans.Job;
import com.matchjobs.beans.Worker;
import com.matchjobs.service.MatchJobsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.matchjobs.utils.DistanceCalculator.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Matches Jobs for a worker Implementation
 */
@Component
public class MatchJobsImpl implements MatchJobs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchJobsService.class);

    @Override
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker) {

        List<Job> matchedJobs = new ArrayList<>();

        if (!worker.isActive()) {
        		LOGGER.info("Worker is not active.");
        		return matchedJobs;
        }

        // Filter based on Driving License, certificate and skills required for the job
        matchedJobs = jobs.stream()
        		.filter(p -> p.isDriverLicenseRequired() == worker.isHasDriversLicense())
        		.filter(p -> worker.getCertificates().containsAll(p.getRequiredCertificates()))
        		.filter(p -> worker.getSkills().contains(p.getJobTitle()))
        		.collect(Collectors.toList());
        
        //Filter based on distance
        matchedJobs = matchedJobs.stream().filter(p -> distance(
                worker.getJobSearchAddress().getLatitude(),
                p.getLocation().getLatitude(),
                worker.getJobSearchAddress().getLongitude(),
                p.getLocation().getLongitude(),
                worker.getJobSearchAddress().getUnit()) <= worker.getJobSearchAddress().getMaxJobDistance())
                .collect(Collectors.toList());

        return matchedJobs;
    }
    
}
