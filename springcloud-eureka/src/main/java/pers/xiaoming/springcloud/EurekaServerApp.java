package pers.xiaoming.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // EurekaServer Server start class, use this server to register other services
public class EurekaServerApp {
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
