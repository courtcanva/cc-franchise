package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.configs.MailingConfig;
import com.courtcanva.ccfranchise.utils.MailingClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void shouldSendVerificationEmail() {
        doNothing().when(mailingClient).sendEmail(any(), any(), any(), any());
        mailingService.sendVerificationEmail("some@one.com", "random generated token");

        verify(mailingClient, times(1)).sendEmail(any(), any(), any(), any());
    }
}
