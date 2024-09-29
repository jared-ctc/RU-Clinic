package ruclinic;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits;

    public int charge()
    {
        Visit ptr = this.visits;
        int totalCharge = 0;

        while (ptr != null)
        {
            Specialty apptSpecialty = ptr.getAppointment().getProvider().getSpecialty();

            switch (apptSpecialty)
            {
                case
            }

            ptr = ptr.getNext();
        }

        return totalCharge;
    }

    @Override
    int compareTo(Patient patient)
    {

    }
}
