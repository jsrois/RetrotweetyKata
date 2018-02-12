package io.alfrheim;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Clock {
    public ZonedDateTime now() {
        return  ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
