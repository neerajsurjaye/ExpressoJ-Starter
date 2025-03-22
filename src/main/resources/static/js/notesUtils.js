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

let showAllNotesForCurrUser = async () => {
    let jwt = localStorage.getItem(constants.AUTH_KEY_LOCALSTORAGE);
    let notesCont = document.getElementById("notes-cont");
    if (!notesCont) return;

    let json = await restHelper.httpAuthReqWithJsonResp(
        "/api/notes",
        "GET",
        null,
        jwt
    );

    console.log(JSON.stringify(json));
};

let notesUtils = () => {
    domCreator.genereateNavBar();

    handleSignUpSubmitBtn();
    handleSignOut();
    handleLoginSubmitBtn();
    showAllNotesForCurrUser();
};

notesUtils();
