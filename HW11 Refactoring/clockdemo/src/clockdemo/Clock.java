package clockdemo;

public class Clock {
    
    private int hours;
    private int minutes;
    private int seconds;

    public Clock(int hours, int minutes, int seconds) {
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) { 
            throw new IllegalArgumentException("Hours, minutes, and seconds must be within valid ranges.");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void addHour() {
        hours = (hours + 1) % 24;
    }

    public void addMinute() {
        if (++minutes >= 60) {
            minutes = 0;
            addHour();
        }
    }

    public void addSecond() {
        if (++seconds >= 60) {
            seconds = 0;
            addMinute();
        }
    }

    public String get24HourFormat() {
        return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
    }

    private String getSuffix() {
        return (hours < 12) ? "AM" : "PM";
    }

    public String get12HourFormat() {
        int hour12 = hours % 12;
        if (hour12 == 0) {
            hour12 = 12;
        }
        return hour12 + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();
    }

    private String pad(int value) {
        return (value < 10 ? "0" : "") + value;
    }
}
