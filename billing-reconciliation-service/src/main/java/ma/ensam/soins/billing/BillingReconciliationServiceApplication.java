package ma.ensam.soins.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BillingReconciliationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingReconciliationServiceApplication.class, args);
    }
}
