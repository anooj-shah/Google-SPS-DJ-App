package com.google.sps.data;

public final class Event {

  private final long tokenID;
  private final long eventID;
  private final String djName;
  private final String eventName;
  private final String location;
  private final String timeRange;
  private final String eventDescription;

  public Event (long ID, long eventID, String djName, String eventName, String location, String timeRange, String eventDescription) {
    this.tokenID = ID;
    this.eventID = eventID;
    this.djName = djName;
    this.eventName = eventName;
    this.location = location;
    this.timeRange = timeRange;
    this.eventDescription = eventDescription;
  }
}