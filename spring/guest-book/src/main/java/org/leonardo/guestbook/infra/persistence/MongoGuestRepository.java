package org.leonardo.guestbook.infra.persistence;

import org.leonardo.guestbook.domain.Guest;
import org.leonardo.guestbook.domain.GuestRepository;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public interface MongoGuestRepository extends GuestRepository {

    @Cacheable("guest")
    Optional<Guest> findById(String id);

}
