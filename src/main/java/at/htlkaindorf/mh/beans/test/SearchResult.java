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
public class SearchResult {
    private int page;
    private MovieSearch[] results;
    private int total_pages;
    private int total_results;
}
