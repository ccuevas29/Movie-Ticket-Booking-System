import java.util.HashMap;

// Abstract base class
abstract class BookingSystem {
    public abstract boolean checkAvailability(String showTime, int tickets);
    public abstract String bookTicket(String showTime, int tickets);
    public abstract String cancelReservation(String showTime, int tickets);
}

// Concrete subclass
class MovieBookingSystem extends BookingSystem {
    private static class Show {
        int availableTickets;
        int bookedTickets;

        Show(int availableTickets) {
            this.availableTickets = availableTickets;
            this.bookedTickets = 0;
        }
    }

    private HashMap<String, Show> shows = new HashMap<>();

    // Add a new showtime with available tickets
    public void addShow(String showTime, int tickets) {
        shows.put(showTime, new Show(tickets));
    }

    @Override
    public boolean checkAvailability(String showTime, int tickets) {
        Show show = shows.get(showTime);
        return show != null && show.availableTickets >= tickets;
    }

    @Override
    public String bookTicket(String showTime, int tickets) {
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
    public String cancelReservation(String showTime, int tickets) {
        Show show = shows.get(showTime);
        if (show == null) {
            return "Showtime not found.";
        }
        if (tickets <= show.bookedTickets) {
            show.availableTickets += tickets;
            show.bookedTickets -= tickets;
            return tickets + " tickets successfully canceled for " + showTime;
        }
        return "Invalid operation (Attempt to cancel more tickets than booked).";
    }
}
