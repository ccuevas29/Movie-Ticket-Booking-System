package moviesystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieBookingSystemTest {
    private MovieBookingSystem mbs;

    // Constants to avoid magic numbers
    private static final String SHOW_10_AM = "10:00 AM";
    private static final String SHOW_1_PM = "1:00 PM";
    private static final String SHOW_9_PM = "9:00 PM";
    private static final int TICKETS_50 = 50;
    private static final int TICKETS_20 = 20;
    private static final int BOOK_5 = 5;
    private static final int BOOK_2 = 2;
    private static final int CANCEL_3 = 3;
    private static final int CANCEL_5 = 5;
    private static final int OVERBOOK = 100;

    @BeforeEach
    void setUp() {
        mbs = new MovieBookingSystem();
        mbs.addShow(SHOW_10_AM, TICKETS_50);
        mbs.addShow(SHOW_1_PM, TICKETS_20);
    }

    @Test
    void testBookTicketSuccess() {
        String result = mbs.bookTicket(SHOW_10_AM, BOOK_5);
        assertEquals(BOOK_5 + " tickets successfully booked for " + SHOW_10_AM, result);
    }

    @Test
    void testBookTicketNotEnough() {
        String result = mbs.bookTicket(SHOW_10_AM, OVERBOOK);
        assertEquals("Not enough tickets available for this showtime.", result);
    }

    @Test
    void testCancelReservationSuccess() {
        mbs.bookTicket(SHOW_10_AM, BOOK_5);
        String result = mbs.cancelReservation(SHOW_10_AM, CANCEL_3);
        assertEquals(CANCEL_3 + " tickets successfully canceled for " + SHOW_10_AM, result);
    }

    @Test
    void testBookAnotherShowtime() {
        String result = mbs.bookTicket(SHOW_1_PM, BOOK_2);
        assertEquals(BOOK_2 + " tickets successfully booked for " + SHOW_1_PM, result);
    }

    @Test
    void testCancelMoreThanBooked() {
        mbs.bookTicket(SHOW_1_PM, BOOK_2);
        String result = mbs.cancelReservation(SHOW_1_PM, CANCEL_5);
        assertEquals("Invalid operation (Attempt to cancel more tickets than booked).", result);
    }

    @Test
    void testInvalidShowtime() {
        String result = mbs.bookTicket(SHOW_9_PM, BOOK_2);
        assertEquals("Showtime not found.", result);
    }
}
