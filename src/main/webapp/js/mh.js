let _jwt;
let _startCounter;
let _endCounter;

let _endMax;

let _movies;



function searchMovie(searchString, page) {

    /*    _genres.forEach(genres => {
            if (genres.name === genre) {
                /!*alert(genres.id);*!/
            }
        })*/

    window.scrollTo(0, 0);

    _startCounter = 0;
    _endCounter = 5;

    if (searchString !== "") {
        document.getElementById('searchResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
            '  <div class="spinner-border" role="status">\n' +
            '    <span class="visually-hidden">Loading...</span>\n' +
            '  </div>\n' +
            '</div>'
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                console.log(value);
                value.json().then(data => {
                    _movies = data;
                    _endMax = data.total_results > 20 ? 20 : data.total_results;
                    console.log(data);
                    loadMovies();
                })
            })
    } else {
        document.getElementById('searchResult').innerHTML = '';
        _movies = null;
        document.getElementById('singleMovieDiv').innerHTML = '';
    }
}

function nextMovie() {
    if (_endCounter < _endMax - 1) {
        _startCounter++;
        _endCounter++;

        loadMovies();
    }
}

function previousMovie() {
    if (_startCounter > 0) {
        _startCounter--;
        _endCounter--;

        loadMovies();
    }
}

function loadMovies() {
    document.getElementById('singleMovieDiv').innerHTML = '';

    document.getElementById('searchResult').innerHTML = '<button class="btn bg-transparent" id="leftSlide" onclick="previousMovie()">\n' +
        '        <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-caret-left" viewBox="0 0 16 16">\n' +
        '  <path d="M10 12.796V3.204L4.519 8 10 12.796zm-.659.753-5.48-4.796a1 1 0 0 1 0-1.506l5.48-4.796A1 1 0 0 1 11 3.204v9.592a1 1 0 0 1-1.659.753z"/>\n' +
        '</svg>\n' +
        '    </button>\n' +
        '    <ul style="text-align: center; margin-top: 25px" id="list" class="list-group list-group-horizontal">\n' +
        '    </ul>\n' +
        '    <button class="btn bg-transparent" id="rightSlide" onclick="nextMovie()">\n' +
        '        <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-caret-right" viewBox="0 0 16 16">\n' +
        '  <path d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z"/>\n' +
        '</svg>\n' +
        '    </button>';
    document.getElementById('list').innerHTML = '';
    let counter = 0;


    if (_movies == null) {
        document.getElementById('searchResult').innerHTML = '';
        return;
    }

    _movies.results.forEach(movie => {
        if (counter >= _startCounter && counter < _endCounter) {

            let img = '';
            if (movie.poster_path == null) {
                img = 'images/notAvailable.jpg'
            } else {
                img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
            }
            let card;
            if (movie.original_title.length > 20) {

                card = '<div class="card" style="width:200px">\n' +
                    '  <div style="height: 300px">\n' +
                    '  <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="' + img + '" alt="Card image">\n' +
                    '  </div>\n' +
                    '  <div class="card-body">\n' +
                    '    <marquee><h4 style="height: 60px" class="card-title">' + movie.original_title + '</h4></marquee>\n' +
                    '<hr>' +
                    '    <p style="height: 125px" class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                    '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-info">See More</a>\n' +
                    '  </div>\n' +
                    '</div>';
            } else {
                card = '<div class="card" style="width:200px">\n' +
                    '  <div style="height: 300px">\n' +
                    '  <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="' + img + '" alt="Card image">\n' +
                    '  </div>\n' +
                    '  <div class="card-body">\n' +
                    '    <h4 style="height: 60px" class="card-title">' + movie.original_title + '</h4></>\n' +
                    '<hr>' +
                    '    <p style="height: 125px" class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                    '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-info">See More</a>\n' +
                    '  </div>\n' +
                    '</div>';
            }
            let item = '<li class="list-group-item">' + card + '</li>';
            document.getElementById('list').innerHTML += item;
        }
        counter++;
    })

}

function discover(year, monetization, language, region, sort, adult, genres) {
    genres = genres === 'Alle' ? ' ' : genres;
    document.getElementById('searchResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
        '  <div class="spinner-border" role="status">\n' +
        '    <span class="visually-hidden">Loading...</span>\n' +
        '  </div>\n' +
        '</div>'
    fetch('./api/search/discover?year=' + year +
        '&monetization=' + monetization +
        '&language=' + language +
        '&region=' + region +
        '&sort=' + sort +
        '&adult=' + adult +
        '&genres=' + genres)
        .then(result => {
            if (result.status === 200) {
                $('#discoverModal').modal('hide');
                _startCounter = 0;
                _endCounter = 5;

                result.json().then(data => {
                    console.log(data);
                    _movies = data;
                    _endMax = data.total_results > 20 ? 20 : data.total_results;
                    console.log(data);
                    loadMovies();
                })
            }

        })
}

function getTMDBInformation(tmdbID) {
    document.getElementById('searchResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
        '  <div class="spinner-border" role="status">\n' +
        '    <span class="visually-hidden">Loading...</span>\n' +
        '  </div>\n' +
        '</div>'
    fetch('./api/search/' + tmdbID)
        .then(result => {
            result.json().then(data => {
                console.log(data);

                document.getElementById('searchResult').innerHTML = '';

                let img = '';
                if (data.poster_path == null) {
                    img = 'images/notAvailable.jpg'
                } else {
                    img = 'https://image.tmdb.org/t/p/original' + data.poster_path;
                }

                let imgbg = '';
                if (data.backdrop_path == null) {
                    imgbg = 'images/notAvailable.jpg'
                } else {
                    imgbg = 'https://image.tmdb.org/t/p/original' + data.backdrop_path;
                }

                let video = '';


                fetch('./api/search/video/' + data.id)
                    .then(videoResult => {
                        videoResult.json().then(videoData => {
                            console.log(videoData);

                            if (videoData.results.length > 0) {

                                let key = videoData.results[0].key;

                                video = '<iframe style="margin-bottom: 25px" width="560" height="315" src="https://www.youtube.com/embed/' + key + '" title="YouTube video player"\n' +
                                    '        frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"\n' +
                                    '        allowfullscreen>\n' +
                                    '</iframe>';
                            }
                            let card = '<div class="card mb-3" style="max-width: 540px;">\n' +
                                '  <div class="row g-0">\n' +
                                '    <div class="col-md-4">\n' +
                                '      <img style="border-radius: 15px;" src="' + img + '" class="img-fluid rounded-start border-0" alt="...">\n' +
                                '    </div>\n' +
                                '    <div class="col-md-8">\n' +
                                '      <div class="card-body">\n' +
                                '        <h5 class="card-title">' + data.original_title + '</h5>\n' +
                                '        <p class="card-text" style="text-align: justify">' + data.overview + '</p>\n' +
                                '        <p class="card-text"><small class="text-muted"><button onclick="loadMovies()" type="button" class="btn btn-dark">Back</button></small></p>\n' +
                                '      </div>\n' +
                                '    </div>\n' +
                                '  </div>\n' +
                                '</div>';

                            document.getElementById('singleMovieDiv').innerHTML = '<div class="bg-image p-5 text-center shadow-1-strong rounded mb-5"\n' +
                                '        style="background-image: url(\'' + imgbg + '\');width: 100%">\n' +
                                '<div class="d-flex justify-content-center d-flex align-items-center" id="singleMovie">\n' +
                                '    </div>\n' +
                                '</div>\n' +
                                '<div class="d-flex justify-content-around">' +  video + '</div>';

                            document.getElementById('singleMovie').innerHTML = card;

                            //getIMDBInformation(data.imdb_id);

                        })
                    })
                fetch('./api/search/provider/' + data.id)
                    .then(providerResult => {
                        providerResult.json().then(providerData => {
                            console.log(providerData);

                            let us = providerData.results.US;

                            let buyArr = [];

                            if (us.buy != null) {
                                buyArr.push('<p>Buy</p>');
                                us.buy.forEach(buyData => {
                                        buyArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '">\n' +
                                            '<span>' + buyData.provider_name + '</span>');
                                    }
                                )
                            }

                            let flatrateArr = [];

                            if (us.flatrate != null) {
                                flatrateArr.push('<p>Flatrate</p>');
                                us.flatrate.forEach(buyData => {
                                        flatrateArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '">\n' +
                                            '<span>' + buyData.provider_name + '</span>');
                                    }
                                )
                            }

                            let rentArr = [];

                            if (us.rent != null) {
                                rentArr.push('<p>Rent</p>');
                                us.rent.forEach(buyData => {
                                        rentArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '">\n' +
                                            '<span>' + buyData.provider_name + '</span>');
                                    }
                                )
                            }

                            let maxRows = buyArr.length;

                            if (maxRows < flatrateArr.length) {
                                maxRows = flatrateArr.length;
                            }

                            if (maxRows < rentArr.length) {
                                maxRows = rentArr.length;
                            }

                            let table = '<table>';

                            for (let i = 0; i < maxRows; i++) {
                                table += '<tr>';
                                if (i < buyArr.length) {
                                    if (i === 0) {
                                        table += '<th>' + buyArr[i] + '</th>';
                                    } else {
                                        table += '<td>' + buyArr[i] + '</td>';
                                    }
                                } else {
                                    table += '<td></td>';
                                }
                                if (i < flatrateArr.length) {
                                    if (i === 0) {
                                        table += '<th>' + flatrateArr[i] + '</th>';
                                    } else {
                                        table += '<td>' + flatrateArr[i] + '</td>';
                                    }
                                } else {
                                    table += '<td></td>';
                                }
                                if (i < rentArr.length) {
                                    if (i === 0) {
                                        table += '<th>' + rentArr[i] + '</th>';
                                    } else {
                                        table += '<td>' + rentArr[i] + '</td>';
                                    }
                                } else {
                                    table += '<td></td>';
                                }
                                table += '</tr>';
                            }
                            table += '</table>';
                            document.getElementById('singleMovieDiv').innerHTML +=
                                '<div style="margin-bottom: 25px" class="d-flex justify-content-around">' + table + '</div>';
                        })
                    })

            })
        })
}

function getIMDBInformation(imdbID) {
    fetch('./api/search/imdb/' + imdbID)
        .then(result => {
            result.json().then(data => {
                console.log(data);
            })
        })
}

let _genres;

function loadGenres() {

    $("#releaseYear").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years"
    });
    fetch('./api/genres')
        .then(result => {
            result.json().then(data => {
                console.log(data);
                let genres = '<option value="Alle">Alle</option>';
                _genres = data.genres;

                data.genres.forEach(genre => {
                    genres += '<option value="' + genre.name + '">' + genre.name + '</option>';
                })

                document.getElementById('search_param').innerHTML = genres;
            })
        })
}

function loadTrending() {
    document.getElementById('trendingResult').innerHTML = '<div class="d-flex justify-content-center">\n' +
        '  <div class="spinner-border" role="status">\n' +
        '    <span class="visually-hidden">Loading...</span>\n' +
        '  </div>\n' +
        '</div>'
    fetch('./api/trending/movies')
        .then(result => {
            result.json().then(data => {
                console.log(data);

                document.getElementById('trendingResult').innerHTML = '<ul style="text-align: center; margin-top: 25px" id="listTrending" class="list-group list-group-horizontal">\n' +
                    '    </ul>\n';

                document.getElementById('listTrending').innerHTML = '';
                let counter = 0;

                data.results.forEach(movie => {
                    if (counter >= 0 && counter < 5) {

                        let img = '';
                        if (movie.poster_path == null) {
                            img = 'images/notAvailable.jpg'
                        } else {
                            img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
                        }

                        let card = '';
                        if (movie.original_title.length > 20) {

                            card = '<div class="card" style="width:200px">\n' +
                                '  <div style="height: 300px">\n' +
                                '  <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="' + img + '" alt="Card image">\n' +
                                '  </div>\n' +
                                '  <div class="card-body">\n' +
                                '    <marquee><h4 style="height: 60px" class="card-title">' + movie.original_title + '</h4></marquee>\n' +
                                '<hr>' +
                                '    <p style="height: 125px" class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                                '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-info">See More</a>\n' +
                                '  </div>\n' +
                                '</div>';
                        } else {
                            card = '<div class="card" style="width:200px">\n' +
                                '  <div style="height: 300px">\n' +
                                '  <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="' + img + '" alt="Card image">\n' +
                                '  </div>\n' +
                                '  <div class="card-body">\n' +
                                '    <h4 style="height: 60px" class="card-title">' + movie.original_title + '</h4></>\n' +
                                '<hr>' +
                                '    <p style="height: 125px" class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                                '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-info">See More</a>\n' +
                                '  </div>\n' +
                                '</div>';
                        }
                        let item = '<li class="list-group-item">' + card + '</li>';
                        document.getElementById('listTrending').innerHTML += item;
                    }
                    counter++;
                })
            })
        })
}

function clearLoginModal() {
    document.getElementById("tfLoginEmail").value = "";
    document.getElementById("tfLoginPassword").value = "";
}

firebase.auth().onAuthStateChanged((user) => {
    if (user) {
        // User is signed in, see docs for a list of available properties
        // https://firebase.google.com/docs/reference/js/firebase.User
        var uid = user.uid;
        // ...
    } else {
        // User is signed out
        // ...
    }
});

/*
function login(userEmail, userPassword) {
    firebase.auth().signInWithEmailAndPassword(userEmail, userPassword)
        .then((userCredential) => {
            // Signed in
            var user = userCredential.user;

            window.alert(user);
        })
        .catch((error) => {
            var errorCode = error.code;
            var errorMessage = error.message;

            window.alert("Error: " + errorCode + " " + errorMessage);
        });

    let info = document.getElementById("loginInfo");
    let d = {
        "email": tfEmail,
        "password": tfPassword
    }

    fetch("./api/login/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(d)
    }).then(response => {
        info.innerHTML = "";
        if (response.status !== 200) {
            if (response.status === 401) {
                info.innerHTML = "No user with the given credentials found";
            }
            return;
        }
        $('#loginModal').modal('hide');
        _jwt = response.headers.get("Authorization");
    });
}

function clearRegisterModal() {
    document.getElementById("tfRegisterEmail").value = "";
    document.getElementById("tfRegisterPassword1").value = "";
    document.getElementById("tfRegisterPassword2").value = "";
}

function register(tfEmail, tfPassword1, tfPassword2) {
    let info = document.getElementById("registerInfo");
    if (tfPassword2 === tfPassword1) {
        if (!validatePassword(tfPassword1)) {
            document.getElementById("registerInfo").innerHTML = "Password must be a minimum of 8 characters including number, upper, lower and \n" +
                "one special character";
            return;
        }
        info.innerHTML = "";

        var d = {
            "email": tfEmail,
            "password": tfPassword1
        }

        fetch("./api/login/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(d)
        }).then(response => {
            if (response.status !== 200) {
                if (response.status === 400) {
                    info.innerHTML = "Einen User mit der E-Mail gibt es bereits! Bitte melden Sie sich an.";
                }
                return;
            }
            _jwt = response.headers.get("Authorization");
            $('#registerModal').modal('hide');

            var navbarContent = document.getElementById("navbarContent");
            var child = document.getElementById("loginSector");
            navbarContent.removeChild(child);
            navbarContent.innerHTML += '<div class="nav-item navLogin" data-bs-toggle="modal" data-bs-target="#" id="loginSector">\n' +
                '            <span id="loginSpan">' + tfEmail + '</span>\n' +
                '            <svg id="loginImg" xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">\n' +
                '  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>\n' +
                '  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>\n' +
                '</svg>' + '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-up" viewBox="0 0 16 16">\n' +
                '  <path d="M3.204 11h9.592L8 5.519 3.204 11zm-.753-.659 4.796-5.48a1 1 0 0 1 1.506 0l4.796 5.48c.566.647.106 1.659-.753 1.659H3.204a1 1 0 0 1-.753-1.659z"/>\n' +
                '</svg>' + '</div>';

        });

    } else {
        info.innerHTML = "Not the same password";
        return;
    }
}

function validatePassword(password) {
    var regularExpression = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    return regularExpression.test(password);
}
*/