package com.srv.apiconsume.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.GetRestApisRequest;
import software.amazon.awssdk.services.apigateway.model.GetRestApisResponse;
import software.amazon.awssdk.services.apigateway.model.RestApi;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;

@Service
public class ApiGatewayConfigService implements ApiGatewayConfig {

    private final ApiGatewayClient apiGatewayClient;

    // Constructor to initialize the API Gateway client
    public ApiGatewayConfigService() {
        this.apiGatewayClient = ApiGatewayClient.builder()
                .region(Region.AP_SOUTH_1) // Set your AWS region here
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        "AKIAZPPF7UW7JHBSIYDZ",
                        "XxSTrfL8uVZJaZYndPW8sol+QflpatsQYXf0unOG")))
                .build();
    }

    // Method to retrieve and display API Gateway configurations
    @Override
    public void fetchApiGatewayConfigurations() {
        try {
            // Create a request to fetch REST APIs
            GetRestApisRequest request = GetRestApisRequest.builder().build();

            // Get the response with the list of APIs
            GetRestApisResponse response = apiGatewayClient.getRestApis(request);

            // Loop through the APIs and print their details
            for (RestApi api : response.items()) {
                System.out.println("API ID: " + api.id());
                System.out.println("API Name: " + api.name());
                System.out.println("API Description: " + api.description());
                System.out.println("Created Date: " + api.createdDate());
                System.out.println("Endpoint Configuration: " + api.endpointConfiguration());
                System.out.println("Endpoint Configuration: " + api.apiKeySource().name());
                System.out.println("-----------------------------------------");
            }
        } catch (ApiGatewayException e) {
            System.err.println("Error occurred: " + e.awsErrorDetails().errorMessage());
        }
    }
}
