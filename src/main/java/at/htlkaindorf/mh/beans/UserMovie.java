package at.htlkaindorf.mh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b> Bean Class for {@link UserMovie} </b> <br>
 * @author David
 * @version 1.0
 * @since last update: 10.06.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMovie {
    private String uid;
    private int movieID;
}
