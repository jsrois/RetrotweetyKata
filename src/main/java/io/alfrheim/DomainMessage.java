package io.alfrheim;

import java.time.Duration;
import java.time.ZonedDateTime;

public class DomainMessage {

    private final DomainUser domainUser;
    private final String message;
    private final ZonedDateTime created;

    public DomainMessage(DomainUser domainUser, String message, ZonedDateTime created) {
        this.domainUser = domainUser;
        this.message = message;
        this.created = created;
    }

    public String format(Clock clock) {
        String formattedTime = this.getProperTimeFormat(clock.elapsedTime(getCreated()));
        return String.format("%s - %s %s", this.getDomainUser().getName(), this.getMessage(), formattedTime);
    }

    private String getProperTimeFormat(Duration timeSince) {
        long elapsedTime;
        String time;
        if (timeSince.toMinutes() > 0) {
            elapsedTime = timeSince.toMinutes();
            time = "minute";
            if (isPlural(elapsedTime)) {
                time += "s";
            }

        } else {
            elapsedTime = timeSince.getSeconds();
            time = "second";
            if (isPlural(elapsedTime)) {
                time += "s";
            }

        }

        return String.format("(%d %s ago)", elapsedTime, time);
    }

    private boolean isPlural(long elapsedTime) {
        return elapsedTime > 1;
    }

    public String getMessage() {
        return message;
    }

    public DomainUser getDomainUser() {
        return domainUser;
    }

    public ZonedDateTime getCreated() {
        return created;
    }
}
