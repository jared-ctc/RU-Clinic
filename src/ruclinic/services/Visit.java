package ruclinic.services;

import ruclinic.models.Appointment;

public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit()
    {
        this.appointment = null;
        this.next = null;
    }

    public Visit(Appointment appointment, Visit visit)
    {
        this.appointment = appointment;
        this.next = visit;
    }

    public Visit getNext()
    {
        return this.next;
    }

    public void setAppointment(Appointment appointment)
    {
        this.appointment = appointment;
    }

    public void setNext(Visit visit)
    {
        this.next = visit;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

}
