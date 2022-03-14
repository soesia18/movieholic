package at.htlkaindorf.mh.beans;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String email;
    private String password;
    private List<Movie> userMovies;
}
