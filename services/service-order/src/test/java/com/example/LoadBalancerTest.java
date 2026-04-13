package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void test() {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        System.out.println("choose.getHost() = " + choose.getHost());
    }




    public static void main(String[] args) {
        String a = "aaaa";
        String b = new String("aaaa");
        Integer c = 100;
        Integer d = 300;
        Integer e = 100;
        Integer f = 300;
        System.out.println(c==e);
        System.out.println(d==f);
        System.out.println(b=="aaaa");
        System.out.println(a=="aaaa");
    }
}
