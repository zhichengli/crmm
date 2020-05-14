package com.zcl.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "zcl")
public class Customer1 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("zcl消费消息："+msg);
    }

}
