package com.coffeecon.app.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CognitoConfig {

    @Value("${app.aws.access_key}")
    private String AWS_ACCESS_KEY;

    @Value("${app.aws.secret_key}")
    private String AWS_SECRET_KEY;

    @Value("${app.aws.region}")
    private  String AWS_REGION;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {
        BasicAWSCredentials creds = new BasicAWSCredentials(AWS_ACCESS_KEY,AWS_SECRET_KEY);
        return AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
                .withRegion("us-east-1")
                .build();
    }
}
