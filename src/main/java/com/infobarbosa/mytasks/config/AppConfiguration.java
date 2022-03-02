package com.infobarbosa.mytasks.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    
    @Value("${dynamodb.endpoint}")
    private String endpoint;
    @Value("${dynamodb.region}")
    private String region;
    @Value("${dynamodb.accessKey}")
    private String accessKey;
    @Value("${dynamodb.secretKey}")
    private String secretKey;

    @Bean
    public DynamoDBMapper mapper(){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBConfig());
        
        ListTablesRequest request;
        
        return mapper;
    }

    private AmazonDynamoDB amazonDynamoDBConfig() {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration( new AwsClientBuilder.EndpointConfiguration(endpoint, region) )
            .withCredentials( new AWSStaticCredentialsProvider( new BasicAWSCredentials(accessKey, secretKey) ))
            .build();
    }

}
