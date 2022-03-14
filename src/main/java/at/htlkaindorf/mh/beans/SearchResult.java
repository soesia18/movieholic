package at.htlkaindorf.mh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {
    private int page;
    private MovieSearch[] results;
    private int total_pages;
    private int total_results;
}
