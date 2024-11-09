package ruclinic.services;
import ruclinic.enums.Provider;
import ruclinic.enums.Timeslot;
import ruclinic.models.Appointment;
import ruclinic.models.Profile;
import ruclinic.utils.Date;
import ruclinic.utils.List;

import java.util.Scanner;

public class Scheduler {
    private static final int COMMAND_INDEX = 0;
    private static final int MIN_TIMESLOT = 1;
    private static final int MAX_TIMESLOT = 6;

    public List appointmentSchedule;

    Scanner input;

    public Scheduler()
    {
        System.out.println("Scheduler is running.\n");
        appointmentSchedule = new List();
        input = new Scanner(System.in);
    }


    public void run() {
        String inputLine = "";

        while (!inputLine.equals("Q"))
        {
            inputLine = input.nextLine();
            while (inputLine.isEmpty())   inputLine = input.nextLine(); // skip empty lines
            String[] userData = inputLine.split(",");

            String userCommand = userData[0];
            switch (userCommand)
            {
                case "S":
                    runCommandS(userData); break;
                case "C":
                    runCommandC(userData); break;
                case "R":
                    runCommandR(userData); break;
                case "PA":
                    runCommandPA(userData); break;
                case "PP":
                    runCommandPP(userData); break;
                case "PL":
                    runCommandPL(userData); break;
                case "PS":
                    runCommandPS(userData); break;
                case "Q":
                    break;
                default:
                    System.out.println("Invalid Command!"); break;
            }
        }
    }


    /*
        Helper method
        Takes the second element of userData (a string with format "MM/DD/YY")
           corresponding to the appointment Date
        Returns the appointment date as a Date object
     */
    private Date toDateObject(String dateString)
    {
        String[] parsedStringDate = dateString.split("/");
        int[] parsedIntDate = new int[parsedStringDate.length];
        for (int i = 0; i < parsedIntDate.length; i++)
            parsedIntDate[i] = Integer.parseInt(parsedStringDate[i]);
        return (new Date(parsedIntDate[2], parsedIntDate[0] - 1, parsedIntDate[1]));
    }

    private boolean isValidAppointmentDate(Date apptDate)
    {
        if (!apptDate.isValid()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is not a valid calendar date.");
            return false;
        }
        else if (apptDate.hasPast() || apptDate.isToday()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is today or a date before today.");
            return false;
        }
        else if (apptDate.isWeekend()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is Saturday or Sunday.");
            return false;
        }
        else if (!apptDate.withinSixMonths()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is not within six months.");
            return false;
        }

        return true;
    }

    private boolean isValidTimeslot(String timeslotString)
    {
        if (!Character.isDigit(timeslotString.charAt(0))) {
            System.out.println(timeslotString.charAt(0) + " is not a valid time slot.");
            return false;
        }

        int timeslotInt = Integer.parseInt(timeslotString);

        if (timeslotInt < MIN_TIMESLOT || timeslotInt > MAX_TIMESLOT)
        {
            System.out.println(timeslotString.charAt(0) + " is not a valid time slot.");
            return false;
        }

        return true;
    }

    private boolean isValidBirthDate(Date birthDate)
    {
        if (!birthDate.isValid()) {
            System.out.println("Patient dob: " + birthDate.toString() +
                    " is not a valid calendar date.");
            return false;
        }
        else if (!birthDate.hasPast() || birthDate.isToday()) {
            System.out.println("Patient dob: " + birthDate.toString() +
                    " is today or a date after today.");
            return false;
        }
        return true;
    }

    private Timeslot toTimeslotObject(String timeslotString)
    {
        int timeslotInt = Integer.parseInt(timeslotString);

        switch (timeslotInt) {
            case 1:
                return Timeslot.SLOT1;
            case 2:
                return Timeslot.SLOT2;
            case 3:
                return Timeslot.SLOT3;
            case 4:
                return Timeslot.SLOT4;
            case 5:
                return Timeslot.SLOT5;
            case 6:
                return Timeslot.SLOT6;
            default:
                return null;
        }
    }

    private boolean appointmentAlreadyExists(Appointment appt)
    {
        if (appointmentSchedule.contains(appt)) {
            System.out.println(appt.getPatient().toString() + " has an existing appointment " +
                    "at the same time slot.");
            return true;
        }
        return false;
    }


    private Provider toProviderObject(String providerString)
    {
        for (Provider p : Provider.values()) {
            if (p.name().equals(providerString.toUpperCase())) {
                return p;
            }
        }
        return null;
    }


    private boolean providerIsTaken(Appointment appt)
    {
        boolean isTaken = appointmentSchedule.slotIsTaken(appt);
        if (isTaken)
        {
            System.out.println(appt.getProvider().toString() +
                    " is not available at slot " + (appt.getTimeslot().ordinal() + 1));
        }

        return isTaken;
    }


    private void runCommandS(String[] userData) {
        Date apptDate = toDateObject(userData[1]);
        if (!isValidAppointmentDate(apptDate)) return;

        if (!isValidTimeslot(userData[2])) return;
        Timeslot timeslot = toTimeslotObject(userData[2]);

        Date birthDate = toDateObject(userData[5]);
        if (!isValidBirthDate(birthDate)) return;

        Profile profile = new Profile(userData[3], userData[4], birthDate);
        Appointment newAppointment = new Appointment(apptDate, timeslot, profile);
        if (appointmentAlreadyExists(newAppointment)) return;

        Provider provider = toProviderObject(userData[6]);
        if (provider == null) {
            System.out.println(userData[6] + " - provider doesn't exist.");
            return;
        }
        newAppointment.setProvider(provider);
        if (providerIsTaken(newAppointment))    return;

        appointmentSchedule.add(newAppointment);
        System.out.println(newAppointment.toString() + " booked.");
    }

    private void runCommandC(String[] userData)
    {
        // Get Appointment date
        Date apptDate = toDateObject(userData[1]);

        Timeslot apptTimeslot = toTimeslotObject(userData[2]);

        String fname = userData[3];
        String lname = userData[4];
        Date birthDate = toDateObject(userData[5]);
        Profile profile = new Profile(fname, lname, birthDate);

        // create object matching appointment that will be cancelled in List
        Appointment cancelAppt = new Appointment(apptDate, apptTimeslot, profile);
        if (appointmentSchedule.contains(cancelAppt))
        {
            appointmentSchedule.remove(cancelAppt);
            System.out.println(apptDate.toString() +
                    " " + apptTimeslot.toString() +
                    " " + profile.toString() +
                    " has been cancelled.");
        }
        else
        {
            System.out.println(apptDate.toString() +
                    " " + apptTimeslot.toString() +
                    " " + profile.toString() +
                    " does not exist.");
        }
    }

    private void runCommandR(String[] userData)
    {
        /*
        Date apptDate = toDateObject(userData[1]);
        Timeslot originalTimeslot = toTimeslotObject(userData[2]);

        String fname = userData[3];
        String lname = userData[4];
        Date birthDate = toDateObject(userData[5]);
        Profile profile = new Profile(fname, lname, birthDate);

        Appointment originalAppt =

        */
    }

    private void runCommandPA(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by date/time/provider **");
        appointmentSchedule.printByAppointment();
        System.out.println("** end of list **");
    }

    private void runCommandPP(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by patient/date/time **");
        appointmentSchedule.printByPatient();
        System.out.println("** end of list **");
    }

    private void runCommandPL(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by county/date/time **");
        appointmentSchedule.printByLocation();
        System.out.println("** end of list **");
    }

    private void runCommandPS(String[] userData)
    {

    }
}
