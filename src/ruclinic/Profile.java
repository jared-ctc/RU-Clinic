package ruclinic;

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    public static final int ZERO = 0;

    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }
    @Override
    public boolean equals (Object obj){
        if(obj instanceof Profile){
            Profile profile = (Profile) obj;
            if(this.fname.equals(profile.fname) && this.lname.equals(profile.lname) && this.dob.equals(profile.dob)){
                return true;
            }

        }
        return false;
    }
    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString();
    }
    @Override
    public int compareTo(Profile profile){
        int compareLName = this.lname.compareTo(profile.lname);
        //compare last names first, return if last names are not the same:
        if(compareLName != ZERO){
            return compareLName;
        }
        //last names are the same, compare first name:
        int compareFirst = this.fname.compareTo(profile.fname);
        if(compareFirst != ZERO){
            return compareFirst;
        }
        //both profiles have the same first and last name, compare their d.o.b:
        int compareDob = this.dob.compareTo(profile.dob);
        if(compareDob != ZERO ){
            return compareDob;
        }
        //if the last/first names and dob are the same,  both profiles belong to the same person:
        return ZERO;
    }

    public static void main(String[] args){/*
        Date date1 = new Date(1990, 02, 27);
        Date date2 = new Date(2000, 04, 4);
        Date date3 = new Date(2000, 04, 4);
        Date date4 = new Date(2000, 04, 3);
        Profile p1 = new Profile("John", "Smith", date1);
        Profile p2 = new Profile("Jane", "Doe", date2);
        Profile p3 = new Profile("Jane", "Doe", date3);
        Profile p4 = new Profile("John", "Stevens", date4);
        System.out.println("toString Method1: " + p1.toString());
        System.out.println("toString Method2: " + p2.toString());
        System.out.println("toString Method3: " + p3.toString());
        //not equals ex:
        boolean isTheSame = date1.equals(date2);
        System.out.println("Compare p1 and p2: " + isTheSame);
        //equals example:
        boolean checkSame = date2.equals(date3);
        System.out.println("Compare p2 and p3: " + checkSame);
        //compare last names
        int compLast = p1.compareTo(p2);
        if(compLast > 0 ){
            System.out.println("p1 is greater than p2");
        }
        if (compLast <  0 ){
            System.out.println("p1 is less than p2");
        }
        if(compLast == 0){
            System.out.println("p1 is the same person as p2");
        }
        //compare first names
        int checkFirst = p1.compareTo(p4);
        if(checkFirst > 0 ){
            System.out.println("p1 is greater than p4");
        }
        if (checkFirst <  0 ){
            System.out.println("p1 is less than p4");
        }
        if(checkFirst == 0){
            System.out.println("p1 is the same person as p4");
        }


        //compare if both are the same person
        int comp = p2.compareTo(p3);
        if(comp > 0 ){
            System.out.println("p2 is greater than p3");
        }
        if (comp <  0 ){
            System.out.println("p2 is less than p3");
        }
        if(comp == 0){
            System.out.println("p2 is the same person as p3");
        }*/

    }
}
