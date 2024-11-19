package clockdemo;

public class ClockDemo {

    public static void main(String[] args) {
        // Create a Clock instance with initial time
        Clock clock = new Clock(10, 45, 50);

        // Display initial time in both formats
        displayTime(clock);

        // Add an hour, minute, and second
        clock.addHour();
        clock.addMinute();
        clock.addSecond();

        // Display updated time in both formats
        displayTime(clock);

        // Test boundary case
        clock = new Clock(23, 59, 59);
        System.out.println("Boundary test - initial time:");
        displayTime(clock);

        // Increment to test wraparound
        clock.addSecond();
        System.out.println("Boundary test - after adding second:");
        displayTime(clock);
    }

    // Helper method to display time in both 24-hour and 12-hour formats
    private static void displayTime(Clock clock) {
        System.out.println("24-hour format: " + clock.get24HourFormat());
        System.out.println("12-hour format: " + clock.get12HourFormat());
    }
}
