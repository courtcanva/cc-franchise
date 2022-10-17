package com.courtcanva.ccfranchise.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailingClient {
    private final MailingConfig mailingConfig;
    private final AmazonSimpleEmailService sesClient;

    public MailingClient(MailingConfig mailingConfig) {
        this.mailingConfig = mailingConfig;
        sesClient = generateSESClient();
    }

    public void sendEmail(String from, String to, String subject, String content) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(
                        new Message()
                                .withSubject(new Content().withCharset("UTF-8").withData(subject))
                                .withBody(
                                        new Body()
                                                .withHtml(new Content()
                                                        .withCharset("UTF-8")
                                                        .withData(content))
                                )
                ).withSource(from);

        try {
            SendEmailResult result = sesClient.sendEmail(sendEmailRequest);
            log.info("Send email request completed with result of " + result.getMessageId());
        } catch (Exception ex) {
            throw new MailingClientException("Failed to send email via AWS SES. (" + ex.getMessage() + ")");
        }
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
