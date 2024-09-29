package ruclinic;

public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public static final int MIDDAY = 12;

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment appointment = (Appointment) obj;
            if(this.date.equals(appointment.date) && this.timeslot.equals(appointment.timeslot) &&
                    this.patient.equals(appointment.patient) ){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){
        String dayTime = "AM";
        if(this.timeslot.getHour() > MIDDAY){
            dayTime = "PM";
        }
        return date.toString() + " " + this.timeslot.getHour() + ":" + this.timeslot.getMinute();

    }
    @Override
    public int compareTo(Appointment appointment){
            int compare = this.date.compareTo(appointment.date);
            if(compare != 0){
                return compare;
            }
            //same date, compare time slot
        return this.timeslot.getHour() - appointment.timeslot.getHour();
    }

    public Date getDate(){
        return date;
    }
    public Timeslot getTimeslot(){
        return timeslot;
    }
    public Profile getPatient(){
        return patient;
    }
    public Provider getProvider(){
        return provider;
    }
    public static void main(String[] args){

    }
}