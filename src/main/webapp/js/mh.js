let _jwt;

function searchMovie(searchString, page, genre) {

    _genres.forEach(genres => {
        if (genres.name === genre) {
            /*alert(genres.id);*/
        }
    })

    if (searchString !== "") {
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                console.log(value);
                value.json().then(data => {
                    console.log(data);
                    document.getElementById('searchResult').innerHTML = '<a id="leftSlide" href="#">\n' +
                        '        <span class="glyphicon glyphicon-chevron-left"></span>\n' +
                        '    </a>\n' +
                        '    <ul style="text-align: center; margin-top: 25px" id="list" class="list-group list-group-horizontal">\n' +
                        '    </ul>\n' +
                        '    <a id="rightSlide" href="#">\n' +
                        '        <span class="glyphicon glyphicon-chevron-right"></span>\n' +
                        '    </a>';
                    document.getElementById('list').innerHTML = '';
                    let counter = 0;

                    data.results.forEach(movie => {
                        if (counter < 5) {

                            let img = '';
                            if (movie.poster_path == null) {
                                img = 'images/notAvailable.jpg'
                            } else {
                                img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
                            }


                            let card = '<div class="card" style="width:250px">\n' +
                                '  <div style="height: 400px">\n' +
                                '  <img style="height: 100%" class="card-img-top" src="' + img + '" width="250px" alt="Card image">\n' +
                                '  </div>\n' +
                                '  <div class="card-body">\n' +
                                '    <h4 class="card-title">' + movie.original_title + '</h4>\n' +
                                '    <p class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                                '    <a href="#" onclick="getTMDBInformation(' + movie.id + ')" class="btn btn-primary">See More</a>\n' +
                                '  </div>\n' +
                                '</div>';
                            let item = '<li class="list-group-item">' + card + '</li>';
                            document.getElementById('list').innerHTML += item;
                            counter++;
                        }
                    })
                })
            })
    }
}

function getTMDBInformation(tmdbID) {
    fetch('./api/search/' + tmdbID)
        .then(result => {
            result.json().then(data => {
                console.log(data);
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

function login(tfEmail, tfPassword){
    if (tfEmail === '' || tfPassword === ''){
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




