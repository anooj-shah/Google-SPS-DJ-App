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

function createListElement(event) {
  const element = document.createElement('li');
  element.innerText = event;
  return element;
}
