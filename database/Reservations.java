package database;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

public class Reservations extends Database<ReservationItem> {

  public Reservations() {
    super("reservations");
  }

  private static String dateToString(Date d) {
    return String.valueOf((d.getTime()));
  }

  private static Date stringToDate(String d) {
    return new Date(Long.parseLong(d));
  }

  @Override
  protected String[] serialize(ReservationItem data) throws NotImplementedException {
    return new String[] { Reservations.dateToString(data.getStartTime()), Reservations.dateToString(data.getEndTime()),
        data.getName(), data.getPhoneNumber(), data.getNumberOfPeople().toString(), data.getTable(), data.getNotes() };
  }

  @Override
  protected ReservationItem deserialize(String[] data) throws NotImplementedException {
    return new ReservationItem(Reservations.stringToDate(data[0]), Reservations.stringToDate(data[1]), data[2], data[3],
        Integer.parseInt(data[4]), data[5], data[6]);
  }

  public void createNewReservation(ReservationItem item) throws NotImplementedException, IOException {
    ArrayList<ReservationItem> existing = this.get();

    existing.add(item);

    this.set(existing);
  }
}
