package com.courtcanva.ccfranchise.quartz.config;

import com.courtcanva.ccfranchise.quartz.jobs.AssignOrderJob;
import com.courtcanva.ccfranchise.utils.QuartzAdaptableJobFactory;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean(name = "assignOrder")
    public MethodInvokingJobDetailFactoryBean invokeAssignOrder(AssignOrderJob assignOrderJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(assignOrderJob);
        jobDetail.setTargetMethod("assignOrders");
        return jobDetail;
    }


    @Bean(name = "assignOrderTrigger")
    public CronTriggerFactoryBean triggerAssignOrder(JobDetail assignOrder) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(assignOrder);
        factory.setCronExpression("0 0/5 * ? * MON-FRI");
        return factory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean1(Trigger assignOrderTrigger,
                                                      QuartzAdaptableJobFactory quartzAdaptableJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setTriggers(assignOrderTrigger);
        factory.setJobFactory(quartzAdaptableJobFactory);
        return factory;
    }
}