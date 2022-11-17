package com.courtcanva.ccfranchise.quartz.config;

import com.courtcanva.ccfranchise.quartz.jobs.AssignOrderJob;
import com.courtcanva.ccfranchise.quartz.jobs.OrderJob;
import com.courtcanva.ccfranchise.utils.AdaptableJobFactory;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {
    @Bean(name = "order1")
    public MethodInvokingJobDetailFactoryBean test1(OrderJob orderJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(orderJob);
        jobDetail.setTargetMethod("test1");
        return jobDetail;
    }

    @Bean(name = "assignOrder")
    public MethodInvokingJobDetailFactoryBean test2(AssignOrderJob assignOrderJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(assignOrderJob);
        jobDetail.setTargetMethod("assignOrders");
        return jobDetail;
    }

    @Bean(name = "order1_Trigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean1(JobDetail order1) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(order1);
        factory.setCronExpression("* 0/5 * * * ? *");
        return factory;
    }

    @Bean(name = "assignOrderTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean2(JobDetail assignOrder) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(assignOrder);
        factory.setCronExpression("* 0/5 * * * ? *");
        return factory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean1(Trigger order1_Trigger,
                                                      Trigger assignOrderTrigger,
                                                      AdaptableJobFactory adaptableJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setTriggers(order1_Trigger, assignOrderTrigger);
        factory.setJobFactory(adaptableJobFactory);
        return factory;
    }
}