package ruclinic;
import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int MAX_DAYS = 31;
    public static final int MIN_DAYS = 28;
    public static final int MIN_MONTH = 0;
    public static final int MAX_MONTH = 11;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int ZERO = 0;

    public Date (int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    private boolean isLeap(int year){
        if(year % QUADRENNIAL == ZERO && year % CENTENNIAL != ZERO){
            return true;
        }
        if(year % QUARTERCENTENNIAL == ZERO){
            return true;
        }
        return false;
    }
    private int maxMonthDays(int year, int month){
        boolean leap = isLeap(year);
        //check leap for feb
        if(leap && month == Calendar.FEBRUARY){
            return MIN_DAYS + 1;
        }else
            if(leap != true && month == Calendar.FEBRUARY){
            return MIN_DAYS;
            }
            switch(month){
                case Calendar.JANUARY:
                case Calendar.MARCH:
                case Calendar.MAY:
                case Calendar.JULY:
                case Calendar.AUGUST:
                case Calendar.OCTOBER:
                case Calendar.DECEMBER:
                    return MAX_DAYS;
                case Calendar.APRIL:
                case Calendar.JUNE:
                case Calendar.SEPTEMBER:
                case Calendar.NOVEMBER:
                    return MAX_DAYS - 1;
                default:
                    return 0;
            }
    }
    public boolean isValid(){
        //day doesn't exist
        if(day <= ZERO || month < MIN_MONTH || month > MAX_MONTH){
            return false;
        }
        //tests if the requested date is in the past
        Calendar dateToday = Calendar.getInstance();
        if(year < dateToday.get(Calendar.YEAR) || (year == dateToday.get(Calendar.YEAR) && month < dateToday.get(Calendar.MONTH))||
                (year == dateToday.get(Calendar.YEAR) && month == dateToday.get(Calendar.MONTH) && day < dateToday.get(Calendar.DAY_OF_MONTH))){
            return false;
        }
      //tests if the date exists in the future
        int monthDays = maxMonthDays(year, month);
        if(day > monthDays){
            return false;
        }
        return true;
    }//add checkpoint for weekend if needed later

    @Override
    public boolean equals (Object obj){
        if (obj instanceof Date){
            Date date = (Date) obj;
            if(this.year == date.year && this.month == date.month && this.day == date.day){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString (){
        //numbers in calendar start at 0(double check)
        return month+1  + "/" + day + "/" + year;
    }
    @Override
    public int compareTo(Date date){
        if(this.year != date.year){
            //compares years, negative result means this.year comes after date.year and so on
            return this.year - date.year;
        }
        //if both dates happen to be in the same year, compare the months
        if(this.month != date.month){
            return this.month - date.month;
        }
        //both dates have the same year and month, compare the day #s
        return this.day - date.day;

    }

    public static void main (String[] args){
        Date date  = new Date(2024, 11, 29);
        Date date2 = new Date(2025, 3, 5);
        boolean confirm = date.isValid();
        boolean confirm2 = date.isValid();
        System.out.println("date1: " + confirm);
        System.out.println("date2: " + confirm2);
        System.out.println(date.toString());
        System.out.println(date2.toString());
        // compareTo method
        int compareDates = date.compareTo(date2);
        if(compareDates == 0){
            System.out.println("Dates are the same");
        }else
            if (compareDates > 0){
                System.out.println(date2.toString() + " occurs first.");
            }else
                System.out.println(date.toString() + " occurs first.");

    }

}
