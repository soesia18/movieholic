function searchMovie (searchString, page) {
    if (searchString !== ""){
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                console.log(value);
                value.json().then(data => {
                    console.log(data);
                    document.getElementById('list').innerHTML = '';
                    let table = '';

                    data.results.forEach(movie => {
                        table += '<tr>';
                        table += '<td>' + movie.original_title + '</td>';
                        if (movie.poster_path == null) {
                            table += '<td><img onclick="getTMDBInformation(' + movie.id + ')" src="" width="10%" alt="IMG"></td>';
                        } else {
                            table += '<td><img onclick="getTMDBInformation(' + movie.id + ')" src="https://image.tmdb.org/t/p/original' + movie.poster_path + '" width="10%" alt="IMG"></td>';
                        }
                        table += '</tr>';

                        let item = '<li class="list-group-item">' + movie.original_title +'</li>';
                        document.getElementById('list').innerHTML += item;
                    })

                    document.getElementById("searchResult").innerHTML = table;
                })
            })
    }
}

function getTMDBInformation (tmdbID) {
    fetch('./api/search/' + tmdbID)
        .then(result => {
            result.json().then(data => {
                console.log(data);
                //getIMDBInformation(data.imdb_id);
            })
        })
}

function getIMDBInformation (imdbID) {
    fetch('./api/search/imdb/' + imdbID)
        .then(result => {
            result.json().then(data => {
                console.log(data);
            })
        })

}