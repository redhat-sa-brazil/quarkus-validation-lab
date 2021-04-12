package org.leonardo.guestbook.usecases;

import org.leonardo.guestbook.domain.EventSource;
import org.leonardo.guestbook.domain.Guest;
import org.leonardo.guestbook.domain.GuestRepository;

public class RegisterGuest {
    private final GuestRepository guestRepository;
    private final EventSource eventSource;

    public RegisterGuest(
        GuestRepository guestRepository,
        EventSource eventSource) {
        this.guestRepository = guestRepository;
        this.eventSource = eventSource;
    }

    public Guest execute(Guest guest) {
        Guest result = guestRepository.save(guest);
        eventSource.emitGuestRegistered(result.getId());
        return result;
    }
}
