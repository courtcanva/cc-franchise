package com.courtcanva.ccfranchise.utils;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.stereotype.Component;

@Component("adaptableJobFactory")
@RequiredArgsConstructor
public class AdaptableJobFactory extends org.springframework.scheduling.quartz.AdaptableJobFactory{

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        try {
            Object jobObject = createJobInstance(bundle);
            return adaptJob(jobObject);
        }
        catch (Exception ex) {
            throw new SchedulerException("Job instantiation failed", ex);
        }
    }


}
