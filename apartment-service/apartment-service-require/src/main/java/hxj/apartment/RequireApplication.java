package hxj.apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author HXJ
 * @create 2021-10-20 19:54
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("hxj.apartment.dao")
public class RequireApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequireApplication.class,args);
    }
}