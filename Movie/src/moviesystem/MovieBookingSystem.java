package moviesystem;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {
  private static class Show {
    String showTime;
    int availableTickets;
    int bookedTickets;

    Show(final String showTime, int availableTickets) {
        this.showTime = showTime;
        this.availableTickets = availableTickets;
        this.bookedTickets = 0;
    }
}

    private ArrayList<Show> shows = new ArrayList<>();

    // Add showtime with available tickets
    public void addShow(String showTime, int tickets) {
        shows.add(new Show(showTime, tickets));
    }

    private Show findShow(final String showTime) {
        for (Show show : shows) {
            if (show.showTime.equals(showTime)) {
                return show;
            }
        }
        return null;
    }

    @Override
    public final boolean checkAvailability(final String showTime,
        final int tickets) {
        Show show = findShow(showTime);
        return show != null && show.availableTickets >= tickets;
    }

    @Override
    public final String bookTicket(final String showTime, final int tickets) {
        Show show = findShow(showTime);
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
        Show show = findShow(showTime);
        if (show == null) {
            return "Showtime not found.";
        }
        if (tickets <= show.bookedTickets) {
            show.availableTickets += tickets;
            show.bookedTickets -= tickets;
            return tickets + " tickets successfully canceled for " + showTime;
        }
        return "Invalid operation (Attempt to cancel more tickets "
            + "than booked).";
    }

//    public static void main(final String[] args) {
//      MovieBookingSystem mbs = new MovieBookingSystem();
//      mbs.addShow("10:00 AM", 50);
//      mbs.addShow("1:00 PM", 20);
//
//      // Test Case 1
//      System.out.println(mbs.bookTicket("10:00 AM", 5));
//
//      // Test Case 2
//      System.out.println(mbs.bookTicket("10:00 AM", 100));
//
//      // Test Case 3
//      System.out.println(mbs.cancelReservation("10:00 AM", 3));
//
//      // Test Case 4
//      System.out.println(mbs.bookTicket("1:00 PM", 2));
//
//      // Test Case 5
//      System.out.println(mbs.cancelReservation("1:00 PM", 5));
//  }
    }
