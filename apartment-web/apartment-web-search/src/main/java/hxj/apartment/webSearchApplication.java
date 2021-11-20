package hxj.apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author HXJ
 * @create 2021-11-22 18:30
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class webSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(webSearchApplication.class,args);
    }
}
