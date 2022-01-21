package hxj.apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author HXJ
 * @create 2022-01-28 14:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("hxj.apartment.dao")
public class publishApplication {
    public static void main(String[] args) {
        SpringApplication.run(publishApplication.class, args);
    }
}
