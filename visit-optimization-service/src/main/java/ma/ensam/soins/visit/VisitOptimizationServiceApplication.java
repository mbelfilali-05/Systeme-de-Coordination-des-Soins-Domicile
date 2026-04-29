package ma.ensam.soins.visit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VisitOptimizationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisitOptimizationServiceApplication.class, args);
    }
}
