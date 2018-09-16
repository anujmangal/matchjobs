package com.matchjobs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class currently do direct matching with the integration services.
 * Not mocking the integration service as of now.
 * Ideally should construct the expected response object and check against that. But for now just checking hardcoded
 * json responses.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MatchJobControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMatchingJobs_NoResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/jobs/search/1111111").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void getMatchingJobs_inactiveWorker() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/jobs/search/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
    
 
    @Test
    public void getMatchingJobs_ValidResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/jobs/search/8").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"driverLicenseRequired\":true,\"requiredCertificates\":[\"Outstanding Memory Award\",\"Calm in the Eye of the Storm\",\"Marvelous Multitasker\"],\"location\":{\"longitude\":\"14.312687\",\"latitude\":\"49.828395\"},\"billRate\":\"$7.47\",\"workersRequired\":4,\"startDate\":\"2015-11-24T07:35:25.451+0000\",\"about\":\"Exercitation sint est et fugiat et. Consequat pariatur cupidatat exercitation aliqua dolor pariatur labore aliquip quis irure irure in laboris cillum. Veniam fugiat quis do laborum est exercitation voluptate dolore anim nulla deserunt. Adipisicing dolore culpa commodo aute qui irure ad id exercitation tempor. In in pariatur laborum irure irure. Ut dolor incididunt consectetur quis id fugiat elit sint proident nostrud culpa enim consectetur.\",\"jobTitle\":\"Chief Cheerleader\",\"company\":\"Pholio\",\"guid\":\"562f66aa89c9c662fa538fb7\",\"jobId\":\"24\"},{\"driverLicenseRequired\":true,\"requiredCertificates\":[\"Office Lunch Expert\"],\"location\":{\"longitude\":\"14.293204\",\"latitude\":\"50.266116\"},\"billRate\":\"$6.21\",\"workersRequired\":5,\"startDate\":\"2015-11-02T22:12:40.263+0000\",\"about\":\"Labore tempor aliqua occaecat eiusmod deserunt incididunt qui voluptate laboris fugiat laborum sint eu. In ad ut dolor occaecat amet deserunt pariatur ea fugiat occaecat. Elit est qui quis irure Lorem ullamco Lorem nisi anim cupidatat nostrud qui proident.\",\"jobTitle\":\"The Resinator\",\"company\":\"Lovepad\",\"guid\":\"562f66aa7383f3a5241674c8\",\"jobId\":\"11\"},{\"driverLicenseRequired\":true,\"requiredCertificates\":[\"Healthy Living Promoter\"],\"location\":{\"longitude\":\"14.580436\",\"latitude\":\"49.886497\"},\"billRate\":\"$15.83\",\"workersRequired\":4,\"startDate\":\"2015-11-15T11:23:34.310+0000\",\"about\":\"Eu duis ad nisi pariatur ut Lorem exercitation nulla veniam mollit commodo. Cillum in eu commodo incididunt dolore irure. Pariatur velit sit aliquip ea duis duis consectetur ullamco elit exercitation. Sint in aliqua amet eu non esse dolore aliquip esse. Aute aliquip reprehenderit ullamco qui deserunt. Minim reprehenderit excepteur laborum irure id anim dolor nulla magna ea culpa fugiat consequat. Incididunt consequat ex laborum occaecat nulla irure eu esse nostrud ut qui sunt laboris adipisicing.\",\"jobTitle\":\"Chief Cheerleader\",\"company\":\"Syntac\",\"guid\":\"562f66aa1ceec2fb3e8bb3a0\",\"jobId\":\"14\"}]"));
    }
}
