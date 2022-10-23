package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.utils.MailingClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MailingServiceTest {
    @Mock
    private MailingClient mailingClient;
    @Mock
    private MailingConfig mailingConfig;
    private MailingService mailingService;

    @BeforeEach
    public void setUpMailingService() {
        mailingService = new MailingService(mailingClient, mailingConfig);
    }

    @Test
    void givenEmailAndVerificationToken_whenSendVerificationEmail_shouldNotThrowError() throws MailingClientException {
        when(mailingConfig.getSender()).thenReturn("sender@email.com");
        mailingService.sendVerificationEmail("some@one.com", "random generated token");

        verify(mailingClient, times(1)).sendEmail(eq("sender@email.com"), eq("some@one.com"), eq("Verify your CourtCanva credential!"), any());
    }
}
