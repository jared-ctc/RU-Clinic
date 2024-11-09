package ruclinic;

import java.text.DecimalFormat;

public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hour, minute;

    Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }

    @Override
    public String toString()
    {
        DecimalFormat minTime = new DecimalFormat("00");
        String minutes = minTime.format(this.getMinute());
        String dayTime = "AM";
        int timeHour = this.getHour();
        if(this.getHour() > 12) {
            dayTime = "PM";
            timeHour = this.getHour() % 12;
        }
        return timeHour + ":" + minutes + " " + dayTime;
    }

}
