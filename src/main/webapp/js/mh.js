function searchMovie (searchString) {
    fetch('./api/search/' + searchString)
        .then(value => {
            value.json().then(data => {
                console.log(data);
            })
        })
}