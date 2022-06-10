package at.htlkaindorf.mh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMovie {
    private String uid;
    private int movieID;
}
