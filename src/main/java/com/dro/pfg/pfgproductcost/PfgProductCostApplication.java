package com.dro.pfg.pfgproductcost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PfgProductCostApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfgProductCostApplication.class, args);
    }

}
