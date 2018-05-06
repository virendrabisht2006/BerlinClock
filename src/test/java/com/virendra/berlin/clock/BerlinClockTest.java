package com.virendra.berlin.clock;

import com.virendra.berlin.clock.exception.BerlinClockException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Virendra Singh on 06/05/18.
 */
public class BerlinClockTest {

    private BerlinClock berlinClock = new BerlinClock();

    @Test
    public void shouldConvertTimeWhenAllInputIsZero() {
        StringBuilder expectedTime = new StringBuilder("Y");
        expectedTime.append("\n").append("OOOO")
                .append("\n").append("OOOO")
                .append("\n").append("OOOOOOOOOOO")
                .append("\n").append("OOOO");
        String actualTime = berlinClock.convertTime("00:00:00");

        assertNotNull(actualTime);
        assertEquals(expectedTime.toString(), actualTime);
    }

    @Test
    public void shouldConvertTimeWhenTimeIsEarlyMorning() {
        StringBuilder expectedTime = new StringBuilder("Y");
        expectedTime.append("\n").append("OOOO")
                .append("\n").append("ROOO")
                .append("\n").append("YYOOOOOOOOO")
                .append("\n").append("OOOO");
        String actualTime = berlinClock.convertTime("01:10:12");

        assertNotNull(actualTime);
        assertEquals(expectedTime.toString(), actualTime);
    }

    @Test
    public void shouldConvertTimeWhenTimeIsAfterNoon() {
        StringBuilder expectedTime = new StringBuilder("O");
        expectedTime.append("\n").append("RROO")
                .append("\n").append("RRRO")
                .append("\n").append("YYROOOOOOOO")
                .append("\n").append("YYOO");
        String actualTime = berlinClock.convertTime("13:17:01");

        assertNotNull(actualTime);
        assertEquals(expectedTime.toString(), actualTime);
    }

    @Test
    public void shouldConvertTimeWhenTimeIsNight() {
        StringBuilder expectedTime = new StringBuilder("O");
        expectedTime.append("\n").append("RRRR")
                .append("\n").append("RRRO")
                .append("\n").append("YYRYYRYYRYY")
                .append("\n").append("YYYY");
        String actualTime = berlinClock.convertTime("23:59:59");

        assertNotNull(actualTime);
        assertEquals(expectedTime.toString(), actualTime);
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowBerlinClockExceptionInCaseInvalidInput() {
        berlinClock.convertTime("21323:59:59");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowBerlinClockExceptionWhenInputDoestNotConatainOnlyNumber() {
        berlinClock.convertTime("21:59:59AB");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowBerlinClockExceptionWhenInputIsNull() {
        berlinClock.convertTime(null);
    }
}