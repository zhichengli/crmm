package com.zcl.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.zcl.sms.utils.ALISMS;
import com.zcl.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {


    @RabbitHandler
    public void executeSms(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        System.out.println("手机号："+ map.get("mobile"));
        System.out.println("验证码："+ map.get("checkcode"));
        ALISMS.sendSMS(mobile,checkcode);
    }

}
