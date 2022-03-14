function searchMovie (searchString, page) {
    if (searchString !== ""){
        fetch('./api/search/' + searchString + '/' + page)
            .then(value => {
                value.json().then(data => {
                    console.log(data);

                    var table = '';

                    data.results.forEach(movie => {
                        table += '<tr>';
                        table += '<td>' + movie.original_title + '</td>';
                        table += '<td><img src="https://image.tmdb.org/t/p/original' + movie.poster_path + '" width="10%"></td>';
                        table += '</tr>';
                    })

                    document.getElementById("searchResult").innerHTML = table;
                })
            })
    }
}