package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author HXJ
 * @create 2021-11-03 19:22
 */
@SpringBootApplication
@EnableEurekaClient
public class UserGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserGatewayApplication.class, args);
    }
}
