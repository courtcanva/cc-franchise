package com.courtcanva.ccfranchise.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.courtcanva.ccfranchise.configs.MailingConfig;
import org.springframework.stereotype.Component;

@Component
public class MailingClient {
    private final MailingConfig mailingConfig;
    private final AmazonSimpleEmailService sesClient;

    public MailingClient(MailingConfig mailingConfig) {
        this.mailingConfig = mailingConfig;
        sesClient = generateSESClient();
    }

    public SendEmailResult sendEmail(SendEmailRequest request) {
        return sesClient.sendEmail(request);
    }

    private AmazonSimpleEmailService generateSESClient() {
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(getAwsCredential())
                .withRegion(mailingConfig.getRegion())
                .build();
    }

    private AWSCredentialsProvider getAwsCredential() {
        AWSCredentials sesCredential = new BasicAWSCredentials(mailingConfig.getAccessKey(), mailingConfig.getSecretKey());
        return new AWSStaticCredentialsProvider(sesCredential);
    }

}
