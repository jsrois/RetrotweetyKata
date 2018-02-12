package io.alfrheim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class RetrotweetyShould {

    @Mock
    private  Console console;

    @Mock
    private Clock clock;

    @Test
    public void print_the_superman_message() throws Exception {
        Retrotweety retrotweety = new Retrotweety(console);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Superman");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (0 second ago)");
    }

    @Test
    public void print_the_followers_message_on_the_wall() throws Exception {
        Retrotweety retrotweety = new Retrotweety(console, clock);

        ZonedDateTime now = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30, 15)
                .atZone(ZoneId.of("UTC"));
        ZonedDateTime twoSecondsAgo = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30, 13)
                .atZone(ZoneId.of("UTC"));

        given(clock.now())
                .willReturn(twoSecondsAgo, now);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Spiderman follows Superman");
        retrotweety.command("Spiderman -> Hey Superman! I'm following you!");

        retrotweety.command("Spiderman wall");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Spiderman - Hey Superman! I'm following you! (0 second ago)");
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (2 seconds ago)");
    }

}
