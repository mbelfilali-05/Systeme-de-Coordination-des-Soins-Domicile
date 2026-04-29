package ma.ensam.soins.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RealTimeTrackingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealTimeTrackingServiceApplication.class, args);
    }
}
