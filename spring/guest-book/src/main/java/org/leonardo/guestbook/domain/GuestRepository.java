package org.leonardo.guestbook.domain;

import java.util.List;
import java.util.Optional;

public interface GuestRepository {
    List<Guest> findAll();
    Optional<Guest> findById(String id);
    Guest save(Guest guest);
}
