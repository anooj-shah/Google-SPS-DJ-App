package com.google.sps;

/** An item on a todo list. */
public final class Event {

  private final long tokenID;
  private final String djName;
  private final String location;
  private final TimeRange timeRange;
  private final String eventDescription;

  public Event (long ID, String djName, String location, TimeRange timeRange, String eventDescription) {
    this.tokenID = ID;
    this.djName = djName;
    this.location = location;
    this.timeRange = timeRange;
    this.eventDescription = eventDescription;
  }
}