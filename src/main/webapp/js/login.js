import {initializeApp} from "https://www.gstatic.com/firebasejs/9.8.0/firebase-app.js";
import {getDatabase, set, ref, update} from "https://www.gstatic.com/firebasejs/9.8.0/firebase-database.js";
import {
    getAuth,
    createUserWithEmailAndPassword,
    signInWithEmailAndPassword,
    onAuthStateChanged,
    signOut
} from "https://www.gstatic.com/firebasejs/9.8.0/firebase-auth.js";

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyAUOrSjeSbS44wdOAl-4vrR-4C9gGVmxNQ",
    authDomain: "movieholic-72a5e.firebaseapp.com",
    databaseURL: "https://movieholic-72a5e-default-rtdb.europe-west1.firebasedatabase.app",
    projectId: "movieholic-72a5e",
    storageBucket: "movieholic-72a5e.appspot.com",
    messagingSenderId: "822798284403",
    appId: "1:822798284403:web:ac600428a2cd52cfebdc06"
};

const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
const auth = getAuth(app);


signUp.addEventListener("click", (e) => {
    let email = document.getElementById("tfRegisterEmail").value;
    let password1 = document.getElementById("tfRegisterPassword1").value;
    let password2 = document.getElementById("tfRegisterPassword2").value;

    if (password1 === password2) {
        createUserWithEmailAndPassword(auth, email, password1)
            .then((userCredential) => {
                const user = userCredential.user;
                set(ref(database, 'users/' + user.uid), {
                    email: email
                });
                $('#registerModal').modal('hide');
                clearLoginModal();
            })
            .catch((error) => {
                const errorCode = error.code;
                const errorMessage = error.message;
                console.log("Error: " + errorMessage);
                console.log(errorCode);
            });
    }
});

signIn.addEventListener("click", (e) => {
    let email = document.getElementById("tfLoginEmail").value;
    let password = document.getElementById("tfLoginPassword").value;

    signInWithEmailAndPassword(auth, email, password)
        .then((userCredential) => {
            const user = userCredential.user;

            const dt = new Date();
            update(ref(database, 'users/' + user.uid), {
                last_login: dt
            });

            $('#loginModal').modal('hide');
            clearLoginModal();
        })
        .catch((error) => {
            const errorCode = error.code;
            const errorMessage = error.message;
            console.log("Error: " + errorMessage);

            if (errorCode === 'auth/invalid-email') {

            }
        });
});

const user = auth.currentUser;
onAuthStateChanged(auth, (user) => {
    if (user) {
        const uid = user.uid;

        let navbarContent = document.getElementById("navbarContent");
        let child = document.getElementById("loginSector");
        navbarContent.removeChild(child);
        navbarContent.innerHTML += '<div class="dropdown loginDropdown" id="loginSector">\n' +
            '  <button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">\n' +
            user.email + '\n' +
            '  </button>\n' +
            '  <ul class="dropdown-menu">\n' +
            '    <li><a class="dropdown-item" href="#">Account</a></li>\n' +
            '    <li><a class="dropdown-item btn" onclick="openSettings();">Settings</a></li>\n' +
            '    <li id="logout"><a class="dropdown-item" href="#">Logout</a></li>\n' +
            '  </ul>\n' +
            '</div>';
        let log = document.getElementById("logout");
        log.addEventListener("click", (() => {
            signOut(auth).then(() => {
                console.log("logout");
            }).catch((error) => {
                console.log(error);
            });
        }));
    } else {
        console.log("User is signed out!");
        let navbarContent = document.getElementById("navbarContent");
        let child = document.getElementById("loginSector");
        navbarContent.removeChild(child);

        navbarContent.innerHTML += '<div class="nav-item navLogin" data-bs-toggle="modal" data-bs-target="#loginModal" id="loginSector">\n' +
            '            <span id="loginSpan">Login</span>\n' +
            '            <svg id="loginImg" xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"\n' +
            '                 class="bi bi-person-plus" viewBox="0 0 16 16">\n' +
            '                <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>\n' +
            '                <path fill-rule="evenodd"\n' +
            '                      d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>\n' +
            '            </svg>\n' +
            '        </div>';
    }
});
