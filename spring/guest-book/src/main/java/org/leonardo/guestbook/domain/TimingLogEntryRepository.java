package org.leonardo.guestbook.domain;

import java.util.List;

public interface TimingLogEntryRepository {
    TimingLogEntry save(TimingLogEntry timingLogEntry);
    List<TimingLogEntry> findAll();
}
