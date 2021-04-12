package org.leonardo.guestbook.shell.http.inbound;

import org.leonardo.guestbook.domain.EventSource;
import org.leonardo.guestbook.domain.GuestRepository;
import org.leonardo.guestbook.usecases.RegisterGuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpInboundConfiguration {

    @Bean
    public RegisterGuest registerGuest(
        GuestRepository guestRepository, EventSource eventSource) {
        return new RegisterGuest(guestRepository, eventSource);
    }
}
