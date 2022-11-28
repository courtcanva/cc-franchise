package com.courtcanva.ccfranchise.quartz.config;

import com.courtcanva.ccfranchise.quartz.jobs.RejectOrderJob;
import com.courtcanva.ccfranchise.quartz.jobs.AssignOrderJob;
import com.courtcanva.ccfranchise.utils.QuartzAdaptableJobFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@DisallowConcurrentExecution
public class QuartzConfig {
    @Bean(name = "rejectOrder")
    public MethodInvokingJobDetailFactoryBean invokeRejectOrder(RejectOrderJob rejectOrderJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(rejectOrderJob);
        jobDetail.setTargetMethod("rejectAllExpriedOrders");
        return jobDetail;
    }

    @Bean(name = "rejectOrderTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean1(JobDetail rejectOrder) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(rejectOrder);
        factory.setCronExpression("* 0/1 * * * ?");
        return factory;
    }

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
                                                      Trigger rejectOrderTrigger,
                                                      QuartzAdaptableJobFactory quartzAdaptableJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setTriggers(assignOrderTrigger, rejectOrderTrigger);
        factory.setJobFactory(quartzAdaptableJobFactory);
        return factory;
    }
}
