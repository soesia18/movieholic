let _startCounter;
let _endCounter;

let _endMax;

let _movies;

function displayProfile(){
    $("#profileModal").modal('show');
    let uid = document.getElementById('userSetting').attributes[1].value;
    /* Fetch Stats */

    document.getElementById("profileDiv").innerHTML = `<div class="row">
          <div class="col-sm-4">
            <h1>Profil</h1>
            <p>Filme in der Watchlist</p>
            <p>Filme schon gesehen</p>
            <h3 class="mt-4">Lieblingsgenres</h3>
            <canvas id="genreChart" width="200" height="200"></canvas>
            <hr class="d-sm-none">
          </div>
          <div class="col-sm-8">
            <h2>Watchlist</h2>
            <h5>Filme die Sie noch schauen wollen</h5>
            <hr>
            <h5>Ähnliche Filme</h5>
      
            <h2 class="mt-5">Schon gesehen</h2>
            <h5>Filme die sie schon gesehen haben</h5>
            <hr>
            <h5>Ähnliche Filme</h5>
          </div>
        </div>`
}

function removeFromSeenList(movieID) {
    document.getElementById(movieID + "seenlist").innerHTML = `<a onclick="addToSeenList(${movieID})">
                                <svg style="color: black" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list-ul" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                </svg>
                                <span>Already watched list</span>
                            </a>`;
    let uid = document.getElementById('userSetting').attributes[1].value;

    let data = {
        uid: uid,
        movieID: movieID
    }

    fetch('./api/seenlist/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        console.log(res.statusText);
    });
}

function addToSeenList(movieID) {
    document.getElementById(movieID + "seenlist").innerHTML = `<a onclick="removeFromSeenList(${movieID})">
                                <svg style="color: red" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list-ul" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                </svg>
                                <span>Already watched list</span>
                            </a>`;
    let uid = document.getElementById('userSetting').attributes[1].value;

    let data = {
        uid: uid,
        movieID: movieID
    }

    fetch('./api/seenlist/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        console.log(res.statusText);
    })
}

function addToWatchList(movieID) {
    document.getElementById(movieID + "watchlist").innerHTML = `<a onclick="removeFromWatchList(${movieID})">
                                <svg style="color: red" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                                    <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/>
                                </svg>
                                <span>Watchlist</span>
                            </a>`;
    let uid = document.getElementById('userSetting').attributes[1].value;

    let data = {
        uid: uid,
        movieID: movieID
    }

    fetch('./api/watchlist/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        console.log(res.statusText);
    })

}

function removeFromWatchList(movieID) {
    document.getElementById(movieID + "watchlist").innerHTML = `<a onclick="addToWatchList(${movieID})">
                                <svg style="color: black" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                                    <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/>
                                </svg>
                                <span>Watchlist</span>
                            </a>`;
    let uid = document.getElementById('userSetting').attributes[1].value;

    let data = {
        uid: uid,
        movieID: movieID
    }

    fetch('./api/watchlist/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        console.log(res.statusText);
    });
}


function getCard(img, title, overview, movieID) {
    let dropdown_menu;

    let userSetting = document.getElementById('userSetting');
    if (userSetting != null) {
        let d = {
            uid: userSetting.attributes[1].value,
            movieID: movieID
        };

        fetch('./api/watchlist/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(d)
        }).then(res => {
            res.json().then(data => {
                    if (data) {
                        document.getElementById(movieID + "watchlist").innerHTML = `<a onclick="removeFromWatchList(${movieID})">
                                <svg style="color: red" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                                    <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/>
                                </svg>
                                <span>Watchlist</span>
                            </a>`;
                    }
            });
        });

        fetch('./api/seenlist/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(d)
        }).then(res => {
            res.json().then(data => {
                if (data) {
                    document.getElementById(movieID + "seenlist").innerHTML = `<a onclick="removeFromSeenList(${movieID})">
                                <svg style="color: red" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list-ul" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                </svg>
                                <span>Already watched list</span>
                            </a>`;
                }
            });
        });


        dropdown_menu = `<ul class="dropdown-menu options-dropdown" id="${movieID}dropdown">
                            <li id="${movieID}seenlist" class="dropdown-item"><a onclick="addToSeenList(${movieID})">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list-ul" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                </svg>
                                <span>Already watched list</span>
                            </a></li>
                            <li id="${movieID}watchlist" class="dropdown-item"><a onclick="addToWatchList(${movieID})">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                                    <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/>
                                </svg>
                                <span>Watchlist</span>
                            </a></li>
                        </ul>`;
    } else {
        dropdown_menu = `<ul class="dropdown-menu options-dropdown">
                            <li><span class="dropdown-item-text">Du möchstes diesen Film zu deinen Listen hinzufügen?</span></li>
                            <li data-bs-toggle="modal" data-bs-target="#loginModal"><a class="dropdown-item">Login?</a></li>
                        </ul>`;
    }


    if (title.length > 20) {
        return `<div class="card" style="width:200px">
                <div style="height: 300px" class="image">
                    <div class="wrapper">
                        <a class="image">
                            <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="${img}" alt="Card image">
                        </a>
                        <div class="options dropdown">
                            <a class="options_content" data-bs-toggle="dropdown">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                    <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                                </svg>
                            </a>
                            ${dropdown_menu}
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <marquee><h4 style="height: 60px" class="card-title">${title}</h4></marquee>
                    <hr>
                        <p style="height: 125px" class="card-text">${overview.substring(0, 100) + '...'}</p>
                        <a href="#" onclick="getTMDBInformation(${movieID})" class="btn btn-info">See More</a>
                </div>
            </div>`
    } else {
        return `<div class="card" style="width:200px">
                <div style="height: 300px" class="image">
                    <div class="wrapper">
                        <a class="image_container">
                            <img style="border-radius: 15px;" class="card-img mx-auto d-block border-0" src="${img}" alt="Card image">
                        </a>
                        <div class="options dropdown">
                            <a class="options_content" data-bs-toggle="dropdown">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                    <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                                </svg>
                            </a>
                            ${dropdown_menu}
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <h4 style="height: 60px" class="card-title">${title}</h4>
                    <hr>
                        <p style="height: 125px" class="card-text">${overview.substring(0, 100) + '...'}</p>
                        <a href="#" onclick="getTMDBInformation(${movieID})" class="btn btn-info">See More</a>
                </div>
            </div>`
    }
}


async function searchMovie(searchString, page) {

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
        await fetch('./api/search/' + searchString + '/' + page)
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
    window.scrollTo(0, 0);
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
            let card = getCard(img, movie.original_title, movie.overview, movie.id);;
            let item = '<li class="list-group-item">' + card + '</li>';
            document.getElementById('list').innerHTML += item;
        }
        counter++;
    })

}

function discover(year, monetization, language, region, sort, adult, genres) {
    window.scrollTo(0, 0);
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
            result.json().then(async data => {
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
                let card = '';
                let table = '';


                await fetch('./api/search/video/' + data.id)
                    .then(async videoResult => {
                        console.log("Start");
                        await videoResult.json().then(videoData => {
                            console.log(videoData);

                            if (videoData.results.length > 0) {

                                let key = videoData.results[0].key;

                                video = '<iframe style="margin-bottom: 25px" width="560" height="315" src="https://www.youtube.com/embed/' + key + '" title="YouTube video player"\n' +
                                    '        frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"\n' +
                                    '        allowfullscreen>\n' +
                                    '</iframe>';
                            }
                            card = '<div class="card mb-3" style="max-width: 540px;">\n' +
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


                        })
                    })
                document.getElementById('singleMovieDiv').innerHTML = '<div class="bg-image p-5 text-center shadow-1-strong rounded mb-5"\n' +
                    '        style="background-image: url(\'' + imgbg + '\');width: 100%">\n' +
                    '<div class="d-flex justify-content-center d-flex align-items-center" id="singleMovie">\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '<div class="d-flex justify-content-around">' + video + '</div>';

                document.getElementById('singleMovie').innerHTML = card;

                //getIMDBInformation(data.imdb_id);
                await fetch('./api/search/provider/' + data.id)
                    .then(async providerResult => {
                        if (providerResult.status === 200) {
                            await providerResult.json().then(providerData => {
                                console.log(providerData);
                                if (providerData.results.length !== null) {
                                    let us = providerData.results.US;
                                    console.log(us);

                                    let buyArr = [];
                                    if (typeof us !== 'undefined') {
                                        if (typeof us.buy !== 'undefined') {
                                            buyArr.push('<p>Buy</p>');
                                            us.buy.forEach(buyData => {
                                                    buyArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '" alt="">\n' +
                                                        '<span>' + buyData.provider_name + '</span>');
                                                }
                                            )
                                        }

                                        let flatrateArr = [];

                                        if (typeof us.flatrate !== 'undefined') {
                                            flatrateArr.push('<p>Flatrate</p>');
                                            us.flatrate.forEach(buyData => {
                                                    flatrateArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '" alt="">\n' +
                                                        '<span>' + buyData.provider_name + '</span>');
                                                }
                                            )
                                        }

                                        let rentArr = [];

                                        if (typeof us.rent !== 'undefined') {
                                            rentArr.push('<p>Rent</p>');
                                            us.rent.forEach(buyData => {
                                                    rentArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '" alt="">\n' +
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

                                        table = '<table>';

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
                                    }
                                    /*document.getElementById('singleMovieDiv').innerHTML +=
                                '<div style="margin-bottom: 25px" class="d-flex justify-content-around">' + table + '</div>';*/
                                    let trailerProvider = '<div class="row">\n' +
                                        '    <div class="col">\n' +
                                        '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                        '    </div>\n' +
                                        '    <div class="col-auto">\n' +
                                        '        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-collection-play" viewBox="0 0 16 16">\n' +
                                        '  <path d="M2 3a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11A.5.5 0 0 0 2 3zm2-2a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7A.5.5 0 0 0 4 1zm2.765 5.576A.5.5 0 0 0 6 7v5a.5.5 0 0 0 .765.424l4-2.5a.5.5 0 0 0 0-.848l-4-2.5z"/>\n' +
                                        '  <path d="M1.5 14.5A1.5 1.5 0 0 1 0 13V6a1.5 1.5 0 0 1 1.5-1.5h13A1.5 1.5 0 0 1 16 6v7a1.5 1.5 0 0 1-1.5 1.5h-13zm13-1a.5.5 0 0 0 .5-.5V6a.5.5 0 0 0-.5-.5h-13A.5.5 0 0 0 1 6v7a.5.5 0 0 0 .5.5h13z"/>\n' +
                                        '</svg>\n' +
                                        'Trailer & Provider Information\n' +
                                        '   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-collection-play" viewBox="0 0 16 16">\n' +
                                        '  <path d="M2 3a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11A.5.5 0 0 0 2 3zm2-2a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7A.5.5 0 0 0 4 1zm2.765 5.576A.5.5 0 0 0 6 7v5a.5.5 0 0 0 .765.424l4-2.5a.5.5 0 0 0 0-.848l-4-2.5z"/>\n' +
                                        '  <path d="M1.5 14.5A1.5 1.5 0 0 1 0 13V6a1.5 1.5 0 0 1 1.5-1.5h13A1.5 1.5 0 0 1 16 6v7a1.5 1.5 0 0 1-1.5 1.5h-13zm13-1a.5.5 0 0 0 .5-.5V6a.5.5 0 0 0-.5-.5h-13A.5.5 0 0 0 1 6v7a.5.5 0 0 0 .5.5h13z"/>\n' +
                                        '</svg>\n' +
                                        ' </div>\n' +
                                        '    <div style="margin-bottom: 25px" class="col">\n' +
                                        '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                        '    </div>\n' +
                                        '</div><div id="videoProvider" class="d-flex justify-content-around">' + video + '<div class="d-flex justify-content-around" id="streamProvider" style="width: 560px">' + table + '</div></div>\n';


                                    if (typeof table === 'undefined' || table === '') {
                                        trailerProvider = '<div class="row">\n' +
                                            '    <div class="col">\n' +
                                            '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                            '    </div>\n' +
                                            '    <div class="col-auto">\n' +
                                            '      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-collection-play" viewBox="0 0 16 16">\n' +
                                            '  <path d="M2 3a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11A.5.5 0 0 0 2 3zm2-2a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7A.5.5 0 0 0 4 1zm2.765 5.576A.5.5 0 0 0 6 7v5a.5.5 0 0 0 .765.424l4-2.5a.5.5 0 0 0 0-.848l-4-2.5z"/>\n' +
                                            '  <path d="M1.5 14.5A1.5 1.5 0 0 1 0 13V6a1.5 1.5 0 0 1 1.5-1.5h13A1.5 1.5 0 0 1 16 6v7a1.5 1.5 0 0 1-1.5 1.5h-13zm13-1a.5.5 0 0 0 .5-.5V6a.5.5 0 0 0-.5-.5h-13A.5.5 0 0 0 1 6v7a.5.5 0 0 0 .5.5h13z"/>\n' +
                                            '</svg>\n' +
                                            '  Trailer\n' +
                                            '   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-collection-play" viewBox="0 0 16 16">\n' +
                                            '  <path d="M2 3a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11A.5.5 0 0 0 2 3zm2-2a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7A.5.5 0 0 0 4 1zm2.765 5.576A.5.5 0 0 0 6 7v5a.5.5 0 0 0 .765.424l4-2.5a.5.5 0 0 0 0-.848l-4-2.5z"/>\n' +
                                            '  <path d="M1.5 14.5A1.5 1.5 0 0 1 0 13V6a1.5 1.5 0 0 1 1.5-1.5h13A1.5 1.5 0 0 1 16 6v7a1.5 1.5 0 0 1-1.5 1.5h-13zm13-1a.5.5 0 0 0 .5-.5V6a.5.5 0 0 0-.5-.5h-13A.5.5 0 0 0 1 6v7a.5.5 0 0 0 .5.5h13z"/>\n' +
                                            '</svg>\n' +
                                            ' </div>\n' +
                                            '    <div style="margin-bottom: 25px" class="col">\n' +
                                            '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                            '    </div>\n' +
                                            '</div><div id="videoProvider" class="d-flex justify-content-around">' + video + '</div>';
                                    }

                                    document.getElementById('singleMovieDiv').innerHTML = '<div class="bg-image p-5 text-center shadow-1-strong rounded mb-5"\n' +
                                        '        style="background-image: url(\'' + imgbg + '\');width: 100%">\n' +
                                        '<div class="d-flex justify-content-center d-flex align-items-center" id="singleMovie">\n' +
                                        '    </div>\n' +
                                        '</div>\n' +
                                        trailerProvider + '<div id="similarVideos"></div>';

                                    document.getElementById('singleMovie').innerHTML = card;


                                }
                            })


                        }
                    })
                document.getElementById('similarVideos').innerHTML = '<div class="d-flex justify-content-center">\n' +
                    '  <div class="spinner-border" role="status">\n' +
                    '    <span class="visually-hidden">Loading...</span>\n' +
                    '  </div>\n' +
                    '</div>';
                fetch('./api/search/similar/' + data.id)
                    .then(resultSimilarMovie => {
                        if (resultSimilarMovie.status === 200) {
                            resultSimilarMovie.json().then(dataSimilarMovie => {
                                console.log(dataSimilarMovie);
                                document.getElementById('similarVideos').innerHTML = '<div class="row">\n' +
                                    '    <div class="col">\n' +
                                    '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                    '    </div>\n' +
                                    '    <div class="col-auto">\n' +
                                    '        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-film" viewBox="0 0 16 16">\n' +
                                    '  <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>\n' +
                                    '</svg>\n' +
                                    'Similar Movies\n' +
                                    '    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-film" viewBox="0 0 16 16">\n' +
                                    '  <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>\n' +
                                    '</svg>\n' +
                                    '</div>\n' +
                                    '    <div class="col">\n' +
                                    '        <hr class="bg-danger border-2 border-top border-info">\n' +
                                    '    </div>\n' +
                                    '</div><div class="d-flex justify-content-center"><ul style="text-align: center; margin-top: 25px" id="listSimilar" class="list-group list-group-horizontal">\n' +
                                    '    </ul></div>\n';

                                document.getElementById('listSimilar').innerHTML = '';
                                let counter = 0;

                                dataSimilarMovie.results.forEach(movie => {
                                    if (counter >= 0 && counter < 5) {

                                        let img = '';
                                        if (movie.poster_path == null) {
                                            img = 'images/notAvailable.jpg'
                                        } else {
                                            img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
                                        }

                                        let card = getCard(img, movie.title, movie.overview, movie.id);
                                        let item = '<li class="list-group-item">' + card + '</li>';
                                        document.getElementById('listSimilar').innerHTML += item;
                                    }
                                    counter++;

                                })
                            })
                        }
                    })

                console.log("Ende");

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
    fetch('./api/data/genres')
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

function loadSpecialMovies(data, result, list) {

    document.getElementById(result).innerHTML = '<ul style="text-align: center; margin-top: 25px" id="' + list + '" class="list-group list-group-horizontal">\n' +
        '    </ul>\n';

    document.getElementById(list).innerHTML = '';
    let counter = 0;

    data.results.forEach(movie => {
        if (counter >= 0 && counter < 5) {

            let img = '';
            if (movie.poster_path == null) {
                img = 'images/notAvailable.jpg'
            } else {
                img = 'https://image.tmdb.org/t/p/original' + movie.poster_path;
            }

            let card = getCard(img, movie.original_title, movie.overview, movie.id);
            let item = '<li class="list-group-item">' + card + '</li>';
            document.getElementById(list).innerHTML += item;
        }
        counter++;
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

                loadSpecialMovies(data, 'trendingResult', 'listTrending');
            })
        })
}

function loadNowPlaying() {
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

function loadTopRated() {
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

function loadUpcoming() {
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


function clearLoginModal() {
    document.getElementById("tfLoginEmail").value = "";
    document.getElementById("tfLoginPassword").value = "";
}

function openSettings() {
    $("#userSettingModal").modal('show');
}


/*function updateHomePage(trending, nowplaying, toprated, upcoming) {

    let uid = document.getElementById('userSetting').attributes[1].value;


    fetch('./api/data/homepage?uid=' + uid + '&trending=' + trending + '&nowplaying=' + nowplaying + '&toprated=' +
        toprated + '&upcoming=' + upcoming, {
        method: 'PUT'
    })
        .then(updateSearchResults => {
            updateSearchResults.json().then(data => {
                console.log(data);
            })
        })
}*/

