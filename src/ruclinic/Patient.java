package ruclinic;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits;
    public int charge()
    {
        Visit ptr = this.visits;
        int totalCharge = 0;
        // traverse linked list of Visits and add charge
        while (ptr != null)
        {
            Specialty apptSpecialty = ptr.getAppointment().getProvider().getSpecialty();
            totalCharge += apptSpecialty.getCharge();
            ptr = ptr.getNext();
        }
        return totalCharge;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Patient)
        {
            Patient patient = (Patient) obj;
            if (this.profile.equals(patient.profile))
                return true;
        }
        return false;
    }
    @Override
    public int compareTo(Patient patient)
    {
        return this.profile.compareTo(patient.profile);
    }
    @Override
    public String toString()
    {
        return this.profile.toString();
    }
}
