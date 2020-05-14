package com.zcl.qa.client;

import com.zcl.qa.client.impl.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "crmm-base",fallback = BaseClientImpl.class)
public interface BaseClient {
    @RequestMapping(value = "/label/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId );
}
