package org.leonardo.guestbook.usecases;

import org.leonardo.guestbook.domain.guest.Guest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegisterGuest {

    public Guest execute(Guest guest) {
        Guest.persist(guest);
        return guest;
    }
}
