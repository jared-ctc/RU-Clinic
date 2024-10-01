package ruclinic;
import java.util.Scanner;
import java.util.Calendar;

public class Scheduler {
    private static final int COMMAND_INDEX = 0;

    public List appointmentSchedule;

    Scanner input;

    public Scheduler()
    {
        System.out.println("Scheduler is running.\n");
        appointmentSchedule = new List();
        input = new Scanner(System.in);
    }


    public void run() {
        String inputLine = input.nextLine();
        while (inputLine.isEmpty()) inputLine = input.nextLine();  // reject empty lines
        
        String [] userData = inputLine.split(",");
        String userCommand = userData[COMMAND_INDEX];

        while (!userCommand.equals("Q"))
        {
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
               default:
                   System.out.println("Invalid Command!"); break;
           }
            inputLine = input.nextLine();
            if (inputLine.isEmpty()) continue;
            userData = inputLine.split(",");
            userCommand = userData[COMMAND_INDEX];
        }

    }

    private void runCommandS(String[] userData) {
        // Create appointment object from parsed userData
        // parse date (userData[1]) to create Date object
        String[] dateString = userData[1].split("/");
        int[] parsedApptDate = new int[dateString.length];
        for (int i = 0; i < parsedApptDate.length; i++)
            parsedApptDate[i] = Integer.parseInt(dateString[i]);
        Date apptDate = new Date(parsedApptDate[2], parsedApptDate[0] - 1, parsedApptDate[1]);

        // check for valid appointment date
        if (!apptDate.isValid()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is not a valid calendar date.");
            return;
        } else if (apptDate.hasPast() || apptDate.isToday()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is today or a date before today.");
            return;
        } else if (apptDate.isWeekend()) {
            System.out.println("Appointment date: " + apptDate.toString() +
                    " is Saturday or Sunday.");
            return;
        }


        // check for valid time slot
        if (!Character.isDigit(userData[2].charAt(0))) {
            System.out.println(userData[2].charAt(0) + " is not a valid time slot.");
            return;
        }
        int timeslotInput = Integer.parseInt(userData[2]);
        if (timeslotInput < 1 || timeslotInput > 6) {
            System.out.println(timeslotInput + " is not a valid time slot.");
            return;
        }

        Timeslot timeslot = Timeslot.SLOT1;
        switch (timeslotInput) {
            case 1:
                timeslot = Timeslot.SLOT1;
                break;
            case 2:
                timeslot = Timeslot.SLOT2;
                break;
            case 3:
                timeslot = Timeslot.SLOT3;
                break;
            case 4:
                timeslot = Timeslot.SLOT4;
                break;
            case 5:
                timeslot = Timeslot.SLOT5;
                break;
            case 6:
                timeslot = Timeslot.SLOT6;
                break;
        }


        // check for valid birthdate
        dateString = userData[5].split("/");
        int[] parsedBirthDate = new int[dateString.length];
        for (int i = 0; i < parsedBirthDate.length; i++)
            parsedBirthDate[i] = Integer.parseInt(dateString[i]);
        Date birthDate = new Date(parsedBirthDate[2], parsedBirthDate[0] - 1, parsedBirthDate[1]);
        if (!birthDate.isValid()) {
            System.out.println("Patient dob: " + birthDate.toString() +
                    " is not a valid calendar date.");
            return;
        } else if (!birthDate.hasPast() || birthDate.isToday()) {
            System.out.println("Patient dob: " + birthDate.toString() +
                    " is today or a date after today.");
            return;
        }


        // check for identical appointment
        String fname = userData[3].toUpperCase();
        String lname = userData[4].toUpperCase();
        Profile profile = new Profile(fname, lname, birthDate);
        Appointment newAppt = new Appointment(apptDate, timeslot, profile);
        if (appointmentSchedule.contains(newAppt)) {
            System.out.println(profile.toString() + " has an existing appointment " +
                    "at the same time slot.");
            return;
        }

        String providerName = userData[6].toUpperCase();
        Provider provider = Provider.PATEL;
        boolean isValidProvider = false;
        for (Provider providers : Provider.values()) {
            if (providers.toString().equals(providerName)) {
                isValidProvider = true;
                provider = providers;
                break;
            }
        }
        if (!isValidProvider)
        {
            System.out.println(providerName + " - provider doesn't exist.");
            return;
        }

        //if (appointmentSchedule)

        newAppt.setProvider(provider);

        appointmentSchedule.add(newAppt);

        System.out.println(newAppt.getDate().toString() + " " +
                newAppt.getTimeslot().getHour() + ":" +
                newAppt.getTimeslot().getMinute() + " " +
                newAppt.getPatient().toString() +
                "[" + providerName + ", " + provider.getLocation() + ", " +
                provider.getLocation().getCounty() + " " +
                provider.getLocation().getZip() + ", " +
                provider.getSpecialty() + " booked.");
    }

    private void runCommandC(String[] userData)
    {
        // Get Appointment date
        String[] dateString = userData[1].split("/");
        int[] parsedApptDate = new int[dateString.length];
        for (int i = 0; i < parsedApptDate.length; i++)
            parsedApptDate[i] = Integer.parseInt(dateString[i]);
        Date apptDate = new Date(parsedApptDate[2], parsedApptDate[0] - 1, parsedApptDate[1]);

        // Get timeslot

        int timeslotInput = Integer.parseInt(userData[2]);
        Timeslot timeslot = Timeslot.SLOT1;
        switch (timeslotInput) {
            case 1:
                timeslot = Timeslot.SLOT1;
                break;
            case 2:
                timeslot = Timeslot.SLOT2;
                break;
            case 3:
                timeslot = Timeslot.SLOT3;
                break;
            case 4:
                timeslot = Timeslot.SLOT4;
                break;
            case 5:
                timeslot = Timeslot.SLOT5;
                break;
            case 6:
                timeslot = Timeslot.SLOT6;
                break;
        }

        // get first name last name
        String fname = userData[3];
        String lname = userData[4];

        // get dob
        dateString = userData[5].split("/");
        int[] parsedBirthDate = new int[dateString.length];
        for (int i = 0; i < parsedBirthDate.length; i++)
            parsedBirthDate[i] = Integer.parseInt(dateString[i]);
        Date birthDate = new Date(parsedBirthDate[2],parsedBirthDate[0] - 1, parsedBirthDate[1]);

        // create profile using fields
        Profile profile = new Profile(fname, lname, birthDate);

        // create object matching appointment that will be cancelled in List
        Appointment cancelAppt = new Appointment(apptDate, timeslot, profile);

        appointmentSchedule.remove(cancelAppt);

    }

    private void runCommandR(String[] userData)
    {

    }

    private void runCommandPA(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by date/time/provider **");
        appointmentSchedule.printByAppointment();
    }

    private void runCommandPP(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by patient/date/time **");
        appointmentSchedule.printByPatient();
    }

    private void runCommandPL(String[] userData)
    {
        if (appointmentSchedule.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        System.out.println("\n** Appointments ordered by county/date/time **");
        appointmentSchedule.printByLocation();
    }

    private void runCommandPS(String[] userData)
    {

    }
}
