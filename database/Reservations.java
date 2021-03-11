package database;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

/**
 * This is my first implementation of Database. Notice how in the extends
 * statement I wrote ReservationItem, this sets the generic type of the datbase.
 * Also in the constructor I manually passed a string that will always be there
 * no matter when you initalized a Reservations database.
 */

public class Reservations extends Database<ReservationItem> {

  public Reservations() {
    super("reservations");
  }

  // Helper function
  private static String dateToString(Date d) {
    return String.valueOf((d.getTime()));
  }

  // Helper function
  private static Date stringToDate(String d) {
    return new Date(Long.parseLong(d));
  }

  /**
   * This function implements Database.serialize. In the original definition of
   * Database.serialize the type of the first parameter was a generic parameter.
   * Now that it has been set this new serialize function has to specify it
   * itself. This function simply creates a string array that represents a
   * ReservationItem, the underlying Database object will beable to freely call it
   * whenever it wants with whatever ReservationItem it wants.
   */
  @Override
  protected String[] serialize(ReservationItem data) throws NotImplementedException {
    return new String[] { Reservations.dateToString(data.getStartTime()), Reservations.dateToString(data.getEndTime()),
        data.getName(), data.getPhoneNumber(), data.getNumberOfPeople().toString(), data.getTable(), data.getNotes() };
  }

  /**
   * This isis the deserializer, it will create ReservationItems to be used by the
   * rest of the app. Notice how the indexes of data match up with the positions
   * of values in the serialize function. This is important to make sure the
   * Object enters and exits the database properly.
   */
  @Override
  protected ReservationItem deserialize(String[] data) {
    return new ReservationItem(Reservations.stringToDate(data[0]), Reservations.stringToDate(data[1]), data[2], data[3],
        Integer.parseInt(data[4]), data[5], data[6]);
  }

  // This is a simple database opperation to add a new reservation item
  public void createNewReservation(ReservationItem item) throws IOException {
    ArrayList<ReservationItem> existing = this.get(); // Load the database into active memory

    existing.add(item); // Add the reservation object

    this.set(existing); // Then save it, it's that easy
  }
}
