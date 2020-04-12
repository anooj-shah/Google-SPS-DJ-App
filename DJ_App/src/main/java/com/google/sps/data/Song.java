package com.google.sps.data;

public final class Song {
    private final long tokenID;
    private final long songID;
    private final long eventID;
    private final String songName;
    private final long songVotes;

    public Song (long tokenID, long songID, long eventID, String songName, long songVotes){
        this.tokenID = tokenID;
        this.songID = songID;
        this.eventID = eventID;
        this.songName = songName;
        this.songVotes = songVotes;
    }
}