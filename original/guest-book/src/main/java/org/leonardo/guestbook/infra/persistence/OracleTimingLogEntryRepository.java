package org.leonardo.guestbook.infra.persistence;

import org.leonardo.guestbook.domain.TimingLogEntry;
import org.leonardo.guestbook.domain.TimingLogEntryRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;

public interface OracleTimingLogEntryRepository
    extends TimingLogEntryRepository, CrudRepository<TimingLogEntry, Instant> {
}
