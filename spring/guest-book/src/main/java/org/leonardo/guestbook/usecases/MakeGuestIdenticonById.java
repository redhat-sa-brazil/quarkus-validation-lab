package org.leonardo.guestbook.usecases;

import org.leonardo.guestbook.domain.Guest;
import org.leonardo.guestbook.domain.GuestRepository;
import org.leonardo.guestbook.domain.IdenticonProvider;
import org.leonardo.guestbook.domain.IdenticonProvisionError;

import java.util.Optional;

public class MakeGuestIdenticonById {
    private final GuestRepository guestRepository;
    private final IdenticonProvider identiconProvider;

    public MakeGuestIdenticonById(
        GuestRepository guestRepository,
        IdenticonProvider identiconProvider) {

        this.guestRepository = guestRepository;
        this.identiconProvider = identiconProvider;
    }

    public void execute(String guestId) throws IdenticonProvisionError {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isPresent()) {
            guestRepository.save(guest.get().updateIdenticon(identiconProvider));
        }
    }
}
