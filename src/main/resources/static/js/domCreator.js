import constants from "./notesConstants.js";

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

const domCreator = {
    genereateNavBar,
};

export default domCreator;
