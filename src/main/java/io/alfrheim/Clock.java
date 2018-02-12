package io.alfrheim;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Clock {
    public ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }


    public Duration elapsedTime(ZonedDateTime time) {
        ZonedDateTime currentDate = this.now();
        return Duration.between(time, currentDate);
    }
}
