
package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.sps.data.Song;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

// Persistent storage for songs created by DJ's

@WebServlet("/songs")
public class SongsServlet extends HttpServlet {
  
  @Override
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Song").addSort("songName", SortDirection.DESCENDING);

    DatastoreService datastoreSongs = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastoreSongs.prepare(query);

    List<Song> songs = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      long songID = (long) entity.getProperty("songID");
      //long eventID = Long.parseLong((String)entity.getProperty("eventID"));
      long eventID = (long)entity.getProperty("eventID");
      String songName = (String) entity.getProperty("songName");
      long songVotes = (long) entity.getProperty("songVotes");

      Song s = new Song(id, songID, eventID, songName, songVotes);
      songs.add(s);
      System.out.println("Songs: " + songs);
    }
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(songs));
  }


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println("Inside Post: " + request.getParameter("songName"));

    // Data storage
    Entity songEntity = new Entity("Song");
    songEntity.setProperty("songID", 4321);
    //System.out.println(Integer.parseInt(request.getParameter("eventID")));
    songEntity.setProperty("eventID", Integer.parseInt(request.getParameter("eventID")));
    songEntity.setProperty("songName", request.getParameter("songName"));
    songEntity.setProperty("songVotes", 1);
    
    DatastoreService datastoreSongs = DatastoreServiceFactory.getDatastoreService();
    datastoreSongs.put(songEntity);

    // Stay on same page
    response.sendRedirect("/");
  }
}
