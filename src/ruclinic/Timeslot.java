package ruclinic;

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
    public String toString(){

    }

}
