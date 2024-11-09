package clockdemo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClockTest {

    private Clock clock;

    @BeforeEach
    public void setUp() {
        clock = new Clock(10, 30, 45);
    }

    @Test
    public void testConstructorWithValidInput() {
        assertEquals("10:30:45", clock.get24HourFormat());
    }

    @Test
    public void testAddHour() {
        clock.addHour();
        assertEquals("11:30:45", clock.get24HourFormat());
    }

    @Test
    public void testAddMinute() {
        clock.addMinute();
        assertEquals("10:31:45", clock.get24HourFormat());
    }

    @Test
    public void testAddSecond() {
        clock.addSecond();
        assertEquals("10:30:46", clock.get24HourFormat());
    }

    
	@Test
    public void test24HourFormat() {
        assertEquals("10:30:45", clock.get24HourFormat());
    }

    @Test
    public void test12HourFormat() {
        assertEquals("10:30:45 AM", clock.get12HourFormat());
    }

    private void assertEquals(String string, String get12HourFormat) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testAMPMTransition() {
        clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }
    
    @Test
    public void testMiddayTransition() {
        clock = new Clock(12, 0, 0);
        assertEquals("12:00:00 PM", clock.get12HourFormat());
    }
    
    @Test
    public void testMidnightTransition() {
        clock = new Clock(0, 0, 0);
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }
}
