package fr.passpar2.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.passpar2.api")
public class Passpar2ApiApplication {
    public Passpar2ApiApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Passpar2ApiApplication.class, args);
    }
}
