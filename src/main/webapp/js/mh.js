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

    _startCounter = 0;
    _endCounter = 5;

    if (searchString !== "") {
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

    _movies.results.forEach(movie => {
        if (counter >= _startCounter && counter < _endCounter) {

            let img = '';
            if (movie.poster_path == null) {
                img = 'images/notAvailable.jpg'
            } else {
                img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
            }

            let card = '<div class="card" style="width:200px">\n' +
                '  <div style="height: 300px">\n' +
                '  <img class="card-img mx-auto d-block" src="' + img + '" alt="Card image">\n' +
                '  </div>\n' +
                '  <div class="card-body">\n' +
                '    <h4 style="height: 60px" class="card-title">' + movie.original_title + '</h4>\n' +
                '<hr>' +
                '    <p style="height: 125px" class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-primary">See More</a>\n' +
                '  </div>\n' +
                '</div>';
            let item = '<li class="list-group-item">' + card + '</li>';
            document.getElementById('list').innerHTML += item;
        }
        counter++;
    })
}


function getTMDBInformation(tmdbID) {
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


                let card = '<div class="card mb-3" style="max-width: 540px;">\n' +
                    '  <div class="row g-0">\n' +
                    '    <div class="col-md-4">\n' +
                    '      <img src="' + img + '" class="img-fluid rounded-start" alt="...">\n' +
                    '    </div>\n' +
                    '    <div class="col-md-8">\n' +
                    '      <div class="card-body">\n' +
                    '        <h5 class="card-title">' + data.original_title + '</h5>\n' +
                    '        <p class="card-text">' + data.overview + '</p>\n' +
                    '        <p class="card-text"><small class="text-muted"><button onclick="loadMovies()" type="button" class="btn btn-dark">Back</button></small></p>\n' +
                    '      </div>\n' +
                    '    </div>\n' +
                    '  </div>\n' +
                    '</div>';

                let backgroundDiv = '<div class="bg-image p-5 text-center shadow-1-strong rounded mb-5"\n' +
                    '        style="background-image: url(\'' + imgbg + '\');">\n' +
                   '<div class="d-flex justify-content-center d-flex align-items-center" id="singleMovie">\n' +
                        '    </div>\n' +
                    '</div>';

                document.getElementById('singleMovieDiv').innerHTML = backgroundDiv;


                document.getElementById('singleMovie').innerHTML = card;

                getIMDBInformation(data.imdb_id);
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

function login(tfEmail, tfPassword) {
    if (tfEmail === '' || tfPassword === '') {
        alert("Bitte füllen Sie alle Login Felder aus");
        return;
    }

    let d = {
        "email": tfEmail,
        "password": tfPassword
    }

    fetch("./api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(d)
    }).then(response => {
        if (response.status !== 200) {
            alert(response.status + " " + response.statusText);
            return;
        }
        _jwt = response.headers.get("Authorization");
    });
}





