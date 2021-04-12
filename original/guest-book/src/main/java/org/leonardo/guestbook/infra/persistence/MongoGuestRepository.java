package org.leonardo.guestbook.infra.persistence;

import org.leonardo.guestbook.domain.Guest;
import org.leonardo.guestbook.domain.GuestRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoGuestRepository
    extends GuestRepository, MongoRepository<Guest, String> {
    @Cacheable("guest")
    Optional<Guest> findById(String id);
}
