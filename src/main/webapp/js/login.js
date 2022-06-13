import { initializeApp } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-app.js";
import { getDatabase, set, ref, update } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-database.js";
import { getAuth, createUserWithEmailAndPassword, signInWithEmailAndPassword, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-auth.js";
import { getFirestore, doc, setDoc, getDoc, updateDoc } from "https://www.gstatic.com/firebasejs/9.8.0/firebase-firestore.js";

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
const db = getFirestore(app);


signUp.addEventListener("click", (e) => {
    let registerEmailInfo = document.getElementById("registerEmailInfo");
    let registerPasswordInfo = document.getElementById("registerPasswordInfo");

    registerEmailInfo.innerHTML = "";
    registerPasswordInfo.innerHTML = "";

    let email = document.getElementById("tfRegisterEmail").value;
    let password1 = document.getElementById("tfRegisterPassword1").value;
    let password2 = document.getElementById("tfRegisterPassword2").value;

    if (password1 === password2){
        createUserWithEmailAndPassword(auth, email, password1)
            .then(async (userCredential) => {

                const user = userCredential.user;


                await setDoc(doc(db, "users", user.uid), {
                    email: user.email,
                    homepage: {
                        trending: true,
                        nowplaying: true,
                        toprated: true,
                        upcoming: true,
                    }
                });

                $('#registerModal').modal('hide');
                clearLoginModal();
            })
            .catch((error) => {
                const errorCode = error.code;
                const errorMessage = error.message;
                console.log("Error: " + errorMessage);

                console.log(errorCode);

                if (errorCode === "auth/email-already-in-use"){
                    registerEmailInfo.innerHTML = "Email already in use";
                }else if(errorCode === "auth/invalid-email"){
                    registerEmailInfo.innerHTML = "Invalid email";
                }else if(errorCode === "auth/weak-password"){
                    registerPasswordInfo.innerHTML = "Password is too weak";
                }
            });
    }
});

signIn.addEventListener("click", (e) => {
    let loginEmailInfo = document.getElementById("loginEmailInfo");
    let loginPasswordInfo = document.getElementById("loginPasswordInfo");

    let email = document.getElementById("tfLoginEmail").value;
    let password = document.getElementById("tfLoginPassword").value;

    loginEmailInfo.innerHTML = "";
    loginPasswordInfo.innerHTML = "";

    signInWithEmailAndPassword(auth, email, password)
        .then(async (userCredential) => {
            const user = userCredential.user;
            const dt = new Date();

            await updateDoc(doc(db, "users", user.uid), {
                lastLogin: dt.toString()
            });

            $('#loginModal').modal('hide');
            clearLoginModal();
        })
        .catch((error) => {
            const errorCode = error.code;
            const errorMessage = error.message;
            console.log("Error: " + errorMessage);

            if (errorCode === 'auth/invalid-email'){
                loginEmailInfo.innerHTML = 'Invalid email';
            } else if (errorCode === 'auth/user-not-found'){
                loginEmailInfo.innerHTML = 'User not found';
            }else if(errorCode === 'auth/wrong-password'){
                loginPasswordInfo.innerHTML = 'Wrong password';
            }else if(errorCode === 'auth/user-disabled'){
                loginEmailInfo.innerHTML = 'User disabled';
            }
        });
});

const user = auth.currentUser;
onAuthStateChanged(auth, async (user) => {
    if (user) {
        const uid = user.uid;


        showRightHomePage(uid);


        let navbarContent = document.getElementById("navbarContent");
        let child = document.getElementById("loginSector");
        navbarContent.removeChild(child);
        navbarContent.innerHTML += '<div class="dropdown loginDropdown" id="loginSector">\n' +
            '  <a type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown">\n' +
            user.email + '\n' +
            '  </a>\n' +
            '  <ul class="dropdown-menu">\n' +
            '    <li><a class="dropdown-item" onclick="displayProfile()">Profile</a></li>\n' +
            '    <li id="userSetting" value="' + uid + '" <a class="dropdown-item" onclick="openSettings();" href="#">Settings</a></li>\n' +
            '    <li id="logout"><a class="dropdown-item" href="#">Logout</a></li>\n' +
            '  </ul>\n' +
            '</div>';
        let log = document.getElementById("logout");
        log.addEventListener("click", (() => {
            signOut(auth).then(() => {
                location.reload();
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

async function showRightHomePage(uid) {
    const docRef = doc(db, "users", uid);
    const docSnap = await getDoc(docRef);
    if (docSnap.exists()) {
        console.log("Document data:", docSnap.data());

        if (!docSnap.data().homepage.nowplaying) {
            document.getElementById('nowPlayingMovie').checked = false;
            document.getElementById('nowPlayingResult').innerHTML = '';
            document.getElementById('hrNowplaying').innerHTML = '';
            document.getElementById('liNowPlaying').innerHTML = '';

        } else {
            document.getElementById('nowPlayingMovie').checked = true;
            document.getElementById('hrNowplaying').innerHTML = getHrText('Now Playing Movies');
            document.getElementById('liNowPlaying').innerHTML = '<a class="nav-link" href="#nowPlayingResult">Now Playing</a>';
            document.getElementById('nowPlayingResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
                '  <div class="spinner-border" role="status">\n' +
                '    <span class="visually-hidden">Loading...</span>\n' +
                '  </div>\n' +
                '</div>'
            fetch('./api/search/nowplaying')
                .then(result => {
                    result.json().then(data => {
                        console.log(data);

                        loadSpecialMovies(data, 'nowPlayingResult', 'listNowPlaying');
                    })
                })
        }

        if (!docSnap.data().homepage.toprated) {
            document.getElementById('topRatedMovie').checked = false;
            document.getElementById('topRatedResult').innerHTML = '';
            document.getElementById('hrToprated').innerHTML = '';
            document.getElementById('liToprated').innerHTML = '';
        } else {
            document.getElementById('topRatedMovie').checked = true;
            document.getElementById('hrToprated').innerHTML = getHrText('Top Rated Movies');
            document.getElementById('liToprated').innerHTML = '<a class="nav-link" href="#topRatedResult">Top Rated</a>';
            document.getElementById('topRatedResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
                '  <div class="spinner-border" role="status">\n' +
                '    <span class="visually-hidden">Loading...</span>\n' +
                '  </div>\n' +
                '</div>'
            fetch('./api/search/toprated')
                .then(result => {
                    result.json().then(data => {
                        console.log(data);

                        loadSpecialMovies(data, 'topRatedResult', 'listTopRated');
                    })
                })
        }

        if (!docSnap.data().homepage.trending) {
            document.getElementById('trendingMovie').checked = false;
            document.getElementById('trendingResult').innerHTML = '';
            document.getElementById('hrTrending').innerHTML = '';
            document.getElementById('liTrending').innerHTML = '';
        } else {
            document.getElementById('trendingMovie').checked = true;
            document.getElementById('hrTrending').innerHTML = getHrText('Movie-Trends of the Week');
            document.getElementById('liTrending').innerHTML = '<a class="nav-link" href="#trendingResult">Trending</a>';
            document.getElementById('trendingResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
                '  <div class="spinner-border" role="status">\n' +
                '    <span class="visually-hidden">Loading...</span>\n' +
                '  </div>\n' +
                '</div>'
            fetch('./api/trending/movies')
                .then(result => {
                    result.json().then(data => {
                        console.log(data);

                        loadSpecialMovies(data, 'trendingResult', 'listTrending');
                    })
                })
        }

        if (!docSnap.data().homepage.upcoming) {
            document.getElementById('upcomingMovie').checked = false;
            document.getElementById('upcomingResult').innerHTML = '';
            document.getElementById('hrUpcoming').innerHTML = '';
            document.getElementById('liUpcoming').innerHTML = '';
        } else {
            document.getElementById('upcomingMovie').checked = true;
            document.getElementById('hrUpcoming').innerHTML = getHrText('Upcoming Movies');
            document.getElementById('liUpcoming').innerHTML = '<a class="nav-link" href="#upcomingResult">Upcoming</a>';
            document.getElementById('upcomingResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
                '  <div class="spinner-border" role="status">\n' +
                '    <span class="visually-hidden">Loading...</span>\n' +
                '  </div>\n' +
                '</div>'
            fetch('./api/search/upcoming')
                .then(result => {
                    result.json().then(data => {
                        console.log(data);

                        loadSpecialMovies(data, 'upcomingResult', 'listUpcoming');
                    })
                })
        }

    } else {
        // doc.data() will be undefined in this case
        console.log("No such document!");
    }
}
document.getElementById("btnUpdateHomePage").addEventListener("click", updateHomePageInFirebase);
async function updateHomePageInFirebase() {
    let trending = document.getElementById('trendingMovie').checked;
    let nowplaying = document.getElementById('nowPlayingMovie').checked;
    let toprated = document.getElementById('topRatedMovie').checked;
    let upcoming = document.getElementById('upcomingMovie').checked;

    let uid = document.getElementById('userSetting').attributes[1].value;

    await updateDoc(doc(db, "users", uid), {
        homepage: {
            trending: trending,
            nowplaying: nowplaying,
            toprated: toprated,
            upcoming: upcoming,
        }
    });

    showRightHomePage(uid);
}

function getHrText (name) {
    return '<div class="col">\n' +
        '        <hr class="bg-danger border-2 border-top border-danger">\n' +
        '    </div>\n' +
        '    <div class="col-auto">\n' +
        '        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"\n' +
        '             class="bi bi-lightning-charge" viewBox="0 0 16 16">\n' +
        '            <path d="M11.251.068a.5.5 0 0 1 .227.58L9.677 6.5H13a.5.5 0 0 1 .364.843l-8 8.5a.5.5 0 0 1-.842-.49L6.323 9.5H3a.5.5 0 0 1-.364-.843l8-8.5a.5.5 0 0 1 .615-.09zM4.157 8.5H7a.5.5 0 0 1 .478.647L6.11 13.59l5.732-6.09H9a.5.5 0 0 1-.478-.647L9.89 2.41 4.157 8.5z"/>\n' +
        '        </svg>\n' + name +
        '        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"\n' +
        '             class="bi bi-lightning-charge" viewBox="0 0 16 16">\n' +
        '            <path d="M11.251.068a.5.5 0 0 1 .227.58L9.677 6.5H13a.5.5 0 0 1 .364.843l-8 8.5a.5.5 0 0 1-.842-.49L6.323 9.5H3a.5.5 0 0 1-.364-.843l8-8.5a.5.5 0 0 1 .615-.09zM4.157 8.5H7a.5.5 0 0 1 .478.647L6.11 13.59l5.732-6.09H9a.5.5 0 0 1-.478-.647L9.89 2.41 4.157 8.5z"/>\n' +
        '        </svg>\n' +
        '    </div>\n' +
        '    <div class="col">\n' +
        '        <hr class="bg-danger border-2 border-top border-danger">\n' +
        '    </div>';
}

export async function addToWatch(movieId) {
    let uid = user.uid;
    let doc = db.collection("users").doc(uid);
    doc.get().then(docSnap => {
        let watchList = docSnap.data().watchList;
        if (watchList.includes(movieId)) {
            alert("This movie is already in your watch list");
        } else {
            watchList.push(movieId);
            updateDoc(doc, {
                watchList: watchList
            });
        }
    });
}