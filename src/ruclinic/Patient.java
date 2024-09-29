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
            ptr = ptr.getNext();
        }

        return totalCharge;
    }

    @Override
    int compareTo(Patient patient)
    {

    }
}
