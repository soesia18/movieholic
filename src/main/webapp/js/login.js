import { initializeApp } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-app.js";
import { getDatabase, set, ref, update } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-database.js";
import { getAuth, createUserWithEmailAndPassword, signInWithEmailAndPassword, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-auth.js";

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

    if (password1 === password2){
        createUserWithEmailAndPassword(auth, email, password1)
            .then((userCredential) => {
                const user = userCredential.user;
                set(ref(database, 'users/' + user.uid), {
                    email: email
                });
                $('#registerModal').modal('hide');
            })
            .catch((error) => {
                const errorCode = error.code;
                const errorMessage = error.message;
                console.log("Error: " + errorMessage);
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
        })
        .catch((error) => {
            const errorCode = error.code;
            const errorMessage = error.message;

            console.log("Error: " + errorMessage);
        });
});

const user = auth.currentUser;
onAuthStateChanged(auth, (user) => {
    if (user) {
        const uid = user.uid;

        let navbarContent = document.getElementById("navbarContent");
        let child = document.getElementById("loginSector");
        navbarContent.removeChild(child);
        navbarContent.innerHTML += '<div class="dropdown">\n' +
            '  <button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">\n'+
            user.email + '\n' +
            '  </button>\n' +
            '  <ul class="dropdown-menu">\n' +
            '    <li><a class="dropdown-item" href="#">Account</a></li>\n' +
            '    <li><a class="dropdown-item" href="#">Settings</a></li>\n' +
            '    <li onclick="logout()"><a class="dropdown-item" href="#">Logout</a></li>\n' +
            '  </ul>\n' +
            '</div>';
    } else {
        console.log("User is signed out!");
    }
});

function logout(){
    console.log("logout");
}
