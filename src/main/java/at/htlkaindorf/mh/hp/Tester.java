package at.htlkaindorf.mh.hp;

import java.util.List;

/**
 * <b>Movieholic</b><br><br>
 * Test the Homepage class
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
 */
public class Tester {
    /**
     * Test the Homepage class
     * @param args
     */
    public static void main(String[] args) {
        HomePage hp = HomePage.builder().userUID("Test").addHomePageItems("/discover/test", "/search/test").build();

        System.out.println(hp);
    }
}
