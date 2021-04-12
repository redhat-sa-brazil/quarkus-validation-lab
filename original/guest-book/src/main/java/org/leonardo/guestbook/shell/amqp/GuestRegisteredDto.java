package org.leonardo.guestbook.shell.amqp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GuestRegisteredDto implements Serializable {
    private String guestId;

    @JsonCreator
    public GuestRegisteredDto(@JsonProperty("guestId") String guestId) {
        this.guestId = guestId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
