import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieBookingSystemTest {
    private MovieBookingSystem mbs;

    @BeforeEach
    void setUp() {
        mbs = new MovieBookingSystem();
        mbs.addShow("10:00 AM", 50);
        mbs.addShow("1:00 PM", 20);
    }

    @Test
    void testBookTicketSuccess() {
        String result = mbs.bookTicket("10:00 AM", 5);
        assertEquals("5 tickets successfully booked for 10:00 AM", result);
    }

    @Test
    void testBookTicketNotEnough() {
        String result = mbs.bookTicket("10:00 AM", 100);
        assertEquals("Not enough tickets available for this showtime.", result);
    }

    @Test
    void testCancelReservationSuccess() {
        mbs.bookTicket("10:00 AM", 5);
        String result = mbs.cancelReservation("10:00 AM", 3);
        assertEquals("3 tickets successfully canceled for 10:00 AM", result);
    }

    @Test
    void testBookAnotherShowtime() {
        String result = mbs.bookTicket("1:00 PM", 2);
        assertEquals("2 tickets successfully booked for 1:00 PM", result);
    }

    @Test
    void testCancelMoreThanBooked() {
        mbs.bookTicket("1:00 PM", 2);
        String result = mbs.cancelReservation("1:00 PM", 5);
        assertEquals("Invalid operation (Attempt to cancel more tickets than booked).", result);
    }

    @Test
    void testInvalidShowtime() {
        String result = mbs.bookTicket("9:00 PM", 2);
        assertEquals("Showtime not found.", result);
    }
}
