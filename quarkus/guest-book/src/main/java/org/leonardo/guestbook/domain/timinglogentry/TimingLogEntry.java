package org.leonardo.guestbook.domain.timinglogentry;

import io.quarkus.agroal.DataSource;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@DataSource("timing")
@Table(name = "TIMING_LOG_ENTRY")
public class TimingLogEntry extends PanacheEntityBase {

    @Id
    @Column(name = "ENTRY_ID")
    private String entryId;

    @Column(name = "START_TIME")
    private Instant startTime;

    @Column(name = "DURATION_IN_MILLIS")
    private Long durationInMillis;

    @Column(name = "REQUEST_URI")
    private String requestUri;

    @Column(name = "HTTP_METHOD")
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
