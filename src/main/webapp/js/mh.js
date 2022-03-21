function searchMovie(searchString, page) {
    if (searchString !== "") {
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                console.log(value);
                value.json().then(data => {
                    console.log(data);
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
                                '  <img class="card-img-top" src="' + img + '" width="250px" alt="Card image">\n' +
                                '  <div class="card-body">\n' +
                                '    <h4 class="card-title">' + movie.original_title + '</h4>\n' +
                                '    <p class="card-text">' + movie.overview.substring(0, 100) + '...' + '</p>\n' +
                                '    <a href="#" class="btn btn-primary">See More</a>\n' +
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
                //getIMDBInformation(data.imdb_id);
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