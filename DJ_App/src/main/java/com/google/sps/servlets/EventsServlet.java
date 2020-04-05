
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
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Event").addSort("eventName", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Event> events = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      int eventID = (int) entity.getProperty("eventID");
      String djName = (String) entity.getProperty("djName");
      String eventName = (String) entity.getProperty("eventName");
      String location = (String) entity.getProperty("location");
      TimeRange timeRange = (TimeRange) entity.getProperty("timeRange")
      String eventDescription = (String) entity.getProperty("eventDescription");

      Event e = new Event(id, event_id, djName, eventName, location, timeRange, eventDescription);

      events.add(e);
    }
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(events));
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Data storage
    Entity eventEntity = new Entity("Event");
    eventEntity.setProperty("eventID", 1234);
    eventEntity.setProperty("djName", request.getParameter("djName"));
    eventEntity.setProperty("eventName", request.getParameter("eventName"));
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
