package at.htlkaindorf.mh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private char rights;
    //private List<Movie> userMovies;
}
