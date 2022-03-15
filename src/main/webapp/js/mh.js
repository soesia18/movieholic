function searchMovie (searchString, page) {
    if (searchString !== ""){
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                value.json().then(data => {
                    console.log(data);

                    let table = '';

                    data.results.forEach(movie => {
                        table += '<tr>';
                        table += '<td>' + movie.original_title + '</td>';
                        table += '<td><img onclick="getTMDBInformation(' + movie.id + ')" src="https://image.tmdb.org/t/p/original' + movie.poster_path + '" width="10%" alt="IMG"></td>';
                        table += '</tr>';
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
            })
        })
}