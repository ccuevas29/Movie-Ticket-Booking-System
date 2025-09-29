//CODE REVIEWER: LOYSCE
package moviesystem;

/**
 * Abstract class representing a movie booking system.
 * Provides methods to check availability, book tickets,
 * and cancel reservations.
 */
public abstract class BookingSystem {

  /**
   * Checks if the requested number of tickets is
   * available for a given showtime.
   * @param showTime the time of the show
   * @param tickets the number of tickets requested
   * @return true if available, false otherwise
   */
  public abstract boolean checkAvailability(String showTime, int tickets);

  /**
   * Books the specified number of tickets for a given showtime.
   * @param showTime the time of the show
   * @param tickets the number of tickets to book
   * @return confirmation message of the booking
   */
  public abstract String bookTicket(String showTime, int tickets);

  /**
   * Cancels the reservation for the specified
   * number of tickets at a given showtime.
   * @param showTime the time of the show
   * @param tickets the number of tickets to cancel
   * @return confirmation message of the cancellation
   */
  public abstract String cancelReservation(String showTime, int tickets);
}
