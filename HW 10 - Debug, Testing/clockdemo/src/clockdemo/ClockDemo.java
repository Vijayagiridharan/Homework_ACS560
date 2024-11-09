// ClockDemo.java
package clockdemo;



public class ClockDemo {

    public static void main(String[] args) {
        // Create a Clock instance with initial time
        Clock clock = new Clock(10, 45, 50);

        // Display initial time in both formats
        System.out.println("Initial 24-hour format: " + clock.get24HourFormat());
        System.out.println("Initial 12-hour format: " + clock.get12HourFormat());

        // Add an hour, minute, and second
        clock.addHour();
        clock.addMinute();
        clock.addSecond();

        // Display updated time in both formats
        System.out.println("Updated 24-hour format: " + clock.get24HourFormat());
        System.out.println("Updated 12-hour format: " + clock.get12HourFormat());

        // Test boundary case
        clock = new Clock(23, 59, 59);
        System.out.println("Boundary test initial 24-hour format: " + clock.get24HourFormat());
        System.out.println("Boundary test initial 12-hour format: " + clock.get12HourFormat());

        // Increment to test wraparound
        clock.addSecond();
        System.out.println("Boundary test after addSecond - 24-hour format: " + clock.get24HourFormat());
        System.out.println("Boundary test after addSecond - 12-hour format: " + clock.get12HourFormat());
    }
}
