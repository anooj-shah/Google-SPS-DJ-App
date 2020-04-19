function getEvents() {
  fetch('/events').then(response => response.json()).then((events) => {
    const eventsElement = document.getElementById('events-container');

    eventsElement.innerHTML = "";
    console.log(events);
    for (i of events) {
      eventsElement.appendChild(createListElement(i.eventName));
    }
  });
}

function getInfo() {
  fetch('/events').then(response => response.json()).then((events) => {
    console.log("INSIDE GET INFO");
    // const eventID = document.getElementById('eventID').submit();
    // console.log("EVENTID" + eventID);
    const eventIDNum = document.getElementById('eventIDNum');
    eventIDNum.innerHTML = "";
    console.log("EVENT" + events[0].eventID);
    eventIDNum.appendChild(createListElement(events[0].eventID));

    const eventDJ = document.getElementById('eventDJ');
    eventDJ.innerHTML = "";
    console.log("EVENT" + events[0].djName);
    eventDJ.appendChild(createListElement(events[0].djName));

    const eventName = document.getElementById('eventName');
    eventName.innerHTML = "";
    console.log("EVENT" + events[0].eventName);
    eventName.appendChild(createListElement(events[0].eventName));
  });
}

function createListElement(event) {
  const element = document.createElement('li');
  element.innerText = event;
  return element;
}

async function getSongs() {
    const response = await fetch('/songs');
    const songs = await response.json();

    const songList = document.getElementById('song-list');
    songs.forEach((song) => {
        songList.appendChild(createListElement(song.songName));
        console.log(song.songName);
    });  
}

function getGenre() {
  // location is genre
  fetch('/events').then(response => response.json()).then((events) => {
    const genrelist = document.getElementById('genre-list');
    genrelist.innerHTML = "";
    console.log("Location" + events[0].location);
    genrelist.appendChild(createListElement(events[0].location));
  });
}

function updateData() {
    getInfo();
    getSongs();
    getGenre();
}
