package com.courtcanva.ccfranchise.quartz.jobs;

import com.courtcanva.ccfranchise.services.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {
    @Autowired
    private OrderService orderService;

    public void rejectAllExpriedOrders() throws InterruptedException {
        orderService.rejectAllExpriedOrders();
    }

    public void test2() throws InterruptedException {

    }
}
