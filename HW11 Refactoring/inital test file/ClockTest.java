package clockdemo;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import clockdemo.Clock;  // Ensure the correct import

class ClockTest {

    @Test
    void testClockInitialization() {
        Clock clock = new Clock(10, 45, 50);
        assertEquals("10:45:50", clock.get24HourFormat());
        assertEquals("10:45:50 AM", clock.get12HourFormat());
    }

    @Test
    void testAddHour() {
        Clock clock = new Clock(10, 45, 50);
        clock.addHour();
        assertEquals("11:45:50", clock.get24HourFormat());
    }

    @Test
    void testAddMinute() {
        Clock clock = new Clock(10, 59, 50);
        clock.addMinute();
        assertEquals("11:00:50", clock.get24HourFormat());
    }

    @Test
    void testAddSecond() {
        Clock clock = new Clock(10, 45, 59);
        clock.addSecond();
        assertEquals("10:46:00", clock.get24HourFormat());
    }

    @Test
    void testBoundary() {
        Clock clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }
}


