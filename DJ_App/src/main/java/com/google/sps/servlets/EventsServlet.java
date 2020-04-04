
package com.google.sps;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Persistent storage for events created by DJ's

// TODO1: Make TimeRange work
// TODO2: Make sure events storage are persistent?
@WebServlet("/events")
public class EventsServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Data storage
    Entity eventEntity = new Entity("Event");
    eventEntity.setProperty("djName", request.getParameter("djName"));
    eventEntity.setProperty("location", request.getParameter("location"));
    //TimeRange time = TimeRange.fromStartEnd(Integer.valueOf(request.getParameter("startTime")), Integer.valueOf(request.getParameter("endTime")), true);
    //TimeRange time = TimeRange.WHOLE_DAY;
    //eventEntity.setProperty("timeRange", time);
    eventEntity.setProperty("eventDescription", request.getParameter("eventDescription"));
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(eventEntity);

    // Stay on same page
    response.sendRedirect("/");
  }
}
