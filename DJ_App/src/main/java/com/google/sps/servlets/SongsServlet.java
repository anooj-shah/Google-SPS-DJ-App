
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
import java.util.Set;
import java.util.HashSet;

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

    // Retrieving information from datastore
    Query query = new Query("Song");
    DatastoreService datastoreSongs = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastoreSongs.prepare(query);

    String songName = (String)request.getParameter("songName");
    long eventID = Long.parseLong(request.getParameter("eventID"));
    boolean present = false;

    System.out.println(songName);
    System.out.println(eventID);

    for (Entity curr : results.asIterable()) {
        if ((((String)curr.getProperty("songName")).equals(songName)) &&
        ((long)curr.getProperty("eventID") == eventID)) {
            // existing event and existing song
            long songVotes = (long) curr.getProperty("songVotes") + 1;
            curr.setProperty("songVotes", songVotes);
            present = true;
            datastoreSongs.put(curr);
            break;
        }
    }

    if (!present) {
        // create new song entity
        Set<Long> songIDs = new HashSet<Long>();
        for (Entity curr : results.asIterable()) {
            songIDs.add((long)curr.getProperty("songID"));
        }
        long songID = 10000;
        while (songIDs.contains(songID)){
            songID = Math.round(Math.random() * 10000);
        }
        Entity songEntity = new Entity("Song", songID);
        songEntity.setProperty("songID", songID);
        songEntity.setProperty("eventID", eventID);
        songEntity.setProperty("songName", songName);
        songEntity.setProperty("songVotes", 1);
        datastoreSongs.put(songEntity);
    }

    // Stay on same page
    response.sendRedirect("/");
  }
}
