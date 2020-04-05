package com.google.sps;

/** An item on a todo list. */
public final class Event {

  private final long tokenID;
  private final int eventID;
  private final String djName;
  private final String eventName;
  private final String location;
  private final TimeRange timeRange;
  private final String eventDescription;

  public Event (long ID, int eventID, String djName, String eventName, String location, TimeRange timeRange, String eventDescription) {
    this.tokenID = ID;
    this.eventID = eventID;
    this.djName = djName;
    this.eventName = eventName;
    this.location = location;
    this.timeRange = timeRange;
    this.eventDescription = eventDescription;
  }
}