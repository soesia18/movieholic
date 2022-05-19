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
                        videoResult.json().then(async videoData => {
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
                                '<div class="d-flex justify-content-around">' + video + '</div>';

                            document.getElementById('singleMovie').innerHTML = card;

                            //getIMDBInformation(data.imdb_id);
                            let table = '';
                            await fetch('./api/search/provider/' + data.id)
                                .then(providerResult => {
                                    providerResult.json().then(providerData => {
                                        console.log(providerData);
                                        if (providerData.results.length !== null) {
                                            let us = providerData.results.US;

                                            let buyArr = [];

                                            if (us.buy != null) {
                                                buyArr.push('<p>Buy</p>');
                                                us.buy.forEach(buyData => {
                                                        buyArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '" alt="">\n' +
                                                            '<span>' + buyData.provider_name + '</span>');
                                                    }
                                                )
                                            }

                                            let flatrateArr = [];

                                            if (us.flatrate != null) {
                                                flatrateArr.push('<p>Flatrate</p>');
                                                us.flatrate.forEach(buyData => {
                                                        flatrateArr.push('<img height="25px" width="25px" src="https://image.tmdb.org/t/p/w500' + buyData.logo_path + '" alt="">\n' +
                                                            '<span>' + buyData.provider_name + '</span>');
                                                    }
                                                )
                                            }

                                            let rentArr = [];

                                            if (us.rent != null) {
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
                                            /*document.getElementById('singleMovieDiv').innerHTML +=
                                                '<div style="margin-bottom: 25px" class="d-flex justify-content-around">' + table + '</div>';*/
                                        }
                                    })

                                    let trailerProvider = '<div id="videoProvider" class="d-flex justify-content-around">' + video + '<div class="d-flex justify-content-around" id="streamProvider" style="width: 560px">' + table + '</div></div>\n';

                                    if (table === '') {
                                        trailerProvider = '<div id="videoProvider" class="d-flex justify-content-around">' + video + '</div>';
                                    }

                                    document.getElementById('singleMovieDiv').innerHTML = '<div class="bg-image p-5 text-center shadow-1-strong rounded mb-5"\n' +
                                        '        style="background-image: url(\'' + imgbg + '\');width: 100%">\n' +
                                        '<div class="d-flex justify-content-center d-flex align-items-center" id="singleMovie">\n' +
                                        '    </div>\n' +
                                        '</div>\n' +
                                        +trailerProvider + '<div id="similarVideos"></div>';

                                    document.getElementById('singleMovie').innerHTML = card;

                                    document.getElementById('similarVideos').innerHTML = '<div class="d-flex justify-content-center">\n' +
                                        '  <div class="spinner-border" role="status">\n' +
                                        '    <span class="visually-hidden">Loading...</span>\n' +
                                        '  </div>\n' +
                                        '</div>'
                                    fetch('./api/search/similar/' + data.id)
                                        .then(resultSimilarMovie => {
                                            resultSimilarMovie.json().then(dataSimilarMovie => {
                                                console.log(dataSimilarMovie);
                                                document.getElementById('similarVideos').innerHTML = '<div class="d-flex justify-content-center"><ul style="text-align: center; margin-top: 25px" id="listSimilar" class="list-group list-group-horizontal">\n' +
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
                                                        document.getElementById('listSimilar').innerHTML += item;
                                                    }
                                                    counter++;

                                                })
                                            })
                                        })
                                })

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