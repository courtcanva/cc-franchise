package com.courtcanva.ccfranchise.utils;

import lombok.RequiredArgsConstructor;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component("adaptableJobFactory")
@RequiredArgsConstructor
public class AdaptableJobFactory extends org.springframework.scheduling.quartz.AdaptableJobFactory {
    private final AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object obj = super.createJobInstance(bundle);
        this.autowireCapableBeanFactory.autowireBean(obj);
        return obj;
    }
}
