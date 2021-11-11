package hxj.apartment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import util.SnowflakeIdWorker;
/**
 * @author HXJ
 * @create 2021-10-20 19:47
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("hxj.apartment.dao")
public class GoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodApplication.class, args);
    }

    @Value("${snowflake.conf.workerId}")
    private Integer wokerId;

    @Value("${snowflake.conf.datacenterId}")
    private Integer datacenterId;

    @Bean(name = "idWorker")
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(wokerId, datacenterId);
    }
}
