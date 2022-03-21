package at.htlkaindorf.mh.beans.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {
    private int page;
    private MovieSearch[] results;
    private int total_pages;
    private int total_results;
}
