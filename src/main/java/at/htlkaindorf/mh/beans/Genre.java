package at.htlkaindorf.mh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Movieholic</b><br><br>
 * <b>Bean Class for Genre</b> <br>
 * @author David
 * @version 1.0
 * @since last update: 11.06.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    private int id;
    private String name;
}
