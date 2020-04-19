function getEvents() {
  fetch(`/events`).then(response => response.json()).then((events) => {
    const eventsElement = document.getElementById('events-container');

    eventsElement.innerHTML = "";
    console.log(events);
    for (i of events) {
      eventsElement.appendChild(createListElement(i.eventName));
    }
  });
}

function getSongs() {
  fetch('/songs').then(response => response.json()).then((songs) => {
    const songsElement = document.getElementById('songs-container');
    songsElement.innerHTML = "";
    console.log(songs);
    for (i of songs) {
      songsElement.appendChild(createListElement(i.songName));
    }
  });
}

function createListElement(event) {
  const element = document.createElement('li');
  element.innerText = event;
  return element;
}
