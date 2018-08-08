package pers.xiaoming.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
public class WebServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApp.class, args);
    }
}
