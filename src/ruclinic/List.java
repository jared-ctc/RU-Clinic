package ruclinic;

public class List {
    private static final int INITIAL_CAPACITY = 4;
    private static final int CAPACITY_INCREASE = 4;
    private static final int NOT_FOUND = -1;

    private Appointment[] appointments;
    private int size; // number of appointments in array

    public List()
    {
        size = 0;
        appointments = new Appointment[INITIAL_CAPACITY];
    }

    private int find(Appointment appointment)
    {
        for (int i = 0; i < size; i++)
        {
            if (appointment.equals(appointments[i]))
                return i;
        }

        return NOT_FOUND;
    }

    private void grow()
    {
        Appointment[] newAppointments = new Appointment[appointments.length + CAPACITY_INCREASE];

        for (int i = 0; i < appointments.length; i++)
        {
            newAppointments[i] = appointments[i];
        }

        appointments = newAppointments;
    }

    public boolean contains(Appointment appointment)
    {
        for (int i = 0; i < size; i++)
        {
            if (appointment.equals(appointments[i]))
                return true;
        }

        return false;
    }

    public void add(Appointment appointment)
    {
        if (++size > appointments.length)
        {
            grow();
        }

        appointments[size] = appointment;
    }

    public void remove(Appointment appointment)
    {
        int removeIndex = find(appointment);

        if (removeIndex == NOT_FOUND)
            return;

        // fill empty spot with last appointment in list and decrements size
        swap(removeIndex, --size);
    }

    public void printByPatient()
    {
        sortByPatient();
        for (int i = 0; i < size; i++)
        {
            System.out.println(appointments[i].toString());
        }

    }

    public void printByLocation()
    {
        sortByLocation();
        for(int i = 0; i < size; i++)
        {
            System.out.println(appointments[i].toString());
        }
    }

    public void printByAppointment()
    {
        sortByAppointment();
        for(int i = 0; i < size; i++)
        {
            System.out.println(appointments[i].toString());
        }
    }

    private void sortByPatient()
    {
        for (int i = 1; i < size; i++)
        {
            int k = i;
            for (int j = k-1; j >= 0; j--)
            {
                int comparePatient = appointments[k].getPatient().compareTo(appointments[j].getPatient());
                int compareDate = appointments[k].getDate().compareTo(appointments[j].getDate());
                int compareTimeslot = appointments[k].getTimeslot().compareTo(appointments[j].getTimeslot());

                // sorts by Patient, Date, then Timeslot
                if (    comparePatient < 0 ||
                        (comparePatient == 0 && compareDate < 0) ||
                        (compareDate == 0 && compareTimeslot < 0) )
                {
                    swap(k, j);
                    k--;
                }
                else // Appointment has been placed in order
                {
                    break;
                }
            }
        }
    }

    private void sortByLocation()
    {
        for (int i = 1; i < size; i++)
        {
            int k = i;
            for (int j = k-1; j >= 0; j--)
            {
                String kcounty = appointments[k].getProvider().getLocation().getCounty();
                String jcounty = appointments[j].getProvider().getLocation().getCounty();

                int compareLocation = kcounty.compareTo(jcounty);
                int compareDate = appointments[k].getDate().compareTo(appointments[j].getDate());
                int compareTimeslot = appointments[k].getTimeslot().compareTo(appointments[j].getTimeslot());

                // sorts by Date, Timeslot, then Provider
                if (    compareLocation < 0 ||
                        (compareLocation == 0 && compareDate < 0) ||
                        (compareDate == 0 && compareTimeslot < 0) )
                {
                    swap(k, j);
                    k--;
                }
                else // Appointment has been placed in order
                {
                    break;
                }
            }
        }
    }

    private void sortByAppointment()
    {
        for (int i = 1; i < size; i++)
        {
            int k = i;
            for (int j = k-1; j >= 0; j--)
            {
                int compareDate = appointments[k].getDate().compareTo(appointments[j].getDate());
                int compareTimeslot = appointments[k].getTimeslot().compareTo(appointments[j].getTimeslot());
                int compareProvider = appointments[k].getProvider().compareTo(appointments[j].getProvider());

                // sorts by Date, Timeslot, then Provider
                if (    compareDate < 0 ||
                        (compareDate == 0 && compareTimeslot < 0) ||
                        (compareTimeslot == 0 && compareProvider < 0) )
                {
                    swap(k, j);
                    k--;
                }
                else // Appointment has been placed in order
                {
                    break;
                }
            }
        }
    }

    // helper method
    private void swap(int index1, int index2)
    {
        if (index1 == index2)
            return;

        Appointment temp = appointments[index1];
        appointments[index1] = appointments[index2];
        appointments[index2] = temp;
    }

}
