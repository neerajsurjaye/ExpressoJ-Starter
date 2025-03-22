import constants from "./notesConstants.js";
import restHelper from "./restHelper.js";

let genereateNavBar = () => {
    let navBar = document.getElementById("nav-bar");

    let isAuth = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE) != null;

    console.log({ isAuth });

    navBar.innerHTML = `
            <a href="/"> <div>Notes</div> </a>

            ${
                !isAuth
                    ? `<a href="/html/login.html">Log-in</a>
             <a href="/html/signup.html">Sign-up</a>`
                    : `<div id="sign-out-btn">Sign out</div>`
            }
        `;
};

let generateNote = (note, generateNotesList) => {
    let cont = document.createElement("div");
    cont.classList.add("note-elem");

    let noteTitle = document.createElement("h3");
    noteTitle.innerText = note.title;
    noteTitle.classList.add("note-title");

    let noteDesc = document.createElement("div");
    noteDesc.innerText = note.body;
    noteDesc.classList.add("note-desc");

    let removeNoteBtn = document.createElement("input");
    removeNoteBtn.type = "button";
    removeNoteBtn.value = "Remove Note";
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

    cont.appendChild(noteTitle);
    cont.appendChild(noteDesc);
    cont.appendChild(removeNoteBtn);

    return cont;
};

const domCreator = {
    genereateNavBar,
    generateNote,
};

export default domCreator;
