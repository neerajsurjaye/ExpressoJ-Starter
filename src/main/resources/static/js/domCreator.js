import constants from "./notesConstants.js";
import restHelper from "./restHelper.js";

let genereateNavBar = () => {
    let navBar = document.getElementById("nav-bar");

    let isAuth = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE) != null;

    console.log({ isAuth });

    navBar.innerHTML = `
            <a href="/" class="nav-title nav-content"> <div>Notes</div> </a>

            ${
                !isAuth
                    ? `<a href="/html/login.html" class="to-right nav-btn nav-content">Log-in</a>
             <a href="/html/signup.html" class="nav-btn nav-content">Sign-up</a>`
                    : `<div id="sign-out-btn" class="to-right nav-btn nav-content">Sign out</div>`
            }
        `;
};

let generateNote = (note, generateNotesList) => {
    let cont = document.createElement("div");
    cont.classList.add("note-elem");

    let notesInfoCont = document.createElement("div");
    notesInfoCont.classList.add("notes-info-cont");

    let noteTitle = document.createElement("h3");
    noteTitle.innerText = note.title;
    noteTitle.classList.add("note-title");

    let noteDesc = document.createElement("div");
    noteDesc.innerText = note.body;
    noteDesc.classList.add("note-desc");

    notesInfoCont.appendChild(noteTitle);
    notesInfoCont.appendChild(noteDesc);

    let removeNoteBtn = document.createElement("input");
    removeNoteBtn.classList.add("note-remove-btn");
    removeNoteBtn.type = "button";
    removeNoteBtn.value = "X";
    removeNoteBtn.addEventListener("click", async () => {
        let jwt = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE);
        let uuid = note.uuid;
        await restHelper.httpAuthReqWithJsonResp(
            "/api/notes",
            "DELETE",
            { uuid },
            jwt
        );
        generateNotesList();
    });

    cont.appendChild(notesInfoCont);
    cont.appendChild(removeNoteBtn);

    return cont;
};

let generateErrorMessageElement = (msg) => {
    let errorMsgCont = document.createElement("div");
    errorMsgCont.classList.add("error-msg-cont");

    errorMsgCont.innerText = msg;
    return errorMsgCont;
};

let showErrorModal = (msg) => {
    let modalCont = document.getElementById("modal-cont");

    modalCont.classList.add("modal-status-error");
    modalCont.textContent = msg;
};

let showSuccessModal = (msg) => {
    let modalCont = document.getElementById("modal-cont");

    modalCont.classList.add("modal-status-success");
    modalCont.textContent = msg;
};

const domCreator = {
    genereateNavBar,
    generateNote,
    generateErrorMessageElement,
    showErrorModal,
    showSuccessModal,
};

export default domCreator;
