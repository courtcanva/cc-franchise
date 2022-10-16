package com.courtcanva.ccfranchise.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class MailingConfig {
    @Value("${cloud-resources.aws.region}")
    private String region;
    @Value("${cloud-resources.aws.ses.sender}")
    private String sender;
    @Value("${cloud-resources.aws.ses.client-side-base-url}")
    private String clientSideBaseUrl;
    @Value("${cloud-resources.aws.ses.access-key}")
    private String accessKey;
    @Value("${cloud-resources.aws.ses.secret-key}")
    private String secretKey;
}
