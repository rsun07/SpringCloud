package pers.xiaoming.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class WebServerApp {
    public static void main(String[] args) {
        SpringApplication.run(WebServerApp.class, args);
    }
}
