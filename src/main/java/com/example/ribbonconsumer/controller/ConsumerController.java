package com.example.ribbonconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {


    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hystrixFallback")
    @RequestMapping("consumer")
    public String consumer() {

        System.out.println("This is Consumer(服务消费者)!");
        return restTemplate.getForEntity("http://SERVICE-PROVIDER/provider", String.class).getBody();
    }




    public String hystrixFallback() {

        return "hystrixFallback: ERROR";
    }

}
