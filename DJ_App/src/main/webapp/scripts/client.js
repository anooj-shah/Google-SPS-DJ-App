async function getEvents() {
    const response = await fetch('/events');
    const events = await response.json();

    const accessID = ""
    function verifyAccessCode() {
        const verifiedAccess = document.getElementById('verified-access')
        const accessCode = document.getElementById('access-code')
        const eventName = document.getElementById('eventName');
        const eventID = document.getElementById('eventID');
        const eventDJ = document.getElementById('eventDJ');
        const errorMsg = document.getElementById("errorMsg")
        accessID = accessCode.value;

        events.forEach((event) => {
            if (event.eventID === accessID){
                eventID.innerText = event.eventID
                eventName.innerText = event.eventName
                eventDJ.innerText = event.eventDJ
            }
            else{
                errorMsg.innerText = "incorrect access code, please enter access code"
            }
            console.log(event.eventName);
        });
        verifiedAccess.innerText = "clientScreen.html"
        console.log(accessID);
    }

    
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

function createListElement(text) {
        const liElement = document.createElement('li');
        liElement.innerText = text;
        console.log(text);
        return liElement;
    }