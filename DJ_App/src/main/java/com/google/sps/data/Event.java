package com.google.sps.data;

public final class Event {

  private final long tokenID;
  private final String accessCode;
  private final String djName;
  private final String eventName;
  private final String location;
  private final String timeRange;
  private final String startTime;
  private final String endTime;
  private final String eventDescription;

  public Event (long ID, String accessCode, String djName, String eventName, String location, String timeRange, String startTime, String endTime, String eventDescription) {
    this.tokenID = ID;
    this.accessCode = accessCode;
    this.djName = djName;
    this.eventName = eventName;
    this.location = location;
    this.timeRange = timeRange;
    this.startTime = startTime;
    this.endTime = endTime;
    this.eventDescription = eventDescription;
  }
}