package com.zcl.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "lmmb")
public class Customer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("lmmb："+msg);
    }

}
