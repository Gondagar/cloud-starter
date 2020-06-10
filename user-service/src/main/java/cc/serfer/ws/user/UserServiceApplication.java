package cc.serfer.ws.user;

//import cc.serfer.ws.user.shared.FeignErrorDecoder;
//import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
//@EnableCircuitBreaker
@Slf4j
public class UserServiceApplication {

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

   /* @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }*/

/*
    @Bean
    public FeignErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }

   @Bean
    @Profile("production")
    public String createProductionBean(){
        log.info("Production bean created ({})", environment.getProperty("env.profile"));
        return  "Production bean";
    }

    @Bean
    @Profile("!production")
    public String createNotProductionBean(){
        log.info("Not Production bean created ({})", environment.getProperty("env.profile"));

        return  "Not production bean";
    }


    @Bean
    @Profile("default")
    public String createDevelopmentBean(){
        log.info("Development bean created ({})", environment.getProperty("env.profile"));
        return  "Development bean";
    }*/

}
