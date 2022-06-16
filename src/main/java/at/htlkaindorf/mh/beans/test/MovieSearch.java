package at.htlkaindorf.mh.beans.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Movieholic</b><br><br>
 * <b>Bean Class</b>  <br>
 * @author Simon
 * @version 1.0
 * @since last update: 14.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearch {
    private boolean adult;
    private String backdrop_path;
    private int[] genre_ids;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
}
