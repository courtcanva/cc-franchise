package com.courtcanva.ccfranchise.services;

import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.utils.MailingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailingClient mailingClient;
    private final MailingConfig mailingConfig;

    public void sendVerificationEmail(String to, String verificationToken) {
        String from = mailingConfig.getSender();
        String subject = "Verify your CourtCanva credential!";
        String verificationUrl = mailingConfig.getClientSideBaseUrl() + "/verify-email/" + verificationToken;
        String content = "<a target=\"_blank\" href=\"" + verificationUrl + "\">" + verificationUrl + "</a>";

        try {
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

            SendEmailResult result = mailingClient.sendEmail(sendEmailRequest);
            log.info("Verification email sent, operation finished with id " + result.getMessageId());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }


}
