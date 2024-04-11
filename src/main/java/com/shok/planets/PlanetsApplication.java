package com.shok.planets;



import com.shok.planets.properties.TencentOssProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.shok.planets.mapper")
@EnableScheduling
public class PlanetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanetsApplication.class, args);
    }

}
