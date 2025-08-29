package com.bajajfinserv.webhooksolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebhookSolutionApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebhookSolutionApplication.class);
    
    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(WebhookSolutionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting Bajaj Finserv Health Java Qualifier Solution...");
        
        try {
            // Execute the complete flow
            webhookService.executeWebhookFlow();
            logger.info("Webhook solution completed successfully!");
        } catch (Exception e) {
            logger.error("Error executing webhook flow: ", e);
        }
    }
}
