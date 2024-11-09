package ruclinic.models;

public class MedicalRecord
{
    private static final int INITIAL_CAPACITY = 4;
    private static final int RESIZE_FACTOR = 2;

    private Patient[] patients;
    private int size; // number of patient objects in array

    public MedicalRecord()
    {
        patients = new Patient[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(Patient patient)
    {
        if (size > patients.length)
        {
            resize();
        }
        patients[size++] = patient;
    }

    public void resize()
    {
        Patient[] newPatients = new Patient[patients.length * RESIZE_FACTOR];

        for (int i = 0; i < patients.length; i++)
        {
            newPatients[i] = patients[i];
        }

        patients = newPatients;
    }
}
