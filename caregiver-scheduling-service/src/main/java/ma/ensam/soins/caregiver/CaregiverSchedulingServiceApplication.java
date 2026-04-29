package ma.ensam.soins.caregiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CaregiverSchedulingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaregiverSchedulingServiceApplication.class, args);
    }
}
