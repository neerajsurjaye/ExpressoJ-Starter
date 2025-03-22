import constants from "./notesConstants.js";
import domCreator from "./domCreator.js";
import restHelper from "./restHelper.js";

let getFormInputs = () => {
    let usernameInput = document.getElementById("user-form-username");
    let passwordInput = document.getElementById("user-form-password");

    return { usernameInput, passwordInput };
};

let handleSignUp = async () => {
    let formInputs = getFormInputs();

    let username = formInputs.usernameInput.value;
    let password = formInputs.passwordInput.value;

    let json = await restHelper.httpReqWithJsonResp(
        "/auth/signup",
        {
            username,
            password,
        },
        "POST"
    );

    let jwtToken = json?.jwt;

    setJwt(jwtToken);
};

let handleSignUpSubmitBtn = () => {
    let submitBtn = document.getElementById("signup-submit-btn");
    if (!submitBtn) return;
    submitBtn.addEventListener("click", (e) => {
        handleSignUp();
    });
};

let handleSignOut = () => {
    let signOutElement = document.getElementById("sign-out-btn");

    console.log(signOutElement);
    if (!signOutElement) return;

    signOutElement.addEventListener("click", () => {
        localStorage.removeItem(constants.AUTH_KEY_LOCALSTORAGE);
        location.reload();
    });
};

let setJwt = (jwtToken) => {
    console.log(jwtToken);

    if (jwtToken) {
        localStorage.setItem(constants.AUTH_KEY_LOCALSTORAGE, jwtToken);
    }
};

let handleLogin = async () => {
    let formInputs = getFormInputs();

    let username = formInputs.usernameInput.value;
    let password = formInputs.passwordInput.value;

    console.log({ username, password });

    let json = await restHelper.httpReqWithJsonResp(
        "/auth/login",
        { username, password },
        "POST"
    );

    let jwtToken = json?.jwt;

    setJwt(jwtToken);
};

let handleLoginSubmitBtn = () => {
    let submitBtn = document.getElementById("login-submit-btn");
    if (submitBtn == null) return;
    submitBtn.addEventListener("click", () => {
        handleLogin();
    });
};

let getAllNotesForCurrentUser = async () => {
    let jwt = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE);
    let notesCont = document.getElementById("notes-cont");
    if (!notesCont) return;

    let json = await restHelper.httpAuthReqWithJsonResp(
        "/api/notes",
        "GET",
        null,
        jwt
    );

    if (json.status == "success") {
        return json.notes;
    }

    return [];
};

let addNote = async () => {
    let notesTitle = document.getElementById("notes-form-title").value;
    let notesDesc = document.getElementById("notes-form-desc").value;

    let body = {
        note: {
            title: notesTitle,
            body: notesDesc,
        },
    };

    const authToken = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE);

    let json = await restHelper.httpAuthReqWithJsonResp(
        "/api/notes",
        "POST",
        body,
        authToken
    );

    console.log(`Added note : ${JSON.stringify(json)}`);
};

let handleSubmitNotesBtn = () => {
    let submitNotesBtn = document.getElementById("notes-submit-btn");
    if (!submitNotesBtn) return;
    submitNotesBtn.addEventListener("click", async () => {
        await addNote();
        await generateNotesList();
    });
};

let generateNotesList = async () => {
    let notesContainer = document.getElementById("notes-cont");

    notesContainer.innerHTML = "";

    let notesList = await getAllNotesForCurrentUser();

    for (let key in notesList) {
        let currNote = notesList[key];
        let noteDomElement = domCreator.generateNote(
            currNote,
            generateNotesList
        );
        notesContainer.appendChild(noteDomElement);
    }
};

let notesUtils = () => {
    domCreator.genereateNavBar();

    handleSignUpSubmitBtn();
    handleSignOut();
    handleLoginSubmitBtn();
    getAllNotesForCurrentUser();
    handleSubmitNotesBtn();
    generateNotesList();
};

notesUtils();
