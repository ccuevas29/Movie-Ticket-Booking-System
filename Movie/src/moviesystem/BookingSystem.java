package moviesystem;

public abstract class BookingSystem {
  public abstract boolean checkAvailability(String showTime, int tickets);
  public abstract String bookTicket(String showTime, int tickets);
  public abstract String cancelReservation(String showTime, int tickets);
}
