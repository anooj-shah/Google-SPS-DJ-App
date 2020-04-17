// function getEvents() {
//   fetch('/events').then(response => response.json()).then((events) => {
//     const eventsElement = document.getElementById('events-container');
//     eventsElement.innerHTML = "";
//     console.log(events);
//     for (i of events) {
//       eventsElement.appendChild(createListElement(i.eventName));
//     }
//   });
// }

// function getSongs() {
//   fetch('/songs').then(response => response.json()).then((songs) => {
//     const songsElement = document.getElementById('songs-container');
//     songsElement.innerHTML = "";
//     console.log(songs);
//     for (i of songs) {
//       songsElement.appendChild(createListElement(i.songName));
//     }
//   });
// }

// function createListElement(event) {
//   const element = document.createElement('li');
//   element.innerText = event;
//   return element;
// }



async function getEvents() {
    const response = await fetch('/events');
    const events = await response.json();

    const accessID = ""
    // function verifyAccessCode() {
    //     const verifiedAccess = document.getElementById('verified-access')
        const accessCode = document.getElementById('access-code').value
        console.log(accessCode);

        const eventName = document.getElementById('eventName');
        const eventID = document.getElementById('eventID');
        const eventDJ = document.getElementById('eventDJ');
        const errorMsg = document.getElementById("errorMsg")
    //     accessID = accessCode.value;

        count = 0
        events.forEach((event) => {
            if (event.eventID === accessCode){
                console.log(count); // if statement not running, tonights stop
                fillUserPage()
                window.location.assign = 'https://8080-dot-10844361-dot-devshell.appspot.com/userPage.html'
            }
            else{
                if (events.length < count){
                    count++
                    errorMsg.innerText = "incorrect access code, please enter access code"
                    return
                }
            }
            
        });

        function fillUserPage() {
            const eventName = document.getElementById('eventName');
            const eventID = document.getElementById('eventID');
            const eventDJ = document.getElementById('eventDJ');

            eventID.innerText = event.eventID
            eventName.innerText = event.eventName
            eventDJ.innerText = event.eventDJ
        }
        // verifiedAccess.innerText = "clientScreen.html"
        console.log(events);
    }

    
// }

async function getSongs() {
    const response = await fetch('/songs');
    const songs = await response.json();

    const songList = document.getElementById('song-list');
    songs.forEach((song) => {
        songList.appendChild(createListElement(song.songName));
        console.log(song.songName);
    });  
}

function createListElement(text) {
        const liElement = document.createElement('li');
        liElement.innerText = text;
        console.log(text);
        return liElement;
    }