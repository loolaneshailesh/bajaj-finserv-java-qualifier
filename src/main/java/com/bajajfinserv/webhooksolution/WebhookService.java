package com.bajajfinserv.webhooksolution;

import com.bajajfinserv.webhooksolution.dto.WebhookRequest;
import com.bajajfinserv.webhooksolution.dto.WebhookResponse;
import com.bajajfinserv.webhooksolution.dto.SolutionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String SUBMIT_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SqlSolutionService sqlSolutionService;

    @Autowired
    private ObjectMapper objectMapper;

    public void executeWebhookFlow() throws Exception {
        // Step 1: Generate webhook
        WebhookResponse webhookResponse = generateWebhook();
        logger.info("Generated webhook successfully");

        // Step 2: Solve SQL problem based on regNo
        String finalQuery = sqlSolutionService.solveSqlProblem("REG12347");
        logger.info("Generated SQL solution: {}", finalQuery);

        // Step 3: Submit solution
        submitSolution(finalQuery, webhookResponse.getAccessToken());
        logger.info("Solution submitted successfully");
    }

    private WebhookResponse generateWebhook() throws Exception {
        WebhookRequest request = new WebhookRequest();
        request.setName("John Doe");
        request.setRegNo("REG12347");
        request.setEmail("john@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                GENERATE_WEBHOOK_URL, entity, WebhookResponse.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to generate webhook: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error generating webhook: ", e);
            throw e;
        }
    }

    private void submitSolution(String finalQuery, String accessToken) throws Exception {
        SolutionRequest request = new SolutionRequest();
        request.setFinalQuery(finalQuery);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                SUBMIT_WEBHOOK_URL, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Solution submitted successfully with status: {}", response.getStatusCode());
            } else {
                throw new RuntimeException("Failed to submit solution: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error submitting solution: ", e);
            throw e;
        }
    }
}
