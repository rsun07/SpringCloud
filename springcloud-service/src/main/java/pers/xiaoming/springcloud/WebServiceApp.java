package pers.xiaoming.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class WebServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApp.class, args);
    }
}
