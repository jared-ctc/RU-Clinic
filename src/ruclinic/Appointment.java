package ruclinic;
import java.text.DecimalFormat;

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
        DecimalFormat minTime = new DecimalFormat("00");
        String minutes = minTime.format(this.timeslot.getMinute());
        String dayTime = "AM";
        int timeHour = this.timeslot.getHour();
        if(this.timeslot.getHour() > MIDDAY){
            dayTime = "PM";
            timeHour = this.timeslot.getHour() % 12;
        }

        return date.toString() + " " + timeHour + ":" + minutes + " "
                + dayTime + " " + this.patient.getFname() + " " + this.patient.getLname() + " "  + this.patient.getDob().toString() + " " +
                "[" + this.getProvider() + ", " + this.provider.getLocation() + ", " +
                this.getProvider().getLocation().getCounty() + " " + this.getProvider().getLocation().getZip() +
                ", " + this.provider.getSpecialty() + "]";

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

    public static void main(String[] args){
        /*Date dateOfBirth = new Date(1989, 12- 1, 13);
        Date app = new Date(2024, 10 - 1, 30);

        Profile pa1 = new Profile("John", "Doe", dateOfBirth);
        Appointment appt = new Appointment(app, Timeslot.SLOT1, pa1, Provider.PATEL);
        System.out.println(appt.toString());

        Date app2 = new Date(2024, 10 - 1, 29);
        Date app3 = new Date(2024, 10 - 1, 29);
        Date app4 = new Date(2024, 10, 21);
        Appointment appt2 = new Appointment(app2, Timeslot.SLOT2, pa1, Provider.PATEL);
        Appointment appt3 = new Appointment(app3, Timeslot.SLOT2, pa1, Provider.PATEL);
        Appointment appt4 = new Appointment(app4, Timeslot.SLOT5, pa1, Provider.PATEL);
        System.out.println(appt2.toString());
        System.out.println("appt4: " + appt4.toString());
        boolean checkEqual = appt.equals(appt2);
        System.out.println("Is appt1 and appt2 equal: " + checkEqual);//false expected

        boolean check = appt2.equals(appt3);
        System.out.println("Is appt2 and appt3 equal: " + check);// true expected

        //compareTo
        int compare = appt.compareTo(appt2);
        if(compare < 0){
            System.out.println("appt1 is less than appt2");
        }
        if(compare > 0){
            System.out.println("appt1 is greater than appt2");//expected result
        }
        if(compare == 0){
            System.out.println("appt1 and appt2 are the same");

        }

        int compare1 = appt2.compareTo(appt3);
        if(compare1 < 0){
            System.out.println("appt2 is less than appt3");
        }
        if(compare1 > 0){
            System.out.println("appt3 is greater than appt3");//expected result
        }
        if(compare1 == 0){
            System.out.println("appt2 and appt3 are the same");//expected result

        }*/
    }
}