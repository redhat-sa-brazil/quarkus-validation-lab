package org.leonardo.guestbook.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
public class TimingLogEntry {
    @Id
    private String entryId;
    private Instant startTime;
    private Long durationInMillis;
    private String requestUri;
    private String httpMethod;

    public TimingLogEntry() {
    }

    public TimingLogEntry(
        Instant startTime,
        Long durationInMillis,
        String httpMethod,
        String requestUri) {

        this.entryId = UUID.randomUUID().toString();
        this.httpMethod = httpMethod;
        this.requestUri = requestUri;
        this.startTime = startTime;
        this.durationInMillis = durationInMillis;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Long getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
