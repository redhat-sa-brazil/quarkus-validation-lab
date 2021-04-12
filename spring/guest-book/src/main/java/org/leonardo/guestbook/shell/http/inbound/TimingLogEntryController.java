package org.leonardo.guestbook.shell.http.inbound;

import org.leonardo.guestbook.domain.TimingLogEntry;
import org.leonardo.guestbook.domain.TimingLogEntryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timing-log-entries")
public class TimingLogEntryController {
    private final TimingLogEntryRepository timingLogEntryRepository;

    public TimingLogEntryController(
        TimingLogEntryRepository timingLogEntryRepository) {
        this.timingLogEntryRepository = timingLogEntryRepository;
    }

    @GetMapping
    public List<TimingLogEntry> getAllAccessLogEntries() {
        return timingLogEntryRepository.findAll();
    }
}
