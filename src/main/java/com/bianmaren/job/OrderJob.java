package com.bianmaren.job;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by dengwenbing on 2015-12-29.
 */
@Component("orderJob")
@Lazy(false)
public class OrderJob {


    /**
     * 24小时之内没付款的订单自动取消
     */
    @Scheduled(cron = "${job.order.cancel}")
    public void cancelOrder() {

    }


}
