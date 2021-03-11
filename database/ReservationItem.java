package database;

import java.util.Date;

public class ReservationItem {
  private Date reservedTimeStart;
  private Date reservedTimeEnd;
  private String name;
  private String phoneNumber;
  private Integer numberOfPeople;
  private String table;
  private String notes;

  public ReservationItem(Date start, Date end, String name, String phoneNumber, Integer numberOfPeople, String table,
      String notes) {
    this.reservedTimeStart = start;
    this.reservedTimeEnd = end;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.numberOfPeople = numberOfPeople;
    this.table = table;
    this.notes = notes;
  }

  public Date getStartTime() {
    return this.reservedTimeStart;
  }

  public Date getEndTime() {
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
