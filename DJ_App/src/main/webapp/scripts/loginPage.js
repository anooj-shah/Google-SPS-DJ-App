const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

function createEventForm() {
    console.log("create event");
    document.getElementById("createEvent").hidden = true;
    document.getElementById("joinEvent").hidden = true;
}

function joinEventForm() {
    console.log("join event");
    document.getElementById("joinEvent").hidden = false;
    document.getElementById("createEvent").hidden = true;
}