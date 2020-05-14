package com.zcl.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "crmm")
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("crmm："+msg);
    }

}
