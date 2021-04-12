package org.leonardo.guestbook.shell.http.inbound;

import org.bson.types.ObjectId;
import org.leonardo.guestbook.domain.guest.Guest;
import org.leonardo.guestbook.domain.timinglogentry.TimingLogEntry;
import org.leonardo.guestbook.usecases.RegisterGuest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

@Path("/guests")
public class GuestController {

    @Inject
    RegisterGuest registerGuest;

    @Context
    HttpServletRequest request;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Guest registerGuest(Guest guest) {
        return runRecordingAccess(request, () -> registerGuest.execute(guest));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Guest> getAllGuests() {
        return Guest.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Transactional
    public Guest getGuestById(@PathParam("id") ObjectId id) {
        return runRecordingAccess(request, () -> Guest.findById(id));
    }

    private <O> O runRecordingAccess(HttpServletRequest request, Supplier<O> action) {
        Instant start = Instant.now();
        try {
            return action.get();
        } finally {
            Duration duration = Duration.between(start, Instant.now());
            registerAccess(request, start, duration);
        }
    }

    private void registerAccess(HttpServletRequest request, Instant start, Duration duration) {
        try {
            String method = request.getMethod();
            String path = request.getRequestURI();
            TimingLogEntry logEntry = new TimingLogEntry(start, duration.toMillis(), method, path);
            logEntry.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
