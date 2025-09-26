package moviesystem;

import java.util.HashMap;

/**
 * Concrete implementation of BookingSystem for managing movie showtimes.
 * Allows adding shows, booking tickets, and canceling reservations.
 */
public class MovieBookingSystem extends BookingSystem {

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
         * @param initialTickets total tickets initially available
         */
        Show(final int initialTickets) {
            this.availableTickets = initialTickets;
            this.bookedTickets = 0;
        }

        /**
         * Gets the number of tickets available for booking.
         * @return available ticket count
         */
        int getAvailableTickets() {
            return availableTickets;
        }

        /**
         * Gets the number of tickets that have been booked.
         * @return booked ticket count
         */
        int getBookedTickets() {
            return bookedTickets;
        }
    }

    /** Stores showtimes mapped to their ticket availability. */
    private HashMap<String, Show> shows = new HashMap<>();

    /**
     * Adds a new showtime with the specified number of available tickets.
     * @param showTime the time of the show
     * @param tickets the number of tickets available
     */
    public void addShow(final String showTime, final int tickets) {
        shows.put(showTime, new Show(tickets));
    }

    /**
     * Checks if the requested number of tickets is available
     * for a given showtime.
     * @param showTime the time of the show
     * @param tickets the number of tickets requested
     * @return true if enough tickets are available, false otherwise
     */
    @Override
    public final boolean checkAvailability(final String showTime,
        final int tickets) {
        Show show = shows.get(showTime);
        return show != null && show.getAvailableTickets() >= tickets;
    }

    /**
     * Books the specified number of tickets for a given showtime.
     * @param showTime the time of the show
     * @param tickets the number of tickets to book
     * @return confirmation message of booking or error if unavailable
     */
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

    /**
     * Cancels the specified number of booked tickets for a given showtime.
     * @param showTime the time of the show
     * @param tickets the number of tickets to cancel
     * @return confirmation message of cancellation or error if invalid
     */
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
}
