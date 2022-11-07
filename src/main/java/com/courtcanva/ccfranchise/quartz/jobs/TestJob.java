package com.courtcanva.ccfranchise.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
    public void test1() throws InterruptedException {
        System.out.println("test scheduler1 is running");
    }
}
