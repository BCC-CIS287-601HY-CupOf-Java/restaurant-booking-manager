package database;

import java.util.Calendar;
import java.util.Date;

/**
 * This is a simple data structure item with readonly getters. This is also
 * meant to be used to create database items too
 */

public class ReservationItem extends ItemID {
  private Calendar reservedTimeStart;
  private Calendar reservedTimeEnd;
  private String name;
  private String phoneNumber;
  private Integer numberOfPeople;
  private String table;
  private String notes;

  public ReservationItem(Date start, Date end, String name, String phoneNumber, Integer numberOfPeople, String table,
      String notes) {
    this(start, end, name, phoneNumber, numberOfPeople, table, notes, ItemID.genID());
  }

  public ReservationItem(Date start, Date end, String name, String phoneNumber, Integer numberOfPeople, String table,
      String notes, String id) {
    super();

    this.reservedTimeStart = Calendar.getInstance();
    this.reservedTimeStart.setTime(start);
    this.reservedTimeEnd = Calendar.getInstance();
    this.reservedTimeEnd.setTime(end);
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.numberOfPeople = numberOfPeople;
    this.table = table;
    this.notes = notes;
    this.id = id;
  }

  public Calendar getStartTime() {
    return this.reservedTimeStart;
  }

  public Calendar getEndTime() {
    return this.reservedTimeEnd;
  }

  public String getName() {
    return this.name;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public Integer getNumberOfPeople() {
    return this.numberOfPeople;
  }

  public String getTable() {
    return this.table;
  }

  public String getNotes() {
    return this.notes;
  }

}
