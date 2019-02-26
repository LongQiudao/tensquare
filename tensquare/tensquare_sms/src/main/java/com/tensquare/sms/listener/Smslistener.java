package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 43967 on 2019/2/25.
 */

@Component
@RabbitListener(queues = "sms")
public class Smslistener {

    @RabbitHandler
    public void sendSms(Map<String,String> map) {
        System.out.println("手机号"+map.get("mobile"));
        System.out.println("验证码"+map.get("random"));
    }
}
