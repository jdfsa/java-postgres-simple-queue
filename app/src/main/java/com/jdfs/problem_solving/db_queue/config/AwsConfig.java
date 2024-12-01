package com.jdfs.problem_solving.db_queue.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AwsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.custom-endpoint:}")
    private String customEndpoint;

    @Bean
    public AmazonSQS sqsClient(){
        final var builder = AmazonSQSClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance());
        if (!StringUtils.isEmpty(customEndpoint)) {
            builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(customEndpoint, region));
        }
        else {
            builder.withRegion(Regions.fromName(region));
        }
        return builder.build();
    }
}
