package ruclinic.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ruclinic.utils.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTest {

    @Test
    @DisplayName("Create a valid date in the future.")
    public void testCreateValidDate() {
        Date date = new Date(2024, 0, 31);

        boolean isValid = date.isValid();

        assertTrue(isValid, "The date created is valid");
    }

    @Test
    @DisplayName("Create a valid date in the future.")
    public void testCreateValidDateInLeapYear() {
        Date date = new Date(2024, 1, 29);

        boolean isValid = date.isValid();

        assertTrue(isValid, "The date created is valid on a leap year");
    }

    @Test
    @DisplayName("Create an invalid date exceeding days in the month")
    public void testDateExceedingDays() {
        Date date = new Date(2024, 0, 32);

        boolean isValid = date.isValid();

        assertFalse(isValid, "The date created is not valid by exceeding the days in the month");
    }

    @Test
    @DisplayName("Create an invalid date exceeding months in the year")
    public void testDateExceedingMonths() {
        Date date = new Date(2024, 14, 1);

        boolean isValid = date.isValid();

        assertFalse(isValid, "The date created is not valid by exceeding the months in the year");
    }

    @Test
    @DisplayName("Create an invalid date of a non-existent year")
    public void testDateNonExistentYear() {
        Date date = new Date(-1, 14, 1);

        boolean isValid = date.isValid();

        assertFalse(isValid, "The date created is not valid due to a non-existent year");
    }

    @Test
    @DisplayName("Create an invalid date exceeding days of February in a non-leap year")
    public void testDateInLeapYear() {
        Date date = new Date(2023, 1, 29);

        boolean isValid = date.isValid();

        assertFalse(isValid, "The date created is not be valid on a non-leap year");
    }
}
