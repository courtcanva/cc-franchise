package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.utils.MailingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailingClient mailingClient;
    private final MailingConfig mailingConfig;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendVerificationEmail(String to, String verificationToken) throws MailingClientException {
        String from = mailingConfig.getSender();
        String subject = "Verify your CourtCanva credential!";
        String verificationUrl = mailingConfig.getClientSideBaseUrl() + "/staff/verify-email?token=" + verificationToken + "&i=" + Base64.encodeAsString(to.getBytes());
        String content = "<a target=\"_blank\" href=\"" + verificationUrl + "\">" + verificationUrl + "</a>";

        mailingClient.sendEmail(from, to, subject, content);
    }
}
