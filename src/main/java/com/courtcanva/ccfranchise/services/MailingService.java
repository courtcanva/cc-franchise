package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.utils.MailingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    private static final String VERIFICATION_EMAIL_SUBJECT = "Verify your CourtCanva credential!";
    private static final String VERIFICATION_PAGE_URL = "/staff/verify-email";

    private final MailingClient mailingClient;
    private final MailingConfig mailingConfig;

    @Async
    public void sendVerificationEmail(String to, String verificationToken) {
        String from = mailingConfig.getSender();
        String verificationUrl = mailingConfig.getClientSideBaseUrl() + VERIFICATION_PAGE_URL + "?token=" + verificationToken + "&email=" + Base64.encodeAsString(to.getBytes());
        String content = "<a target=\"_blank\" href=\"" + verificationUrl + "\">" + verificationUrl + "</a>";

        mailingClient.sendEmail(from, to, VERIFICATION_EMAIL_SUBJECT, content);
    }
}
