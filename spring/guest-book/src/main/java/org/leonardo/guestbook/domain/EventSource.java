package org.leonardo.guestbook.domain;

public interface EventSource {
    void emitGuestRegistered(String id);
}
