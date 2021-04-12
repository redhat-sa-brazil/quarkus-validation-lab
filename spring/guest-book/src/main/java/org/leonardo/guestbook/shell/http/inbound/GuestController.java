package org.leonardo.guestbook.shell.http.inbound;

import org.leonardo.guestbook.domain.Guest;
import org.leonardo.guestbook.domain.GuestRepository;
import org.leonardo.guestbook.domain.TimingLogEntry;
import org.leonardo.guestbook.domain.TimingLogEntryRepository;
import org.leonardo.guestbook.usecases.RegisterGuest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/guests")
public class GuestController {

    public static final String REGISTER_ACCESS_LOG_MSG =
        "Unable to register access log: {}";

    private static final Logger logger =
        LoggerFactory.getLogger(GuestController.class);

    private final GuestRepository guestRepository;
    private final TimingLogEntryRepository timingLogEntryRepository;
    private final RegisterGuest registerGuest;

    public GuestController(
        GuestRepository guestRepository,
        TimingLogEntryRepository timingLogEntryRepository,
        RegisterGuest registerGuest) {

        this.guestRepository = guestRepository;
        this.timingLogEntryRepository = timingLogEntryRepository;
        this.registerGuest = registerGuest;
    }

    @GetMapping
    public List<Guest> getAllGuests(HttpServletRequest request) {
        return runRecordingAccess(request, guestRepository::findAll);
    }

    @PostMapping
    public Guest registerGuest(
        @RequestBody Guest guest, HttpServletRequest request) {
        return runRecordingAccess(request, () -> registerGuest.execute(guest));
    }

    @GetMapping("/{id}")
    public Guest getGuestById(
        @PathVariable String id, HttpServletRequest request) {
        return runRecordingAccess(request, () ->
            guestRepository.findById(id).get());
    }

    private <O> O runRecordingAccess(
        HttpServletRequest request, Supplier<O> action) {
        Instant start = Instant.now();
        try {
            return action.get();
        } finally {
            Duration duration = Duration.between(start, Instant.now());
            registerAccess(request, start, duration);
        }
    }

    private void registerAccess(
        HttpServletRequest request, Instant start, Duration duration) {
        try {
            String method = request.getMethod();
            String path = request.getRequestURI();
            TimingLogEntry logEntry = new TimingLogEntry(
                start, duration.toMillis(), method, path);
            timingLogEntryRepository.save(logEntry);
        } catch (Exception e) {
            logger.error(REGISTER_ACCESS_LOG_MSG, e.getMessage());
        }
    }
}
