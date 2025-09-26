package moviesystem;

import java.util.HashMap;

/**
 * Concrete implementation of BookingSystem for managing movie showtimes.
 * Allows adding shows, booking tickets, and canceling reservations.
 */
public class MovieBookingSystem extends BookingSystem {

    /** Morning showtime label. */
    private static final String SHOW_MORNING = "10:00 AM";

    /** Afternoon showtime label. */
    private static final String SHOW_AFTERNOON = "1:00 PM";

    /** Initial ticket count for the morning show. */
    private static final int MORNING_TICKETS = 50;

    /** Initial ticket count for the afternoon show. */
    private static final int AFTERNOON_TICKETS = 20;

    /** Tickets to book in a successful booking test. */
    private static final int BOOK_FIVE = 5;

    /** Tickets to attempt booking beyond availability. */
    private static final int BOOK_TOO_MANY = 100;

    /** Tickets to cancel in a valid cancellation test. */
    private static final int CANCEL_THREE = 3;

    /** Tickets to book for the afternoon show. */
    private static final int BOOK_TWO = 2;

    /** Tickets to attempt cancellation beyond what was booked. */
    private static final int CANCEL_TOO_MANY = 5;

    /**
     * Represents a single movie showtime with available and booked tickets.
     */
    private static class Show {
        /** Remaining tickets available for booking. */
        private int availableTickets;

        /** Number of tickets already booked. */
        private int bookedTickets;

        /**
         * Creates a new show with the given number of tickets.
         *
         * @param initialTickets total tickets initially available
         */
        Show(final int initialTickets) {
            this.availableTickets = initialTickets;
            this.bookedTickets = 0;
        }

        /** @return available ticket count */
        int getAvailableTickets() {
            return availableTickets;
        }

        /** @return booked ticket count */
        int getBookedTickets() {
            return bookedTickets;
        }
    }

    /** Stores showtimes mapped to their ticket availability. */
    private HashMap<String, Show> shows = new HashMap<>();

    /**
     * Adds a new showtime with the specified number of available tickets.
     *
     * @param showTime the time of the show
     * @param tickets  the number of tickets available
     */
    public void addShow(final String showTime, final int tickets) {
        shows.put(showTime, new Show(tickets));
    }

    @Override
    public final boolean checkAvailability(final String showTime,
                                           final int tickets) {
        Show show = shows.get(showTime);
        return show != null && show.getAvailableTickets() >= tickets;
    }

    @Override
    public final String bookTicket(final String showTime, final int tickets) {
        Show show = shows.get(showTime);
        if (show == null) {
            return "Showtime not found.";
        }
        if (checkAvailability(showTime, tickets)) {
            show.availableTickets -= tickets;
            show.bookedTickets += tickets;
            return tickets + " tickets successfully booked for " + showTime;
        }
        return "Not enough tickets available for this showtime.";
    }

    @Override
    public final String cancelReservation(final String showTime,
                                          final int tickets) {
        Show show = shows.get(showTime);
        if (show == null) {
            return "Showtime not found.";
        }
        if (tickets <= show.getBookedTickets()) {
            show.availableTickets += tickets;
            show.bookedTickets -= tickets;
            return tickets + " tickets successfully canceled for " + showTime;
        }

        return "Invalid operation (Attempt to cancel more tickets"
                + " than booked).";
    }

    /**
     * Main method to demonstrate the functionality of the MovieBookingSystem.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        MovieBookingSystem system = new MovieBookingSystem();

        // Add shows using constants (no magic numbers)
        system.addShow(SHOW_MORNING, MORNING_TICKETS);
        system.addShow(SHOW_AFTERNOON, AFTERNOON_TICKETS);

        System.out.println("Test Case 1: Book tickets for a showtime");
        System.out.println(system.bookTicket(SHOW_MORNING, BOOK_FIVE));
        System.out.println(" ");
        // Expected: "5 tickets successfully booked for 10:00 AM"

        System.out.println("Test Case 2: Try booking more tickets"
            + "than available");
        System.out.println(system.bookTicket(SHOW_MORNING, BOOK_TOO_MANY));
        System.out.println(" ");
        // Expected: "Not enough tickets available for this showtime."

        System.out.println("Test Case 3: Cancel tickets for a showtime");
        System.out.println(system.cancelReservation(SHOW_MORNING,
            CANCEL_THREE));
        System.out.println(" ");
        // Expected: "3 tickets successfully canceled for 10:00 AM"

        System.out.println("Test Case 4: Book tickets for another showtime");
        System.out.println(system.bookTicket(SHOW_AFTERNOON, BOOK_TWO));
        System.out.println(" ");
        // Expected: "2 tickets successfully booked for 1:00 PM"

        System.out.println("Test Case 5: Try to cancel more tickets"
            + "than booked");
        System.out.println(system.cancelReservation(SHOW_AFTERNOON,
            CANCEL_TOO_MANY));
        System.out.println(" ");
        // Expected: "Invalid operation (Attempt to cancel
        // more tickets than booked)."
    }
}
